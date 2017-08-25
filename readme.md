# Machine Learning + Kafka Streams Examples

This project contains **examples which demonstrate how to deploy analytic models to mission-critical, scalable production leveraging Apache Kafka's Streams API.**
Examples will include analytic models built with TensorFlow, Keras, H2O, Python, DeepLearning4J and other technologies.

## Installation and Usage
Java 8 and Maven 3 are required. Maven will download all required dependencies.

Just download the project and run 'mvn clean package'.

Every examples includes a implementation and a unit test. The project and examples are very simple and lightweight. No further configuration is needed to build and run it.

The unit test use some helper classes like EmbeddedSingleNodeKafkaCluster in package "com.github.megachucky.kafka.streams.machinelearning.test.utils". If you want to run the main classes, you need to start
a Kafka cluster (with at least one Zookeeper and one Kafka broker running) and create the required topics.

## Use Cases and Technologies
This examples are already available including unit tests:
* Deployment of a H2O GBM model to a Kafka Streams application for prediction of flight delay
* Deployment of a pre-built TensorFlow CNN model for image recognition.

More sophisticated use cases and other technologies will be added over time. The following will be added as soon as I find some more spare time (all examples will be built into a Kafka Streams environment ):
* Anomaly detection using Autoencoders leveraging DeepLearning4J.
* Image Recognition using H2O (to show the difference of H2O instead of using just low level TensorFlow APIs)
* Cross Selling and Customer Churn Detection using classical Machine Learning algorithms but also Deep Learning
* Stateful stream processing to combine different model execution steps into an even more powerful workflow than just inferencing single events (e.g. using sliding or session windows).
* Keras to build different models with Python, TensorFlow, Theano and other Deep Learning frameworks under the hood + Kafka Streams as generic Machine Learning infrastructure to deploy, execute and monitor these different models.

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

### Example 2 - Convolutional Neural Network (CNN) with TensorFlow for Image Recognition
**Use Case**

Convolutional Neural Network (CNN) to for image recognition.
A prebuilt TensorFlow CNN model is instantiated and used in a Kafka Streams application to do recognize new JPEG images. A Kafka Input Topic receives the location of a new images (another option would be to send the image in the Kafka message instead of just a link to it), infers the content of the picture via the TensorFlow model, and sends the result to a Kafka Output Topic.

**Machine Learning Technology**
* TensorFlow (TODO Link)
* Leverages TensorFlow for Java (https://www.tensorflow.org/install/install_java). These APIs are particularly well-suited to loading models created in Python and executing them within a Java application. The Java API doesn't yet include convenience functions (which you might know from Keras), thus a private helper class is used in the example for construction and execution of the pre-built TensorFlow model.
* Check the official TensorFlow demo 'LabelImage' to understand this image recognition example
* https://github.com/kaiwaehner/tensorflow/blob/r1.3/tensorflow/java/src/main/java/org/tensorflow/examples/LabelImage.java
* You can re-use the pre-trained TensorFlow model attached to this project (tensorflow_inception_graph.pb,  more details here: http://arxiv.org/abs/1512.00567)) or add your own model.
* The 'images' folder contains models which were used for training the model (trained_airplane_1.jpg, trained_airplane_2.jpg, trained_butterfly.jpg) but also a new picture (new_airplane.jpg) which is not known by the model and using a different resolution than the others. Feel free to add your own pictures (they need to be trained, see list of trained pictures in the file: imagenet_comp_graph_label_strings.txt), otherwise the model will return 'unknown'.

**Source Code**

[Kafka_Streams_TensorFlow_Image_Recognition_Example.java](TODO)

**Unit Test**

[Kafka_Streams_TensorFlow_Image_Recognition_Example_IntegrationTest.java](TODO)
