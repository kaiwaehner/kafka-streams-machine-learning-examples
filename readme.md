# Machine Learning + Kafka Streams Examples

This project contains **examples which demonstrate how to deploy analytic models to mission-critical, scalable production leveraging [Apache Kafka](https://kafka.apache.org/) and its [Streams API](docs.confluent.io/current/streams/index.html).**
Examples will include analytic models built with TensorFlow, Keras, H2O, Python, DeepLearning4J and other technologies.

## Installation and Usage
Java 8 and Maven 3 are required. Maven will download all required dependencies.

Just download the project and run 'mvn clean package'.

Every examples includes an implementation and an unit test. The examples are very simple and lightweight. No further configuration is needed to build and run it. Though, for this reason, the generated models are also included (and increase the download size of the project).

The unit tests use some Kafka helper classes like EmbeddedSingleNodeKafkaCluster in package "com.github.megachucky.kafka.streams.machinelearning.test.utils". If you want to run an implementation of a main class, you need to start a Kafka cluster (with at least one Zookeeper and one Kafka broker running) and also create the required topics.

## Use Cases and Technologies
The following examples are already available including unit tests:
* Deployment of a H2O GBM model to a Kafka Streams application for prediction of flight delays
* Deployment of a H2O Deep Learning model to a Kafka Streams application for prediction of flight delays
* Deployment of a pre-built TensorFlow CNN model for image recognition

I also added a class for training a [DeepLearning4J](https://deeplearning4j.org/) model. Implementation of the Java Class is still TODO.

More sophisticated use cases around Kafka Streams and other technologies will be added over time. Some ideas:
* Image Recognition with H2O and TensorFlow (to show the difference of using H2O instead of using just low level TensorFlow APIs)
* Anomaly Detection with Autoencoders leveraging DeepLearning4J.
* Cross Selling and Customer Churn Detection using classical Machine Learning algorithms but also Deep Learning
* Stateful Stream Processing to combine different model execution steps into a more powerful workflow instead of "just" inferencing single events (a good example might be a streaming process with sliding or session windows).
* Keras to build different models with Python, TensorFlow, Theano and other Deep Learning frameworks under the hood + Kafka Streams as generic Machine Learning infrastructure to deploy, execute and monitor these different models.

### Example 1 - Gradient Boosting with H2O.ai for Prediction of Flight Delays
**Use Case**

Gradient Boosting Method (GBM) to predict flight delays.
A H2O generated GBM Java model (POJO) is instantiated and used in a Kafka Streams application to do interference on new events.

**Machine Learning Technology**
* [H2O](https://www.h2o.ai)
* Check the [H2O demo](https://github.com/h2oai/h2o-2/wiki/Hacking-Airline-DataSet-with-H2O) to understand the test and and how the model was built
* You can re-use the generated Java model attached to this project ([gbm_pojo_test.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/main/java/com/github/megachucky/kafka/streams/machinelearning/models/gbm_pojo_test.java)) or build your own model using R, Python, Flow UI or any other technologies supported by H2O framework.

**Source Code**

[MachineLearning_H2O_Example.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/main/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_MachineLearning_H2O_GBM_Example.java)

**Unit Test**

[MachineLearning_H2O_Example_IntegrationTest.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/test/java/com/github/megachucky/kafka/streams/machinelearning/test/Kafka_Streams_MachineLearning_H2O_GBM_Example_IntegrationTest.java)

The project includes another example with similar code to use a [H2O Deep Learning model](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/main/java/com/github/megachucky/kafka/streams/machinelearning/models/deeplearning_fe7c1f02_08ec_4070_b784_c2531147e451.java) instead of H2O GBM Model: [Kafka_Streams_MachineLearning_H2O_DeepLearning_Example_IntegrationTest.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/test/java/com/github/megachucky/kafka/streams/machinelearning/test/Kafka_Streams_MachineLearning_H2O_DeepLearning_Example_IntegrationTest.java)
This shows how you can easily test or replace different analytic models for one use case, or even use them for A/B testing.

### Example 2 - Convolutional Neural Network (CNN) with TensorFlow for Image Recognition
**Use Case**

Convolutional Neural Network (CNN) to for image recognition.
A prebuilt TensorFlow CNN model is instantiated and used in a Kafka Streams application to do recognize new JPEG images. A Kafka Input Topic receives the location of a new images (another option would be to send the image in the Kafka message instead of just a link to it), infers the content of the picture via the TensorFlow model, and sends the result to a Kafka Output Topic.

**Machine Learning Technology**
* [TensorFlow](https://www.tensorflow.org/)
* Leverages [TensorFlow for Java](https://www.tensorflow.org/install/install_java). These APIs are particularly well-suited for loading models created in Python and executing them within a Java application. Please note: The Java API doesn't yet include convenience functions (which you might know from Keras), thus a private helper class is used in the example for construction and execution of the pre-built TensorFlow model.
* Check the official TensorFlow demo [LabelImage](https://github.com/kaiwaehner/tensorflow/blob/r1.3/tensorflow/java/src/main/java/org/tensorflow/examples/LabelImage.java) to understand this image recognition example
* You can re-use the pre-trained TensorFlow model attached to this project [tensorflow_inception_graph.pb](http://arxiv.org/abs/1512.00567) or add your own model.
* The 'images' folder contains models which were used for training the model (trained_airplane_1.jpg, trained_airplane_2.jpg, trained_butterfly.jpg) but also a new picture (new_airplane.jpg) which is not known by the model and using a different resolution than the others. Feel free to add your own pictures (they need to be trained, see list of trained pictures in the file: imagenet_comp_graph_label_strings.txt), otherwise the model will return 'unknown'.

**Source Code**

[Kafka_Streams_TensorFlow_Image_Recognition_Example.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/main/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_TensorFlow_Image_Recognition_Example.java)

**Unit Test**

[Kafka_Streams_TensorFlow_Image_Recognition_Example_IntegrationTest.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/test/java/com/github/megachucky/kafka/streams/machinelearning/test/Kafka_Streams_TensorFlow_Image_Recognition_Example_IntegrationTest.java)
