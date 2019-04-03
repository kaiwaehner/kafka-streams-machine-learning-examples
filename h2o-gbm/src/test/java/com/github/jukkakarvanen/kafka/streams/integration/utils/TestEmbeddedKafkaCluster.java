package com.github.jukkakarvanen.kafka.streams.integration.utils;

import org.apache.kafka.streams.integration.utils.EmbeddedKafkaCluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/** This is helper class to workaround for Failing stream tests in Windows environment KAFKA-6647.
 *
 * @author Jukka Karvanen
 *
 * The causing issue is https://issues.apache.org/jira/browse/KAFKA-6647
 * Replacing EmbeddedKafkaCluster with TestEmbeddedKafkaCluster will catch and ignore the exception
 * happening during the tear down of the test
 * The exception does not have affect to functionality
  */

public class TestEmbeddedKafkaCluster extends EmbeddedKafkaCluster {
    private static final Logger log = LoggerFactory.getLogger(TestEmbeddedKafkaCluster.class);

    public TestEmbeddedKafkaCluster(int numBrokers) {
        super(numBrokers);
    }

    public TestEmbeddedKafkaCluster(int numBrokers, Properties brokerConfig) {
        super(numBrokers, brokerConfig);
    }

    public TestEmbeddedKafkaCluster(int numBrokers, Properties brokerConfig, long mockTimeMillisStart) {
        super(numBrokers, brokerConfig, mockTimeMillisStart);
    }

    public TestEmbeddedKafkaCluster(int numBrokers, Properties brokerConfig, long mockTimeMillisStart, long mockTimeNanoStart) {
        super(numBrokers, brokerConfig, mockTimeMillisStart, mockTimeNanoStart);
    }

    public void after() {
        try {
            super.after();
        } catch (RuntimeException e) {
            log.warn("Ignoring exception, test failing in Windows due this exception {}", e);
        }
    }
}
