package com.github.megachucky.kafka.streams.machinelearning.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.utils.MockTime;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.integration.utils.EmbeddedKafkaCluster;
import org.apache.kafka.streams.integration.utils.IntegrationTestUtils;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.test.TestUtils;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * 
 * @author Kai Waehner (www.kai-waehner.de)
 * 
 *         End-to-end integration test, using an embedded Kafka cluster and a
 *         DL4J DeepLearning Model.
 * 
 *         Prediction of Iris Flower Type 1, 2 or 3. Model returns probability
 *         for all three types, like [0.00/ 0.01/ 0.99].
 */
public class Kafka_Streams_MachineLearning_DL4J_DeepLearning_Iris_IntegrationTest {

	@ClassRule
	public static final EmbeddedKafkaCluster CLUSTER = new EmbeddedKafkaCluster(1);

	private static final String inputTopic = "IrisInputTopic";
	private static final String outputTopic = "IrisOutputTopic";

	// Generated DL4J model
	private File locationDL4JModel = new File("src/main/resources/generatedModels/DL4J/DL4J_Iris_Model.zip");

	// Prediction Value
	private static String irisPrediction = "unknown";

	@BeforeClass
	public static void startKafkaCluster() throws Exception {
		CLUSTER.createTopic(inputTopic);
		CLUSTER.createTopic(outputTopic);
	}

	@Test
	public void shouldPredictIrisFlowerType() throws Exception {

		// Iris input data (the model returns probabilities for input being each of Iris
		// Type 1, 2 and 3)
		List<String> inputValues = Arrays.asList("5.4,3.9,1.7,0.4", "7.0,3.2,4.7,1.4", "4.6,3.4,1.4,0.3");

		// Step 1: Configure and start the processor topology.
		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-dl4j-iris-integration-test");
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

		// Create DL4J object (see DeepLearning4J_CSV_Model.java)
		MultiLayerNetwork model = ModelSerializer.restoreMultiLayerNetwork(locationDL4JModel);

		// Configure Kafka Streams Application
		// Specify default (de)serializers for record keys and for record
		// values.
		streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

		// In the subsequent lines we define the processing topology of the
		// Streams application.
		final StreamsBuilder builder = new StreamsBuilder();

		// Construct a `KStream` from the input topic "IrisInputTopic", where
		// message values
		// represent lines of text (for the sake of this example, we ignore
		// whatever may be stored
		// in the message keys).
		final KStream<String, String> irisInputLines = builder.stream(inputTopic);

		// Stream Processor (in this case 'foreach' to add custom logic, i.e. apply the
		// analytic model)
		irisInputLines.foreach((key, value) -> {

			if (value != null && !value.equals("")) {
				System.out.println("#####################");
				System.out.println("Iris Input:" + value);

				// TODO Easier way to map from String[] to double[] !!!
				String[] stringArray = value.split(",");
				Double[] doubleArray = Arrays.stream(stringArray).map(Double::valueOf).toArray(Double[]::new);
				double[] irisInput = Stream.of(doubleArray).mapToDouble(Double::doubleValue).toArray();

				// Inference
				INDArray input = Nd4j.create(irisInput);
				INDArray result = model.output(input);

				System.out.println("Probabilities: " + result.toString());

				irisPrediction = result.toString();

			}

		});

		// Transform message: Add prediction information
		KStream<String, Object> transformedMessage = irisInputLines
				.mapValues(value -> "Prediction: Iris Probability => " + irisPrediction);

		// Send prediction information to Output Topic
		transformedMessage.to(outputTopic);

		// Start Kafka Streams Application to process new incoming messages from
		// Input Topic
		final KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);
		streams.cleanUp();
		streams.start();
		System.out.println("Iris Prediction Microservice is running...");
		System.out.println("Input to Kafka Topic 'IrisInputTopic'; Output to Kafka Topic 'IrisOutputTopic'");

		//
		// Step 2: Produce some input data to the input topic.
		//
		Properties producerConfig = new Properties();
		producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, CLUSTER.bootstrapServers());
		producerConfig.put(ProducerConfig.ACKS_CONFIG, "all");
		producerConfig.put(ProducerConfig.RETRIES_CONFIG, 0);
		producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		IntegrationTestUtils.produceValuesSynchronously(inputTopic, inputValues, producerConfig, new MockTime());

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
				.waitUntilMinKeyValueRecordsReceived(consumerConfig, outputTopic, 3);
		streams.close();
		assertThat(response).isNotNull();

		assertThat(response.get(0).value).isEqualTo("Prediction: Iris Probability => [0.29,  0.70,  0.01]");

		assertThat(response.get(1).value).isEqualTo("Prediction: Iris Probability => [0.00,  0.01,  0.99]");

		assertThat(response.get(2).value).isEqualTo("Prediction: Iris Probability => [0.36,  0.63,  0.01]");
	}

}
