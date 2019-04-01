package com.github.megachucky.kafka.streams.machinelearning;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.BinomialModelPrediction;

/**
 * @author Kai Waehner (www.kai-waehner.de)
 * 
 * Creates a new Kafka Streams application for prediction of flight delays 
 * The application uses the GBM model "gbm_pojo_test" (built with H2O.ai) to infer messages 
 * sent to Kafka topic "AirlineInputTopic". The outcome of model inference is sent to
 * Kafka topic "AirlineOutputTopic".
 *
 */
public class Kafka_Streams_MachineLearning_H2O_DeepLearning_Example extends Kafka_Streams_MachineLearning_H2O_Application {

	// Name of the generated H2O model
	static String modelClassName = "com.github.megachucky.kafka.streams.machinelearning.models.deeplearning_fe7c1f02_08ec_4070_b784_c2531147e451";

	static final String APPLICATION_ID = "kafka-streams-h2o-deeplearning-example";

	public static void main(final String[] args) throws Exception {

		// Configure Kafka Streams Application
		final String bootstrapServers = args.length > 0 ? args[0] : "localhost:9092";
		execute(bootstrapServers, APPLICATION_ID, modelClassName);
	}

}
