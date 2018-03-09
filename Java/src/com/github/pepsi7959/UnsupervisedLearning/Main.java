package com.github.pepsi7959.UnsupervisedLearning;

import java.util.LinkedList;

import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;

public class Main {

	public static void main(String[] args) {
		
		LinkedList<Dataset> datasets = Dataset.fromFile("src\\UnsupervisedLearning\\inputs.csv");
		Matrix weight = new Matrix(datasets.getFirst().getRow(), 1);
		weight.random(0, 5);
		
		NeuralNetwork nn = new NeuralNetwork(datasets, null, weight);
		nn.train();
	}

}
