package com.github.megachucky.kafka.streams.machinelearning.models;

import java.io.File;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;

import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Adam Gibson
 */
public class DeepLearning4J_CSV_Model_Inference {

    private static Logger log = LoggerFactory.getLogger(DeepLearning4J_CSV_Model_Inference.class);

    public static void main(String[] args) throws  Exception {
        
        //Save the model
        File locationToSave = new File("src/main/resources/generatedModels/DL4J/DL4J_Iris_Model.zip");      //Where to save the network. Note: the file is in .zip format - can be opened externally


        //Load the model
        MultiLayerNetwork model = ModelSerializer.restoreMultiLayerNetwork(locationToSave);


        	// Inference
        	INDArray input = Nd4j.create(new double[] {5.0,3.5,1.6,0.6});
        	INDArray result = model.output(input);

            System.out.println("Probabilities: " + result.toString());

    }

}

