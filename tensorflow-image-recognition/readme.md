# Machine Learning + Kafka Streams Examples

General info in main [Readme](../readme.md) 

### Example 2 - Convolutional Neural Network (CNN) with TensorFlow for Image Recognition
**Use Case**

Convolutional Neural Network (CNN) to for image recognition.
A prebuilt TensorFlow CNN model is instantiated and used in a Kafka Streams application to do recognize new JPEG images. A Kafka Input Topic receives the location of a new images (another option would be to send the image in the Kafka message instead of just a link to it), infers the content of the picture via the TensorFlow model, and sends the result to a Kafka Output Topic.

**Machine Learning Technology**
* [TensorFlow](https://www.tensorflow.org/)
* Leverages [TensorFlow for Java](https://www.tensorflow.org/install/install_java). These APIs are particularly well-suited for loading models created in Python and executing them within a Java application. Please note: The Java API doesn't yet include convenience functions (which you might know from [Keras](https://keras.io/)), thus a private helper class is used in the example for construction and execution of the pre-built TensorFlow model.
* Check the official TensorFlow demo [LabelImage](https://github.com/kaiwaehner/tensorflow/blob/r1.3/tensorflow/java/src/main/java/org/tensorflow/examples/LabelImage.java) to understand this image recognition example
* You can re-use the pre-trained TensorFlow model attached to this project [tensorflow_inception_graph.pb](http://arxiv.org/abs/1512.00567) or add your own model.
* The 'images' folder contains models which were used for training the model (trained_airplane_1.jpg, trained_airplane_2.jpg, trained_butterfly.jpg) but also a new picture (new_airplane.jpg) which is not known by the model and using a different resolution than the others. Feel free to add your own pictures (they need to be trained, see list of trained pictures in the file: imagenet_comp_graph_label_strings.txt), otherwise the model will return 'unknown'.

**Source Code**

[Kafka_Streams_TensorFlow_Image_Recognition_Example.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/main/java/com/github/megachucky/kafka/streams/machinelearning/Kafka_Streams_TensorFlow_Image_Recognition_Example.java)

**Unit Test**

[Kafka_Streams_TensorFlow_Image_Recognition_Example_IntegrationTest.java](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/blob/master/src/test/java/com/github/megachucky/kafka/streams/machinelearning/test/Kafka_Streams_TensorFlow_Image_Recognition_Example_IntegrationTest.java)

