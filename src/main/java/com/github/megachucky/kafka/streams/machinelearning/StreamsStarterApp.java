package com.github.megachucky.kafka.streams.machinelearning;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

/**
 * Demo Kakfa Streams app. Foundation for the other ML classes.
 * 
 * @author kai.waehner (www.kai-waehner.de)
 *
 */
public class StreamsStarterApp {

	public static void main(String[] args) {

		Properties config = new Properties();
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-starter-app");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

		StreamsBuilder builder = new StreamsBuilder();

		KStream<String, String> kStream = builder.stream("streams-file-input");
		// do stuff
		kStream.to("streams-wordcount-output");

		KafkaStreams streams = new KafkaStreams(builder.build(), config);
		streams.cleanUp(); // only do this in dev - not in prod
		streams.start();

		// print the topology
		System.out.println(streams.localThreadsMetadata().toString());

		// shutdown hook to correctly close the streams application
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

	}

}
