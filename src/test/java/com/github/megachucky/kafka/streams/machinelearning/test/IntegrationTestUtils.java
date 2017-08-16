/**
 * Copyright 2016 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.github.megachucky.kafka.streams.machinelearning.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.errors.InvalidStateStoreException;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.apache.kafka.streams.state.WindowStoreIterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Utility functions to make integration testing more convenient.
 */
public class IntegrationTestUtils {

  private static final int UNLIMITED_MESSAGES = -1;
  public static final long DEFAULT_TIMEOUT = 30 * 1000L;

  /**
   * Returns up to `maxMessages` message-values from the topic.
   *
   * @param topic          Kafka topic to read messages from
   * @param consumerConfig Kafka consumer configuration
   * @param maxMessages    Maximum number of messages to read via the consumer.
   * @return The values retrieved via the consumer.
   */
  public static <K, V> List<V> readValues(String topic, Properties consumerConfig, int maxMessages) {
    List<KeyValue<K, V>> kvs = readKeyValues(topic, consumerConfig, maxMessages);
    return kvs.stream().map(kv -> kv.value).collect(Collectors.toList());
  }

  /**
   * Returns as many messages as possible from the topic until a (currently hardcoded) timeout is
   * reached.
   *
   * @param topic          Kafka topic to read messages from
   * @param consumerConfig Kafka consumer configuration
   * @return The KeyValue elements retrieved via the consumer.
   */
  public static <K, V> List<KeyValue<K, V>> readKeyValues(String topic, Properties consumerConfig) {
    return readKeyValues(topic, consumerConfig, UNLIMITED_MESSAGES);
  }

  /**
   * Returns up to `maxMessages` by reading via the provided consumer (the topic(s) to read from are
   * already configured in the consumer).
   *
   * @param topic          Kafka topic to read messages from
   * @param consumerConfig Kafka consumer configuration
   * @param maxMessages    Maximum number of messages to read via the consumer
   * @return The KeyValue elements retrieved via the consumer
   */
  public static <K, V> List<KeyValue<K, V>> readKeyValues(String topic, Properties consumerConfig, int maxMessages) {
    KafkaConsumer<K, V> consumer = new KafkaConsumer<>(consumerConfig);
    consumer.subscribe(Collections.singletonList(topic));
    int pollIntervalMs = 100;
    int maxTotalPollTimeMs = 2000;
    int totalPollTimeMs = 0;
    List<KeyValue<K, V>> consumedValues = new ArrayList<>();
    while (totalPollTimeMs < maxTotalPollTimeMs && continueConsuming(consumedValues.size(), maxMessages)) {
      totalPollTimeMs += pollIntervalMs;
      ConsumerRecords<K, V> records = consumer.poll(pollIntervalMs);
      for (ConsumerRecord<K, V> record : records) {
        consumedValues.add(new KeyValue<>(record.key(), record.value()));
      }
    }
    consumer.close();
    return consumedValues;
  }

  private static boolean continueConsuming(int messagesConsumed, int maxMessages) {
    return maxMessages <= 0 || messagesConsumed < maxMessages;
  }

  /**
   * @param topic          Kafka topic to write the data records to
   * @param records        Data records to write to Kafka
   * @param producerConfig Kafka producer configuration
   * @param <K>            Key type of the data records
   * @param <V>            Value type of the data records
   */
  public static <K, V> void produceKeyValuesSynchronously(
      String topic, Collection<KeyValue<K, V>> records, Properties producerConfig)
      throws ExecutionException, InterruptedException {
    Producer<K, V> producer = new KafkaProducer<>(producerConfig);
    for (KeyValue<K, V> record : records) {
      Future<RecordMetadata> f = producer.send(
          new ProducerRecord<>(topic, record.key, record.value));
      f.get();
    }
    producer.flush();
    producer.close();
  }

  public static <V> void produceValuesSynchronously(
      String topic, Collection<V> records, Properties producerConfig)
      throws ExecutionException, InterruptedException {
    Collection<KeyValue<Object, V>> keyedRecords =
        records.stream().map(record -> new KeyValue<>(null, record)).collect(Collectors.toList());
    produceKeyValuesSynchronously(topic, keyedRecords, producerConfig);
  }

  public static <K, V> List<KeyValue<K, V>> waitUntilMinKeyValueRecordsReceived(Properties consumerConfig,
                                                                                String topic,
                                                                                int expectedNumRecords) throws InterruptedException {

    return waitUntilMinKeyValueRecordsReceived(consumerConfig, topic, expectedNumRecords, DEFAULT_TIMEOUT);
  }

  /**
   * Wait until enough data (key-value records) has been consumed.
   *
   * @param consumerConfig     Kafka Consumer configuration
   * @param topic              Topic to consume from
   * @param expectedNumRecords Minimum number of expected records
   * @param waitTime           Upper bound in waiting time in milliseconds
   * @return All the records consumed, or null if no records are consumed
   * @throws AssertionError if the given wait time elapses
   */
  public static <K, V> List<KeyValue<K, V>> waitUntilMinKeyValueRecordsReceived(Properties consumerConfig,
                                                                                String topic,
                                                                                int expectedNumRecords,
                                                                                long waitTime) throws InterruptedException {
    List<KeyValue<K, V>> accumData = new ArrayList<>();
    long startTime = System.currentTimeMillis();
    while (true) {
      List<KeyValue<K, V>> readData = readKeyValues(topic, consumerConfig);
      accumData.addAll(readData);
      if (accumData.size() >= expectedNumRecords)
        return accumData;
      if (System.currentTimeMillis() > startTime + waitTime)
        throw new AssertionError("Expected " + expectedNumRecords +
            " but received only " + accumData.size() +
            " records before timeout " + waitTime + " ms");
      Thread.sleep(Math.min(waitTime, 100L));
    }
  }

  public static <V> List<V> waitUntilMinValuesRecordsReceived(Properties consumerConfig,
                                                              String topic,
                                                              int expectedNumRecords) throws InterruptedException {

    return waitUntilMinValuesRecordsReceived(consumerConfig, topic, expectedNumRecords, DEFAULT_TIMEOUT);
  }

  /**
   * Wait until enough data (value records) has been consumed.
   *
   * @param consumerConfig     Kafka Consumer configuration
   * @param topic              Topic to consume from
   * @param expectedNumRecords Minimum number of expected records
   * @param waitTime           Upper bound in waiting time in milliseconds
   * @return All the records consumed, or null if no records are consumed
   * @throws AssertionError if the given wait time elapses
   */
  public static <V> List<V> waitUntilMinValuesRecordsReceived(Properties consumerConfig,
                                                              String topic,
                                                              int expectedNumRecords,
                                                              long waitTime) throws InterruptedException {
    List<V> accumData = new ArrayList<>();
    long startTime = System.currentTimeMillis();
    while (true) {
      List<V> readData = readValues(topic, consumerConfig, expectedNumRecords);
      accumData.addAll(readData);
      if (accumData.size() >= expectedNumRecords)
        return accumData;
      if (System.currentTimeMillis() > startTime + waitTime)
        throw new AssertionError("Expected " + expectedNumRecords +
            " but received only " + accumData.size() +
            " records before timeout " + waitTime + " ms");
      Thread.sleep(Math.min(waitTime, 100L));
    }
  }

  /**
   * Waits until the named store is queryable and, once it is, returns a reference to the store.
   *
   * Caveat: This is a point in time view and it may change due to partition reassignment.
   * That is, the returned store may still not be queryable in case a rebalancing is happening or
   * happened around the same time.  This caveat is acceptable for testing purposes when only a
   * single `KafkaStreams` instance of the application is running.
   *
   * @param streams            the `KafkaStreams` instance to which the store belongs
   * @param storeName          the name of the store
   * @param queryableStoreType the type of the (queryable) store
   * @param <T>                the type of the (queryable) store
   * @return the same store, which is now ready for querying (but see caveat above)
   */
  public static <T> T waitUntilStoreIsQueryable(final String storeName,
                                                final QueryableStoreType<T> queryableStoreType,
                                                final KafkaStreams streams) throws InterruptedException {
    while (true) {
      try {
        return streams.store(storeName, queryableStoreType);
      } catch (InvalidStateStoreException ignored) {
        // store not yet ready for querying
        Thread.sleep(50);
      }
    }
  }

  /**
   * Asserts that the key-value store contains exactly the expected content and nothing more.
   *
   * @param store    the store to be validated
   * @param expected the expected contents of the store
   * @param <K>      the store's key type
   * @param <V>      the store's value type
   */
  public static <K, V> void assertThatKeyValueStoreContains(ReadOnlyKeyValueStore<K, V> store, Map<K, V> expected) {
    for (K key : expected.keySet()) {
      V actualValue = store.get(key);
      assertThat(actualValue).isEqualTo(expected.get(key));
    }
  }

  /**
   * Asserts that the oldest available window in the window store contains the expected content.
   *
   * @param store    the store to be validated
   * @param expected the expected contents of the store
   * @param <K>      the store's key type
   * @param <V>      the store's value type
   */
  public static <K, V> void assertThatOldestWindowContains(ReadOnlyWindowStore<K, V> store, Map<K, V> expected) {
    long fromBeginningOfTimeMs = 0;
    long toNowInProcessingTimeMs = System.currentTimeMillis();
    for (K key : expected.keySet()) {
      long windowCounter = 0;
      // For each key, `ReadOnlyWindowStore#fetch()` guarantees that the iterator iterates through
      // the windows in ascending-time order; that is, the first window (if any) is the oldest
      // available window for that key.
      try (WindowStoreIterator<V> iterator = store.fetch(key, fromBeginningOfTimeMs, toNowInProcessingTimeMs)) {
        while (iterator.hasNext() && windowCounter <= 1) {
          windowCounter++;
          KeyValue<Long, V> next = iterator.next();
          V actualValue = next.value;
          assertThat(actualValue).isEqualTo(expected.get(key));
        }
      }
    }
  }

}