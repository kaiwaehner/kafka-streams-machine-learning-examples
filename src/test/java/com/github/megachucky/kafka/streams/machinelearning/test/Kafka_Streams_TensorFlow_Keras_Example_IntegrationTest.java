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
import org.apache.kafka.common.utils.MockTime;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.integration.utils.EmbeddedKafkaCluster;
import org.apache.kafka.streams.integration.utils.IntegrationTestUtils;
import org.apache.kafka.streams.kstream.KStream;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;

/**
 * 
 * @author Kai Waehner -
 *         <a href="http://www.kai-waehner.de">http://www.kai-waehner.de</a>
 * 
 *         End-to-end integration test based on
 *         {@link Kafka_Streams_TensorFlow_Keras_Example}, using an embedded
 *         Kafka cluster and a Keras model (trained with TensorFlow backend).
 *         Imported via DL4J Keras Java API support.
 *
 *         *
 */
public class Kafka_Streams_TensorFlow_Keras_Example_IntegrationTest {

	@ClassRule
	public static final EmbeddedKafkaCluster CLUSTER = new EmbeddedKafkaCluster(1);

	private static final String inputTopic = "InputTopic";
	private static final String outputTopic = "OutputTopic";

	private static String prediction = "unknown";

	private static INDArray output = null;

	@BeforeClass
	public static void startKafkaCluster() throws Exception {
		CLUSTER.createTopic(inputTopic);
		CLUSTER.createTopic(outputTopic);
	}

	@Test
	public void shouldPredictValues() throws Exception {

		// ########################################################
		// Step 1: Load Keras Model using DeepLearning4J API
		// ########################################################
		String simpleMlp = new ClassPathResource("generatedModels/Keras/simple_mlp.h5").getFile().getPath();
		System.out.println(simpleMlp.toString());

		MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);

		// Create test data which is sent from Kafka Producer into Input Topic
		List<String> inputValues = Arrays.asList("256,100");

		// ####################################################################
		// Step 2: Configure and start the Kafka Streams processor topology.
		// ####################################################################

		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG,
				"kafka-streams-tensorflow-keras-integration-test");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, CLUSTER.bootstrapServers());

		// Configure Kafka Streams Application
		// Specify default (de)serializers for record keys and for record
		// values.
		streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

		// In the subsequent lines we define the processing topology of the
		// Streams application.
		final StreamsBuilder builder = new StreamsBuilder();

		// Construct a `KStream` from the input topic, where
		// message values represent lines of text (for the sake of this example, we
		// ignore whatever may be stored in the message keys).
		final KStream<String, String> inputEvents = builder.stream(inputTopic);

		// ###############################################################
		// THIS IS WHERE WE DO REAL TIME MODEL INFERENCE FOR EACH EVENT
		// ###############################################################
		inputEvents.foreach((key, value) -> {

			// Transform input values (list of Strings) to expected DL4J parameters (two
			// Integer values):
			String[] valuesAsArray = value.split(",");
			INDArray input = Nd4j.create(Integer.parseInt(valuesAsArray[0]), Integer.parseInt(valuesAsArray[1]));

			// Apply the analytic model:
			output = model.output(input);
			prediction = output.toString();

		});

		// Transform message: Add prediction result
		KStream<String, Object> transformedMessage = inputEvents.mapValues(value -> "Prediction => " + prediction);

		// Send prediction result to Output Topic
		transformedMessage.to(outputTopic);

		// Start Kafka Streams Application to process new incoming messages from
		// Input Topic
		final KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);
		streams.cleanUp();
		streams.start();
		System.out.println("Prediction Microservice is running...");
		System.out.println("Input to Kafka Topic " + inputTopic + "; Output to Kafka Topic " + outputTopic);

		// ########################################################
		// Step 3: Produce some input data to the input topic.
		// ########################################################

		Properties producerConfig = new Properties();
		producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, CLUSTER.bootstrapServers());
		producerConfig.put(ProducerConfig.ACKS_CONFIG, "all");
		producerConfig.put(ProducerConfig.RETRIES_CONFIG, 0);
		producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		IntegrationTestUtils.produceValuesSynchronously(inputTopic, inputValues, producerConfig, new MockTime());

		// ########################################################
		// Step 4: Verify the application's output data.
		// ########################################################

		Properties consumerConfig = new Properties();
		consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, CLUSTER.bootstrapServers());
		consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG,
				"kafka-streams-tensorflow-keras-integration-test-standard-consumer");
		consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		List<KeyValue<String, String>> response = IntegrationTestUtils
				.waitUntilMinKeyValueRecordsReceived(consumerConfig, outputTopic, 1);
		streams.close();

		System.out.println("VALUE: " + response.get(0).value);

		assertThat(response).isNotNull();
		assertThat(response.get(0).value).doesNotMatch("Value => unknown");
		assertThat(response.get(0).value).contains("0.1000,    0.1000,    0.1000");
	}

}
