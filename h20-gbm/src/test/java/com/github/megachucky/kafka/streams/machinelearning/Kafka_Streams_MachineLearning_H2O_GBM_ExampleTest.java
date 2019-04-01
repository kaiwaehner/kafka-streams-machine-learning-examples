package com.github.megachucky.kafka.streams.machinelearning;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.test.ConsumerRecordFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * TopologyTestDriver based test about stream processing of Kafka_Streams_TensorFlow_Image_Recognition_Example.
 *
 * @author Jukka Karvanen /  jukinimi.com
 * *       Unit Test of
 *         {@link Kafka_Streams_MachineLearning_H2O_GBM_Example}, using an
 *         TopologyTestDriver and a TensorFlow CNN model.
 *
 */

public class Kafka_Streams_MachineLearning_H2O_GBM_ExampleTest {
    private TopologyTestDriver testDriver;

    private StringDeserializer stringDeserializer = new StringDeserializer();
    private ConsumerRecordFactory<String, String> recordFactory = new ConsumerRecordFactory<>(new StringSerializer(), new StringSerializer());

    @Before
    public void setup() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        testDriver = new TopologyTestDriver(Kafka_Streams_MachineLearning_H2O_GBM_Example.getStreamTopology(Kafka_Streams_MachineLearning_H2O_GBM_Example.modelClassName), Kafka_Streams_MachineLearning_H2O_GBM_Example.getStreamConfiguration("localhost:9092",Kafka_Streams_MachineLearning_H2O_GBM_Example.APPLICATION_ID));
    }

    @After
    public void tearDown() {
        try {
            testDriver.close();
        } catch (RuntimeException e) {
            // https://issues.apache.org/jira/browse/KAFKA-6647 causes exception when executed in Windows, ignoring it
            // Logged stacktrace cannot be avoided
            System.out.println("Ignoring exception, test failing in Windows due this exception:" + e.getLocalizedMessage());
        }
    }

    private String getOutput() {
        ProducerRecord<String, String>  output = testDriver.readOutput(Kafka_Streams_MachineLearning_H2O_GBM_Example.OUTPUT_TOPIC, stringDeserializer, stringDeserializer);
        assertThat(output).isNotNull();
        return output.value();
    }

    /** Simple test validating only the prediction part of the output
     */
    @Test
    public void testOne() {
        testDriver.pipeInput(recordFactory.create(Kafka_Streams_MachineLearning_H2O_GBM_Example.INPUT_TOPIC, null, "1987,10,14,3,741,730,912,849,PS,1451,NA,91,79,NA,23,11,SAN,SFO,447,NA,NA,0,NA,0,NA,NA,NA,NA,NA,YES,YES", 1L));
        assertThat(getOutput()).isEqualTo("Prediction: Is Airline delayed? => YES");
    }

    /** Test based on Kafka_Streams_TensorFlow_Image_Recognition_Example_IntegrationTest
     *
     */
    @Test
    public void testList() {
        // Flight data (one single flight) --> We want to predict if it will be
        // delayed or not
        List<String> inputValues = Arrays.asList(
                "1987,10,14,3,741,730,912,849,PS,1451,NA,91,79,NA,23,11,SAN,SFO,447,NA,NA,0,NA,0,NA,NA,NA,NA,NA,YES,YES",
                "1999,10,14,3,741,730,912,849,PS,1451,NA,91,79,NA,23,11,SAN,SFO,447,NA,NA,0,NA,0,NA,NA,NA,NA,NA,YES,YES");
        List<KeyValue<String, String>> records = inputValues.stream().map(v -> new KeyValue<String, String>(null, v)).collect(Collectors.toList());


        testDriver.pipeInput(recordFactory.create(Kafka_Streams_MachineLearning_H2O_GBM_Example.INPUT_TOPIC, records, 1L, 100L));
        assertThat(getOutput()).isEqualTo("Prediction: Is Airline delayed? => YES");
        assertThat(getOutput()).isEqualTo("Prediction: Is Airline delayed? => NO");
    }


}
