package  com.github.megachucky.kafka.streams.machinelearning.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.test.TestUtils;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.tensorflow.DataType;
import org.tensorflow.Graph;
import org.tensorflow.Output;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import com.github.megachucky.kafka.streams.machinelearning.Kafka_Streams_TensorFlow_Image_Recognition_Example;
import com.github.megachucky.kafka.streams.machinelearning.test.utils.EmbeddedSingleNodeKafkaCluster;
import com.github.megachucky.kafka.streams.machinelearning.test.utils.IntegrationTestUtils;

/**
 * 
 * @author Kai Waehner (www.kai-waehner.de)
 * 
 * End-to-end integration test based on {@link Kafka_Streams_TensorFlow_Image_Recognition_Example}, using an
 * embedded Kafka cluster and a TensorFlow CNN model.
 *
 * *
 */
public class Kafka_Streams_TensorFlow_Image_Recognition_Example_IntegrationTest {

	@ClassRule
	public static final EmbeddedSingleNodeKafkaCluster CLUSTER = new EmbeddedSingleNodeKafkaCluster();
 
	private static final String inputTopic = "ImageInputTopic";
	private static final String outputTopic = "ImageOutputTopic";

	// Prediction Value
	private static String imageClassification = "unknown";

	@BeforeClass
	public static void startKafkaCluster() throws Exception {
		CLUSTER.createTopic(inputTopic);
		CLUSTER.createTopic(outputTopic);
	}

	@Test
	public void shouldRecognizeImages() throws Exception {

		// Images: 'unknown', Airliner, 'unknown', Butterfly
		List<String> inputValues = Arrays.asList(
				"src/main/resources/TensorFlow_Images/trained_airplane_2.jpg",
				"src/main/resources/TensorFlow_Images/devil.png",
				"src/main/resources/TensorFlow_Images/trained_butterfly.jpg");

		// ########################################################
		// Step 1: Configure and start the processor topology.
		// ########################################################

		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-tensorflow-image-recognition-integration-test");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, CLUSTER.bootstrapServers());

	
		// Create TensorFlow object
	    String modelDir = "src/main/resources/generatedModels/CNN_inception5h";
	    
	    Path pathGraph = Paths.get(modelDir, "tensorflow_inception_graph.pb");
	    byte[] graphDef = Files.readAllBytes(pathGraph);
	    		    
	    Path pathModel = Paths.get(modelDir, "imagenet_comp_graph_label_strings.txt");
	    List<String> labels = Files.readAllLines(pathModel, Charset.forName("UTF-8"));
	      
	    
	    
		// Configure Kafka Streams Application
		// Specify default (de)serializers for record keys and for record
		// values.
		streamsConfiguration.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		streamsConfiguration.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

		// In the subsequent lines we define the processing topology of the
		// Streams application.
		final KStreamBuilder builder = new KStreamBuilder();

		// Construct a `KStream` from the input topic "AirlineInputTopic", where
		// message values
		// represent lines of text (for the sake of this example, we ignore
		// whatever may be stored
		// in the message keys).
		final KStream<String, String> imageInputLines = builder.stream(inputTopic);

		// Stream Processor (in this case 'foreach' to add custom logic, i.e.
		// apply the analytic model)
		imageInputLines.foreach(new ForeachAction<String, String>() {
			public void apply(String key, String value) {

				imageClassification = "unknown";
				
				String imageFile = value;

			    Path pathImage = Paths.get(imageFile);
			    byte[] imageBytes;
				try {
					imageBytes = Files.readAllBytes(pathImage);
					
					// Load and execute TensorFlow graph
					try (Tensor image = constructAndExecuteGraphToNormalizeImage(imageBytes)) {
					      float[] labelProbabilities = executeInceptionGraph(graphDef, image);
					      int bestLabelIdx = maxIndex(labelProbabilities);
					      
					      imageClassification = labels.get(bestLabelIdx);
					      
					      System.out.println(
					          String.format(
					              "BEST MATCH: %s (%.2f%% likely)",
					              imageClassification, labelProbabilities[bestLabelIdx] * 100f));
					    }
					
				} catch (IOException e) {
					e.printStackTrace();
				}			

			}
		});

		// Transform message: Add prediction information
		KStream<String, Object> transformedMessage = imageInputLines
				.mapValues(value -> "Image Recognition: What is content of the picture? => " + imageClassification);

		// Send prediction information to Output Topic
		transformedMessage.to(outputTopic);

		// Start Kafka Streams Application to process new incoming messages from
		// Input Topic
		final KafkaStreams streams = new KafkaStreams(builder, streamsConfiguration);
		streams.cleanUp();
		streams.start();
		System.out.println("Image Recognition Microservice is running...");
		System.out.println("Input to Kafka Topic " + inputTopic + "; Output to Kafka Topic " + outputTopic);

		// ########################################################
		// Step 2: Produce some input data to the input topic.
		// ########################################################

		Properties producerConfig = new Properties();
		producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, CLUSTER.bootstrapServers());
		producerConfig.put(ProducerConfig.ACKS_CONFIG, "all");
		producerConfig.put(ProducerConfig.RETRIES_CONFIG, 0);
		producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		IntegrationTestUtils.produceValuesSynchronously(inputTopic, inputValues, producerConfig);

		// ########################################################
		// Step 3: Verify the application's output data.
		// ########################################################

		Properties consumerConfig = new Properties();
		consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, CLUSTER.bootstrapServers());
		consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-streams-tensorflow-image-recognition-integration-test-standard-consumer");
		consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		List<KeyValue<String, String>> response = IntegrationTestUtils
				.waitUntilMinKeyValueRecordsReceived(consumerConfig, outputTopic, 3);
		streams.close();
		assertThat(response).isNotNull();
		assertThat(response.get(0).value).isEqualTo("Image Recognition: What is content of the picture? => airliner");
		assertThat(response.get(1).value).isNotEqualTo("Image Recognition: What is content of the picture? => airliner");
		assertThat(response.get(2).value).isEqualTo("Image Recognition: What is content of the picture? => cabbage butterfly");

	}
	
	
	// ########################################################################################
	// Private helper class for construction and execution of the pre-built TensorFlow model
	// ########################################################################################

	private static Tensor constructAndExecuteGraphToNormalizeImage(byte[] imageBytes) {
		try (Graph g = new Graph()) {
			GraphBuilder b = new GraphBuilder(g);
			// Some constants specific to the pre-trained model at:
			// https://storage.googleapis.com/download.tensorflow.org/models/inception5h.zip
			//
			// - The model was trained with images scaled to 224x224 pixels.
			// - The colors, represented as R, G, B in 1-byte each were
			// converted to
			// float using (value - Mean)/Scale.
			final int H = 224;
			final int W = 224;
			final float mean = 117f;
			final float scale = 1f;

			// Since the graph is being constructed once per execution here, we
			// can use a constant for the
			// input image. If the graph were to be re-used for multiple input
			// images, a placeholder would
			// have been more appropriate.
			final Output input = b.constant("input", imageBytes);
			final Output output = b
					.div(b.sub(
							b.resizeBilinear(b.expandDims(b.cast(b.decodeJpeg(input, 3), DataType.FLOAT),
									b.constant("make_batch", 0)), b.constant("size", new int[] { H, W })),
							b.constant("mean", mean)), b.constant("scale", scale));
			try (Session s = new Session(g)) {
				return s.runner().fetch(output.op().name()).run().get(0);
			}
		}
	}

	private static float[] executeInceptionGraph(byte[] graphDef, Tensor image) {
		try (Graph g = new Graph()) {
			g.importGraphDef(graphDef);
			try (Session s = new Session(g);
					Tensor result = s.runner().feed("input", image).fetch("output").run().get(0)) {
				final long[] rshape = result.shape();
				if (result.numDimensions() != 2 || rshape[0] != 1) {
					throw new RuntimeException(String.format(
							"Expected model to produce a [1 N] shaped tensor where N is the number of labels, instead it produced one with shape %s",
							Arrays.toString(rshape)));
				}
				int nlabels = (int) rshape[1];
				return result.copyTo(new float[1][nlabels])[0];
			}
		}
	}

	private static int maxIndex(float[] probabilities) {
		int best = 0;
		for (int i = 1; i < probabilities.length; ++i) {
			if (probabilities[i] > probabilities[best]) {
				best = i;
			}
		}
		return best;
	}

	// In the fullness of time, equivalents of the methods of this class should
	// be auto-generated from
	// the OpDefs linked into libtensorflow_jni.so. That would match what is
	// done in other languages
	// like Python, C++ and Go.
	static class GraphBuilder {
		GraphBuilder(Graph g) {
			this.g = g;
		}

		Output div(Output x, Output y) {
			return binaryOp("Div", x, y);
		}

		Output sub(Output x, Output y) {
			return binaryOp("Sub", x, y);
		}

		Output resizeBilinear(Output images, Output size) {
			return binaryOp("ResizeBilinear", images, size);
		}

		Output expandDims(Output input, Output dim) {
			return binaryOp("ExpandDims", input, dim);
		}

		Output cast(Output value, DataType dtype) {
			return g.opBuilder("Cast", "Cast").addInput(value).setAttr("DstT", dtype).build().output(0);
		}

		Output decodeJpeg(Output contents, long channels) {
			return g.opBuilder("DecodeJpeg", "DecodeJpeg").addInput(contents).setAttr("channels", channels).build()
					.output(0);
		}

		Output constant(String name, Object value) {
			try (Tensor t = Tensor.create(value)) {
				return g.opBuilder("Const", name).setAttr("dtype", t.dataType()).setAttr("value", t).build().output(0);
			}
		}

		private Output binaryOp(String type, Output in1, Output in2) {
			return g.opBuilder(type, type).addInput(in1).addInput(in2).build().output(0);
		}

		private Graph g;
	}
	

}
