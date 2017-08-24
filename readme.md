# Machine Learning + Kafka Streams Examples

This project contains **examples which demonstrate how to deploy analytic models to mission-critical, scalable production leveraging Apache Kafka's Streams API.**
Examples will include analytic models built with TensorFlow, Keras, H2O, Python, DeepLearning4J and other technologies.

## Installation and Usage
Just download the project and run 'mvn clean package'.

Every examples includes a implementation and a unit test. The project and examples are very simple and lightweight. No further configuration is needed to build and run it.

Java 8 and Maven 3 are required. Maven will download all required dependencies.

## Use Cases and Technologies
This project kicks off with an example of using Kafka Streams to deploy a H2O GBM model. More sophisticated use cases and other technologies will be added over time.

### Example 1 - Gradient Boosting with H2O.ai for Prediction of Flight Delays
**Use Case**

Gradient Boosting Method (GBM) to predict flight delays.
H2O generated GBM Java model is instantiated and used in a Kafka Streams application to do interence on new events.

**Machine Learning Technology**
* H2O (www.h2o.ai)
* Check the H2O demo to understand the test and and how the model was built
* https://github.com/h2oai/h2o-2/wiki/Hacking-Airline-DataSet-with-H2O
* You can re-use the generated Java model attached to this project ([gbm_pojo_test.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/main/java/com/github/megachucky/kafka/streams/machinelearning/models/gbm_pojo_test.java)) or build your own model using R, Python, Flow UI or any other technologies supported by H2O framework.

**Source Code**

[MachineLearning_H2O_Example.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/main/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_MachineLearning_H2O_GBM_Example.java)

**Unit Test**

[MachineLearning_H2O_Example_IntegrationTest.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/test/java/com/github/megachucky/kafka/streams/machinelearning/test/Kafka_Streams_MachineLearning_H2O_GBM_Example_IntegrationTest.java)
