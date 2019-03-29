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
 *         {@link Kafka_Streams_TensorFlow_Image_Recognition_Example}, using an
 *         TopologyTestDriver and a TensorFlow CNN model.
 *
 */

public class Kafka_Streams_TensorFlow_Image_Recognition_ExampleTest {
    private TopologyTestDriver testDriver;

    private StringDeserializer stringDeserializer = new StringDeserializer();
    private ConsumerRecordFactory<String, String> recordFactory = new ConsumerRecordFactory<>(new StringSerializer(), new StringSerializer());

    @Before
    public void setup() throws IOException {
        testDriver = new TopologyTestDriver(Kafka_Streams_TensorFlow_Image_Recognition_Example.getStreamTopology(), Kafka_Streams_TensorFlow_Image_Recognition_Example.getStreamConfiguration("localhost:9092"));
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
        ProducerRecord<String, String>  output = testDriver.readOutput(Kafka_Streams_TensorFlow_Image_Recognition_Example.imageOutputTopic, stringDeserializer, stringDeserializer);
        assertThat(output).isNotNull();
        return output.value();
    }

    /** Simple recognition test validating only the recognition part of the output
     */
    @Test
    public void testOne() {
        testDriver.pipeInput(recordFactory.create(Kafka_Streams_TensorFlow_Image_Recognition_Example.imageInputTopic, null, "src/main/resources/TensorFlow_Images/new_airplane.jpg", 1L));
        assertThat(getOutput()).contains("What is the content of this picture? => airliner");
    }

    /** Test based on Kafka_Streams_TensorFlow_Image_Recognition_Example_IntegrationTest
     *
     */
    @Test
    public void testList() {
        // Images: 'unknown', Airliner, 'unknown', Butterfly
        List<String> inputValues = Arrays.asList("src/main/resources/TensorFlow_Images/trained_airplane_2.jpg",
                "src/main/resources/TensorFlow_Images/devil.png",
                "src/main/resources/TensorFlow_Images/trained_butterfly.jpg");
        List<KeyValue<String, String>> records = inputValues.stream().map(v -> new KeyValue<String, String>(null, v)).collect(Collectors.toList());


        testDriver.pipeInput(recordFactory.create(Kafka_Streams_TensorFlow_Image_Recognition_Example.imageInputTopic, records, 1L, 100L));
        assertThat(getOutput()).contains("What is the content of this picture? => airliner");
        assertThat(getOutput()).doesNotContain("What is the content of this picture? => airliner");
        assertThat(getOutput()).contains("What is the content of this picture? => cabbage butterfly");
    }


}
