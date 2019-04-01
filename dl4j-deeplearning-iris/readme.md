# Machine Learning + Kafka Streams Examples

General info in main [Readme](../readme.md) 

### Example 3 - Iris Prediction using a Neural Network with DeepLearning4J (DL4J)
**Use Case**

Iris Species Prediction using a Neural Network.
This is a famous example: Prediction of the Iris Species - implemented with many different ML algorithms. Here I use DeepLearning4J (DL4J) to build a neural network using Iris Dataset.

**Machine Learning Technology**
* [DeepLearning4J](https://deeplearning4j.org)
* Pretty simple example to demo how to build, save and load neural networks with DL4J. [MultiLayerNetwork](https://deeplearning4j.org/doc/org/deeplearning4j/nn/multilayer/MultiLayerNetwork.html) and [INDArray](http://nd4j.org/doc/org/nd4j/linalg/api/ndarray/INDArray.html) are the key APIs to look at if you want to understand the details.
* The model is created via [DeepLearning4J_CSV_Model.java](src/main/java/com/github/megachucky/kafka/streams/machinelearning/models/DeepLearning4J_CSV_Model.java) and stored in the resources: [DL4J_Iris_Model.zip](https://github.com/kaiwaehner/kafka-streams-machine-learning-examples/tree/master/src/main/resources/generatedModels/DL4J). No need to re-train, just for reference. Kudos to Adam Gibson who created this example as part of the DL4J project.

**Unit Test**
[Kafka_Streams_MachineLearning_DL4J_DeepLearning_Iris_IntegrationTest.java](src/test/java/com/github/megachucky/kafka/streams/machinelearning/test/Kafka_Streams_MachineLearning_DL4J_DeepLearning_Iris_IntegrationTest.java)

