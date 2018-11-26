package com.github.megachucky.kafka.streams.machinelearning.models;

import java.io.File;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.io.ClassPathResource;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;

import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeepLearning4J_CSV_Iris_Model {

    private static Logger log = LoggerFactory.getLogger(DeepLearning4J_CSV_Iris_Model.class);

    public static void main(String[] args) throws Exception {

        // First: get the dataset using the record reader. CSVRecordReader handles
        // loading/parsing
        int numLinesToSkip = 0;
        char delimiter = ',';
        RecordReader recordReader = new CSVRecordReader(numLinesToSkip, delimiter);
        recordReader.initialize(new FileSplit(new ClassPathResource("DL4J_Resources/iris.txt").getFile()));

        // Second: the RecordReaderDataSetIterator handles conversion to DataSet
        // objects, ready for use in neural network
        int labelIndex = 4; // 5 values in each row of the iris.txt CSV: 4 input features followed by an
                            // integer label (class) index. Labels are the 5th value (index 4) in each row
        int numClasses = 3; // 3 classes (types of iris flowers) in the iris data set. Classes have integer
                            // values 0, 1 or 2
        int batchSize = 150; // Iris data set: 150 examples total. We are loading all of them into one
                             // DataSet (not recommended for large data sets)

        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);
        DataSet allData = iterator.next();
        allData.shuffle();
        SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65); // Use 65% of data for training

        DataSet trainingData = testAndTrain.getTrain();
        DataSet testData = testAndTrain.getTest();

        // We need to normalize our data. We'll use NormalizeStandardize (which gives us
        // mean 0, unit variance):
        DataNormalization normalizer = new NormalizerStandardize();
        normalizer.fit(trainingData); // Collect the statistics (mean/stdev) from the training data. This does not
                                      // modify the input data
        normalizer.transform(trainingData); // Apply normalization to the training data
        normalizer.transform(testData); // Apply normalization to the test data. This is using statistics calculated
                                        // from the *training* set

        final int numInputs = 4;
        int outputNum = 3;
        long seed = 6;

        log.info("Build model....");
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder().seed(seed).activation(Activation.TANH)
                .weightInit(WeightInit.XAVIER).updater(new Sgd(0.1)).l2(1e-4).list()
                .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(3).build())
                .layer(1, new DenseLayer.Builder().nIn(3).nOut(3).build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX).nIn(3).nOut(outputNum).build())
                .build();

        // run the model
        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(100));

        for (int i = 0; i < 1000; i++) {
            model.fit(trainingData);
        }

        // evaluate the model on the test set
        Evaluation eval = new Evaluation(3);
        INDArray input = testData.getFeatures();
        INDArray output = model.output(input);
        System.out.println("INPUT:" + input.toString());
        eval.eval(testData.getLabels(), output);
        log.info(eval.stats());

        // Save the model
        File locationToSave = new File("src/main/resources/generatedModels/DL4J/DL4J_Iris_Model.zip"); // Where to save
        // the network.
        // Note: the file
        // is in .zip
        // format - can
        // be opened
        // externally
        boolean saveUpdater = true; // Updater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this if you
        // want to train your network more in the future
        // ModelSerializer.writeModel(model, locationToSave, saveUpdater);

        // Load the model
        MultiLayerNetwork restored = ModelSerializer.restoreMultiLayerNetwork(locationToSave);

        System.out.println("Saved and loaded parameters are equal:      " + model.params().equals(restored.params()));
        System.out.println("Saved and loaded configurations are equal:  "
                + model.getLayerWiseConfigurations().equals(restored.getLayerWiseConfigurations()));

    }

}