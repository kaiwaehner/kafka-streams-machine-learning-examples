package com.github.megachucky.kafka.streams.machinelearning;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueMapper;

import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.BinomialModelPrediction;

/**
 * @author Kai Waehner (www.kai-waehner.de)
 * 
 *         Creates a new Kafka Streams application for prediction of flight
 *         delays The application uses the GBM model "gbm_pojo_test" (built with
 *         H2O.ai) to infer messages sent to Kafka topic "AirlineInputTopic".
 *         The outcome of model inference is sent to Kafka topic
 *         "AirlineOutputTopic".
 *
 */
public class Kafka_Streams_MachineLearning_H2O_GBM_Example_Refactored {

	// Name of the generated H2O model
	private static String modelClassName = "com.github.megachucky.kafka.streams.machinelearning.models.gbm_pojo_test";

	// Prediction Value
	private static String airlineDelayPreduction = "unknown";

	public static void main(final String[] args) throws Exception {

		// Create H2O object (see gbm_pojo_test.java)
		hex.genmodel.GenModel rawModel;
		rawModel = (hex.genmodel.GenModel) Class.forName(modelClassName).newInstance();
		EasyPredictModelWrapper model = new EasyPredictModelWrapper(rawModel);

		// Configure Kafka Streams Application
		final String bootstrapServers = args.length > 0 ? args[0] : "localhost:9092";
		final Properties streamsConfiguration = new Properties();
		// Give the Streams application a unique name. The name must be unique
		// in the Kafka cluster
		// against which the application is run.
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-h2o-gbm-example");
		// Where to find Kafka broker(s).
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

		// Specify default (de)serializers for record keys and for record
		// values.
		streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

		// For illustrative purposes we disable record caches
		streamsConfiguration.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);

		// In the subsequent lines we define the processing topology of the
		// Streams application.
		final StreamsBuilder builder = new StreamsBuilder();

		// Construct a `KStream` from the input topic "AirlineInputTopic", where
		// message values
		// represent lines of text (for the sake of this example, we ignore
		// whatever may be stored
		// in the message keys).
		final KStream<String, String> airlineInputLines = builder.stream("AirlineInputTopic");

		ValueMapper<String, String> myValMapper = value -> {

			// Year,Month,DayofMonth,DayOfWeek,DepTime,CRSDepTime,ArrTime,CRSArrTime,UniqueCarrier,FlightNum,TailNum,ActualElapsedTime,CRSElapsedTime,AirTime,ArrDelay,DepDelay,Origin,Dest,Distance,TaxiIn,TaxiOut,Cancelled,CancellationCode,Diverted,CarrierDelay,WeatherDelay,NASDelay,SecurityDelay,LateAircraftDelay,IsArrDelayed,IsDepDelayed
			// value:
			// YES, probably delayed:
			// 1987,10,14,3,741,730,912,849,PS,1451,NA,91,79,NA,23,11,SAN,SFO,447,NA,NA,0,NA,0,NA,NA,NA,NA,NA,YES,YES
			// NO, probably not delayed:
			// 1999,10,14,3,741,730,912,849,PS,1451,NA,91,79,NA,23,11,SAN,SFO,447,NA,NA,0,NA,0,NA,NA,NA,NA,NA,YES,YES

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

			return value;

		};

		// Stream Processor (in this case 'mapValues' to add custom logic, i.e. apply
		// the analytic model)
		airlineInputLines.mapValues(value -> myValMapper);

		// airlineInputLines.print();

		// Transform message: Add prediction information
		KStream<String, Object> transformedMessage = airlineInputLines
				.mapValues(value -> "Prediction: Is Airline delayed? => " + airlineDelayPreduction);

		// Send prediction information to Output Topic
		transformedMessage.to("AirlineOutputTopic");

		// Start Kafka Streams Application to process new incoming messages from Input
		// Topic
		final KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration);
		streams.cleanUp();
		streams.start();
		System.out.println("Airline Delay Prediction Microservice is running...");
		System.out.println("Input to Kafka Topic 'AirlineInputTopic'; Output to Kafka Topic 'AirlineOutputTopic'");

		// Add shutdown hook to respond to SIGTERM and gracefully close Kafka
		// Streams
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

	}

}
