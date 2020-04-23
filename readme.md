# Machine Learning + Kafka Streams Examples

This project contains **examples which demonstrate how to deploy analytic models to mission-critical, scalable production leveraging [Apache Kafka](https://kafka.apache.org/) and its [Streams API](https://docs.confluent.io/current/streams/index.html).**
Examples will include analytic models built with TensorFlow, Keras, H2O, Python, DeepLearning4J and other technologies.

![Kafka Open Source Ecosystem for a Scalable Mission Critical Machine Learning Infrastructure](http://www.kai-waehner.de/blog/wp-content/uploads/2017/10/Apache_Kafka_Ecosystem_Kafka_Streams_Machine_Learning.png "Kafka Open Source Ecosystem for a Scalable Mission Critical Machine Learning Infrastructure")

## Material (Blogs Posts, Slides, Videos)

Here is some material about this topic if you want to read and listen to the theory instead of just doing hands-on:

- Blog Post: [How to Build and Deploy Scalable Machine Learning in Production with Apache Kafka](https://www.confluent.io/blog/build-deploy-scalable-machine-learning-production-apache-kafka/)
- Slide Deck: [Apache Kafka + Machine Learning => Intelligent Real Time Applications](https://www.slideshare.net/KaiWaehner/apache-kafka-streams-machine-learning-deep-learning)
- Slide Deck: [Deep Learning at Extreme Scale (in the Cloud)  with the Apache Kafka Open Source Ecosystem](https://www.slideshare.net/KaiWaehner/deep-learning-at-extreme-scale-in-the-cloud-with-the-apache-kafka-open-source-ecosystem)
- Video Recording: [Deep Learning in Mission Critical and Scalable Real Time Applications with Open Source Frameworks](https://vimeo.com/jaxtv/review/256406763/7fbf4213be)
- Blog Post: [Using Apache Kafka to Drive Cutting-Edge Machine Learning - Hybrid ML Architectures, AutoML, and more...](https://www.confluent.io/blog/using-apache-kafka-drive-cutting-edge-machine-learning)
- Blog Post: [Machine Learning with Python, Jupyter, KSQL and TensorFlow](https://www.confluent.io/blog/machine-learning-with-python-jupyter-ksql-tensorflow)
- Blog Post: [Streaming Machine Learning with Tiered Storage and Without a Data Lake](https://www.confluent.io/blog/streaming-machine-learning-with-tiered-storage/)

## Use Cases and Technologies

##### The following examples are already available including unit tests:

* Deployment of a H2O GBM model to a Kafka Streams application for prediction of flight delays
* Deployment of a H2O Deep Learning model to a Kafka Streams application for prediction of flight delays
* Deployment of a pre-built TensorFlow CNN model for image recognition
* Deployment of a DL4J model to predict the species of Iris flowers
* Deployment of a Keras model (trained with TensorFlow backend) using the Import Model API from DeepLearning4J

**More sophisticated use cases around Kafka Streams and other technologies will be added over time in this or related Github project. Some ideas**:

* Image Recognition with H2O and TensorFlow (to show the difference of using H2O instead of using just low level TensorFlow APIs)
* Anomaly Detection with Autoencoders leveraging DeepLearning4J.
* Cross Selling and Customer Churn Detection using classical Machine Learning algorithms but also Deep Learning
* Stateful Stream Processing to combine different model execution steps into a more powerful workflow instead of "just" inferencing single events (a good example might be a streaming process with sliding or session windows).
* Keras to build different models with Python, TensorFlow, Theano and other Deep Learning frameworks under the hood + Kafka Streams as generic Machine Learning infrastructure to deploy, execute and monitor these different models.

##### Some other Github projects exist already with more ML + Kafka content:

The most exciting and powerful example first:
[Streaming Machine Learning at Scale from 100000 IoT Devices with HiveMQ, Apache Kafka and TensorFLow](https://github.com/kaiwaehner/hivemq-mqtt-tensorflow-kafka-realtime-iot-machine-learning-training-inference)

Here some more demos:

- Deep Learning UDF for KSQL: [Streaming Anomaly Detection of MQTT IoT Sensor Data using an Autoencoder](https://github.com/kaiwaehner/ksql-udf-deep-learning-mqtt-iot)
- End-to-End ML Integration Demo: [Continuous Health Checks with Anomaly Detection using KSQL, Kafka Connect, Deep Learning and Elasticsearch](https://github.com/kaiwaehner/ksql-fork-with-deep-learning-function)
- TensorFlow Serving + gRPC + Kafka Streams on Github => Stream Processing and RPC / Request-Response concepts combined: [Model inference with Apache Kafka, Kafka Streams and a TensorFlow model deployed on a TensorFlow Serving model server](https://github.com/kaiwaehner/tensorflow-serving-java-grpc-kafka-streams)
- Solving the impedance mismatch between Data Scientist and Production Engineer: [Python, Jupyter, TensorFlow, Keras, Apache Kafka, KSQL](https://github.com/kaiwaehner/python-jupyter-apache-kafka-ksql-tensorflow-keras)

## Requirements, Installation and Usage
The code is developed and tested on Mac and Linux operating systems. As Kafka does not support and work well on Windows, this is not tested at all.

Java 8 and Maven 3 are required. Maven will download all required dependencies.

Just download the project and run

                mvn clean package

You can do this in main directory or each module separately.

Apache Kafka 2.5 is currently used. The code is also compatible with Kafka and Kafka Streams 1.1 and 2.x.

**Please make sure to run the Maven build without any changes first.** If it works without errors, you can change library versions, Java version, etc. and see if it still works or if you need to adjust code. 

Every examples includes an implementation and an unit test. The examples are very simple and lightweight. No further configuration is needed to build and run it. Though, for this reason, the generated models are also included (and increase the download size of the project).

The unit tests use some Kafka helper classes like EmbeddedSingleNodeKafkaCluster in package **com.github.megachucky.kafka.streams.machinelearning.test.utils** so that you can run it without any other configuration or Kafka setup. 
If you want to run an implementation of a main class in package **com.github.megachucky.kafka.streams.machinelearning**, you need to start a Kafka cluster (with at least one Zookeeper and one Kafka broker running) and also create the required topics. So check out the unit tests first.


### Example 1 - Gradient Boosting with H2O.ai for Prediction of Flight Delays

Detailed info in [h2o-gbm](h2o-gbm/readme.md)

### Example 2 - Convolutional Neural Network (CNN) with TensorFlow for Image Recognition

Detailed info in [tensorflow-image-recognition](tensorflow-image-recognition/readme.md)

### Example 3 - Iris Prediction using a Neural Network with DeepLearning4J (DL4J)

Detailed info in [dl4j-deeplearning-iris](dl4j-deeplearning-iris/readme.md)

### Example 4 - Python + Keras + TensorFlow + DeepLearning4j

Detailed info in [tensorflow-kerasm](tensorflow-keras/readme.md)





