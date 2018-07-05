package com.github.megachucky.kafka.streams.machinelearning.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.test.TestUtils;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com.github.megachucky.kafka.streams.machinelearning.Kafka_Streams_MachineLearning_H2O_GBM_Example;
import com.github.megachucky.kafka.streams.machinelearning.test.utils.EmbeddedSingleNodeKafkaCluster;
import com.github.megachucky.kafka.streams.machinelearning.test.utils.IntegrationTestUtils;

import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.BinomialModelPrediction;

/**
 * 
 * @author Kai Waehner (www.kai-waehner.de)
 * 
 *         End-to-end integration test based on
 *         {@link Kafka_Streams_MachineLearning_H2O_GBM_Example}, using an
 *         embedded Kafka cluster and a H2O.ai GBM Model.
 *
 *         See {@link Kafka_Streams_MachineLearning_H2O_GBM_Example} for further
 *         documentation.
 *
 */
public class Kafka_Streams_MachineLearning_H2O_GBM_Example_IntegrationTest {

	@ClassRule
	public static final EmbeddedSingleNodeKafkaCluster CLUSTER = new EmbeddedSingleNodeKafkaCluster();

	private static final String inputTopic = "AirlineInputTopic";
	private static final String outputTopic = "AirlineOutputTopic";

	// Name of the generated H2O.ai model
	private static String modelClassName = "com.github.megachucky.kafka.streams.machinelearning.models.gbm_pojo_test";

	// Prediction Value
	private static String airlineDelayPreduction = "unknown";

	@BeforeClass
	public static void startKafkaCluster() throws Exception {
		CLUSTER.createTopic(inputTopic);
		CLUSTER.createTopic(outputTopic);
	}

	@Test
	public void shouldPredictFlightDelay() throws Exception {

		// Flight data (one single flight) --> We want to predict if it will be
		// delayed or not
		List<String> inputValues = Arrays.asList(
				"1987,10,14,3,741,730,912,849,PS,1451,NA,91,79,NA,23,11,SAN,SFO,447,NA,NA,0,NA,0,NA,NA,NA,NA,NA,YES,YES",
				"1999,10,14,3,741,730,912,849,PS,1451,NA,91,79,NA,23,11,SAN,SFO,447,NA,NA,0,NA,0,NA,NA,NA,NA,NA,YES,YES");

		// Step 1: Configure and start the processor topology.
		//

		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-h2o-gbm-integration-test");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, CLUSTER.bootstrapServers());

		// The commit interval for flushing records to state stores and
		// downstream must be lower than
		// this integration test's timeout (30 secs) to ensure we observe the
		// expected processing results.
		streamsConfiguration.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10 * 1000);
		streamsConfiguration.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		// Use a temporary directory for storing state, which will be
		// automatically removed after the test.
		streamsConfiguration.put(StreamsConfig.STATE_DIR_CONFIG, TestUtils.tempDirectory().getAbsolutePath());

		// Create H2O object (see gbm_pojo_test.java)
		hex.genmodel.GenModel rawModel;
		rawModel = (hex.genmodel.GenModel) Class.forName(modelClassName).newInstance();
		EasyPredictModelWrapper model = new EasyPredictModelWrapper(rawModel);

		// Configure Kafka Streams Application
		// Specify default (de)serializers for record keys and for record
		// values.
		streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

		// In the subsequent lines we define the processing topology of the
		// Streams application.
		final StreamsBuilder builder = new StreamsBuilder();

		// Construct a `KStream` from the input topic "AirlineInputTopic", where
		// message values
		// represent lines of text (for the sake of this example, we ignore
		// whatever may be stored
		// in the message keys).
		final KStream<String, String> airlineInputLines = builder.stream(inputTopic);

		// Stream Processor (in this case 'foreach' to add custom logic, i.e.
		// apply the analytic model)

		airlineInputLines.foreach((key, value) -> {

			// Year,Month,DayofMonth,DayOfWeek,DepTime,CRSDepTime,ArrTime,CRSArrTime,UniqueCarrier,FlightNum,TailNum,ActualElapsedTime,CRSElapsedTime,AirTime,ArrDelay,DepDelay,Origin,Dest,Distance,TaxiIn,TaxiOut,Cancelled,CancellationCode,Diverted,CarrierDelay,WeatherDelay,NASDelay,SecurityDelay,LateAircraftDelay,IsArrDelayed,IsDepDelayed
			// value:
			// 1987,10,14,3,741,730,912,849,PS,1451,NA,91,79,NA,23,11,SAN,SFO,447,NA,NA,0,NA,0,NA,NA,NA,NA,NA,YES,YES
			if (value != null && !value.equals("")) {
				System.out.println("#####################");
				System.out.println("Flight Input:" + value);

				String[] valuesArray = value.split(",");

				RowData row = new RowData();
				row.put("Year", valuesArray[0]);
				row.put("Month", valuesArray[1]);
				row.put("DayofMonth", valuesArray[2]);
				row.put("DayOfWeek", valuesArray[3]);
				row.put("CRSDepTime", valuesArray[5]);
				row.put("UniqueCarrier", valuesArray[8]);
				row.put("Origin", valuesArray[16]);
				row.put("Dest", valuesArray[17]);
				BinomialModelPrediction p = null;
				try {
					p = model.predictBinomial(row);
				} catch (PredictException e) {
					e.printStackTrace();
				}

				airlineDelayPreduction = p.label;
				System.out.println("Label (aka prediction) is flight departure delayed: " + p.label);
				System.out.print("Class probabilities: ");
				for (int i = 0; i < p.classProbabilities.length; i++) {
					if (i > 0) {
						System.out.print(",");
					}
					System.out.print(p.classProbabilities[i]);
				}
				System.out.println("");
				System.out.println("#####################");

			}

		});

		// Transform message: Add prediction information
		KStream<String, Object> transformedMessage = airlineInputLines
				.mapValues(value -> "Prediction: Is Airline delayed? => " + airlineDelayPreduction);

		// Send prediction information to Output Topic
		transformedMessage.to(outputTopic);

		// Start Kafka Streams Application to process new incoming messages from
		// Input Topic
		final KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);
		streams.cleanUp();
		streams.start();
		System.out.println("Airline Delay Prediction Microservice is running...");
		System.out.println("Input to Kafka Topic 'AirlineInputTopic'; Output to Kafka Topic 'AirlineOutpuTopic'");

		//
		// Step 2: Produce some input data to the input topic.
		//
		Properties producerConfig = new Properties();
		producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, CLUSTER.bootstrapServers());
		producerConfig.put(ProducerConfig.ACKS_CONFIG, "all");
		producerConfig.put(ProducerConfig.RETRIES_CONFIG, 0);
		producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		IntegrationTestUtils.produceValuesSynchronously(inputTopic, inputValues, producerConfig);

		//
		// Step 3: Verify the application's output data.
		//
		Properties consumerConfig = new Properties();
		consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, CLUSTER.bootstrapServers());
		consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG,
				"machine-learning-example-integration-test-standard-consumer");
		consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		List<KeyValue<String, String>> response = IntegrationTestUtils
				.waitUntilMinKeyValueRecordsReceived(consumerConfig, outputTopic, 2);
		streams.close();
		assertThat(response).isNotNull();
		assertThat(response.get(0).value).isEqualTo("Prediction: Is Airline delayed? => YES");

		assertThat(response.get(1).value).isEqualTo("Prediction: Is Airline delayed? => NO");
	}

}
