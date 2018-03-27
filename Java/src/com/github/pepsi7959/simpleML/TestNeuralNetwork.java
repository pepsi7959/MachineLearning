package com.github.pepsi7959.simpleML;

import java.util.LinkedList;

import com.github.pepsi7959.UnsupervisedLearning.Layer;
import com.github.pepsi7959.UnsupervisedLearning.NeuralNetwork;
import com.github.pepsi7959.model.Dataset;

public class TestNeuralNetwork {

	public static void main(String[] args) {

		LinkedList<Dataset> inputs = Dataset.fromFile("src\\com\\github\\pepsi7959\\simpleML\\nn-inputs.csv");
		LinkedList<Double> ev = Dataset
				.expectedValueFromFile("src\\com\\github\\pepsi7959\\simpleML\\nn-expectedValue.csv");

		int numOfInput = inputs.getFirst().getCol();
		NeuralNetwork nn = new NeuralNetwork(inputs, ev, 2);

		// Design your own hidden layer

		// Layer 1
		Layer L1 = new Layer(numOfInput, 2);
		nn.addNewLayer(L1);

		// Layer 2
		Layer L2 = new Layer(numOfInput, 2);
		nn.addNewLayer(L2);

		// Layer 3
		Layer L3 = new Layer(numOfInput, 1);
		nn.addNewLayer(L3);

		nn.train();
	}

}
