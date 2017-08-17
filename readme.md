# Machine Learning + Kafka Streams Examples

This project includes **working examples which demonstrate how to deploy analytic models to mission-critical, scalable production leveraging Apache Kafka's Streams API.**
Examples will include analytic models built with TensorFlow, Keras, H2O, Python, DeepLearning4J and other technologies.

## Installation and Usage
Just download the project and run 'mvn clean package'.

Every examples includes a implementation and a unit test. The project and examples are very simple and lightweight. No further configuration is needed to build and run it.

Java 8 is required on your environment. Maven will download all required dependencies.

## Use Cases and Technologies
This project kicks off with an example of using Kafka Streams to deploy a H2O GBM model. More sophisticated use cases and other technologies will be added over time.

### Example 1 - H2O GBM for Prediction of Flight Delays
Use Case: Gradient Boosting Method (GBM) to predict flight delays:
Technology: H2O generated Java model + Kafka Streams

Code: [MachineLearning_H2O_Example.java](https://github.com/megachucky/kafka-streams-machine-learning-examples/blob/master/src/main/java/com/github/megachucky/kafka/streams/machinelearning/MachineLearning_H2O_Example.java)

Unit Test: [MachineLearning_H2O_Example_IntegrationTest.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/test/java/com/github/megachucky/kafka/streams/machinelearning/test/MachineLearning_H2O_Example_IntegrationTest.java)
