# Machine Learning + Kafka Streams Examples

General info in main [Readme](../readme.md) 

### Example 4 - Python + Keras + TensorFlow + DeepLearning4j

**Use Case**

Development of an analytic model trained with Python, Keras and TensorFlow and deployment to Java and Kafka ecosystem. No business case, just a technical demonstration of a simple 'Hello World' Keras model. Feel free to replace the model with any other Keras model trained with your backend of choice. You just need to replace the model binary (and use a model which is compatible with DeepLearning4J 's model importer).

**Machine Learning Technology**
* [Python](https://www.python.org/) 
* [DeepLearning4J](https://deeplearning4j.org)
* [Keras](https://keras.io/) - a high-level neural networks API, written in Python and capable of running on top of TensorFlow, CNTK, or Theano.
* [TensorFlow](https://www.tensorflow.org/) - used as backend under the hood of Keras 
* DeepLearning4J 's [KerasModelImport feature](https://deeplearning4j.org/docs/latest/keras-import-overview)  is used for importing the Keras / TensorFlow model into Java. The used model is its 'Hello World' model example. 
* The Keras model was trained with this [Python script](src/main/resources/generatedModels/Keras/keras-model-script.py).

**Unit Test**

[Kafka_Streams_TensorFlow_Keras_Example_IntegrationTest.java](src/test/java/com/github/megachucky/kafka/streams/machinelearning/test/Kafka_Streams_TensorFlow_Keras_Example_IntegrationTest.java)







