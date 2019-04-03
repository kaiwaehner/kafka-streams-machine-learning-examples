# Machine Learning + Kafka Streams Examples

General info in main [Readme](../readme.md) 

## Example 1 - Gradient Boosting with H2O.ai for Prediction of Flight Delays

### Use Case

Gradient Boosting Method (GBM) to predict flight delays.
A H2O generated GBM Java model (POJO) is instantiated and used in a Kafka Streams application to do interference on new events.

### Machine Learning Technology

* [H2O](https://www.h2o.ai)
* Check the [H2O demo](https://github.com/h2oai/h2o-2/wiki/Hacking-Airline-DataSet-with-H2O) to understand the test and and how the model was built
* You can re-use the generated Java model attached to this project ([gbm_pojo_test.java](src/main/java/com/github/megachucky/kafka/streams/machinelearning/models/gbm_pojo_test.java)) or build your own model using R, Python, Flow UI or any other technologies supported by H2O framework.

### Source Code

Business Logic (applying the analytic model to do the prediction):
[Kafka_Streams_MachineLearning_H2O_Application.java](src/main/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_MachineLearning_H2O_Application.java)

Specification of the used model:
[Kafka_Streams_MachineLearning_H2O_GBM_Example.java](src/main/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_MachineLearning_H2O_GBM_Example.java)

### Automated Tests

Unit Test using TopologyTestDriver:
[Kafka_Streams_MachineLearning_H2O_GBM_ExampleTest.java](src/test/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_MachineLearning_H2O_GBM_ExampleTest.java)

Integration Test using EmbeddedKafkaCluster:
[Kafka_Streams_MachineLearning_H2O_GBM_Example_IntegrationTest.java](src/test/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_MachineLearning_H2O_GBM_Example_IntegrationTest.java)

### Manual Testing

You can easily test this by yourself. Here are the steps:

* Start Kafka, e.g. with Confluent CLI:

                confluent start kafka
* Create topics AirlineInputTopic and AirlineOutputTopic

                kafka-topics --bootstrap-server localhost:9092 --create --topic AirlineInputTopic --partitions 3 --replication-factor 1

                kafka-topics --bootstrap-server localhost:9092 --create --topic AirlineOutputTopic --partitions 3 --replication-factor 1
* Start the Kafka Streams app: 

                java -cp h2o-gbm/target/h2o-gbm-CP51_AK21-jar-with-dependencies.jar com.github.megachucky.kafka.streams.machinelearning.Kafka_Streams_MachineLearning_H2O_GBM_Example
* Send messages, e.g. with kafkacat:

                echo -e "1987,10,14,3,741,730,912,849,PS,1451,NA,91,79,NA,23,11,SAN,SFO,447,NA,NA,0,NA,0,NA,NA,NA,NA,NA,YES,YES" | kafkacat -b localhost:9092 -P -t AirlineInputTopic
* Consume predictions:

                kafka-console-consumer --bootstrap-server localhost:9092 --topic AirlineOutputTopic --from-beginning
* Find more details in the unit test...

## H2O Deep Learning instead of H2O GBM Model

The project includes another example with similar code to use a [H2O Deep Learning model](src/main/java/com/github/megachucky/kafka/streams/machinelearning/models/deeplearning_fe7c1f02_08ec_4070_b784_c2531147e451.java) instead of H2O GBM Model: [Kafka_Streams_MachineLearning_H2O_DeepLearning_Example_IntegrationTest.java](src/test/java/com/github/megachucky/kafka/streams/machinelearning/test/Kafka_Streams_MachineLearning_H2O_DeepLearning_Example_IntegrationTest.java)
This shows how you can easily test or replace different analytic models for one use case, or even use them for A/B testing.

### Source Code

Business Logic (applying the analytic model to do the prediction):
[Kafka_Streams_MachineLearning_H2O_Application.java](src/main/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_MachineLearning_H2O_Application.java)

Specification of the used model:
[Kafka_Streams_MachineLearning_H2O_DeepLearning_Example.java](src/main/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_MachineLearning_H2O_DeepLearning_Example.java)

### Unit Test

Unit Test using TopologyTestDriver:
[Kafka_Streams_MachineLearning_H2O_DeepLearning_ExampleTest.java](src/test/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_MachineLearning_H2O_DeepLearning_ExampleTest.java)

Integration Test using EmbeddedKafkaCluster:[Kafka_Streams_MachineLearning_H2O_DeepLearning_Example_IntegrationTest.java](src/test/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_MachineLearning_H2O_DeepLearning_Example_IntegrationTest.java)

### Manual Testing

Same as above but change class to start app:

* Start the Kafka Streams app: 

                java -cp h2o-gbm/target/h2o-gbm-CP51_AK21-jar-with-dependencies.jar com.github.megachucky.kafka.streams.machinelearning.Kafka_Streams_MachineLearning_H2O_DeepLearning_Example