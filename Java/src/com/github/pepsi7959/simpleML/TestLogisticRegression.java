package com.github.pepsi7959.simpleML;

import java.util.LinkedList;

import com.github.pepsi7959.SupervisedLearning.LogisticRegression;
import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;

public class TestLogisticRegression {
	public static void main(String []args) {
		
		LinkedList<Dataset> datasets = Dataset.fromFile("src\\com\\github\\pepsi7959\\UnsupervisedLearning\\inputs.csv");
		LinkedList<Double> ev = Dataset.expectedValueFromFile("src\\com\\github\\pepsi7959\\UnsupervisedLearning\\ExpectedValue.csv");
		Matrix weight = new Matrix(datasets.getFirst().getCol(), 1);
		weight.random(0, 5);
		
		LogisticRegression LR = new LogisticRegression(datasets, ev, 0.001, 1000000, weight);
		LR.train();
		LR.Test(datasets, ev);
	}
	
	
}
