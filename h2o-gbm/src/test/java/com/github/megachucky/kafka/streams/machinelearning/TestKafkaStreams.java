package com.github.megachucky.kafka.streams.machinelearning;

import org.apache.kafka.common.utils.Time;
import org.apache.kafka.streams.KafkaClientSupplier;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * This is helper class to workaround for Failing stream tests in Windows
 * environment KAFKA-6647.
 *
 * @author Jukka Karvanen
 *
 *         The causing issue is https://issues.apache.org/jira/browse/KAFKA-6647
 *         Replacing KafkaStreams with TestKafkaStreams will catch and ignore
 *         the exception caused by cleanUp The exception does not have affect to
 *         functionality
 */

public class TestKafkaStreams extends KafkaStreams {
    private static final Logger log = LoggerFactory.getLogger(TestKafkaStreams.class);

    public TestKafkaStreams(Topology topology, Properties props) {
        super(topology, props);
    }

    public TestKafkaStreams(Topology topology, Properties props, KafkaClientSupplier clientSupplier) {
        super(topology, props, clientSupplier);
    }

    public TestKafkaStreams(Topology topology, Properties props, Time time) {
        super(topology, props, time);
    }

    public TestKafkaStreams(Topology topology, Properties props, KafkaClientSupplier clientSupplier, Time time) {
        super(topology, props, clientSupplier, time);
    }

    public void cleanUp() {
        try {
            super.cleanUp();
        } catch (RuntimeException e) {
            log.warn("Ignoring exception, test failing in Windows due this exception {}", e);
        }
    }
}
