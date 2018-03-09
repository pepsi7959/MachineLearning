package com.github.pepsi7959.simpleML;

import java.util.LinkedList;

import com.github.pepsi7959.SupervisedLearning.LinearRegression;
import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;

public class TestLinearRegression {
	public static void main(String []args) {
		
		LinkedList<Dataset> datasets = Dataset.fromFile("src\\com\\github\\pepsi7959\\SupervisedLearning\\inputs.csv");
		LinkedList<Double> ev = Dataset.expectedValueFromFile("src\\com\\github\\pepsi7959\\SupervisedLearning\\ExpectedValue.csv");
		Matrix weight = new Matrix(datasets.getFirst().getCol(), 1);
		weight.random(0, 5);
		
		LinearRegression LR = new LinearRegression(datasets, ev, 0.0001, 1000000, weight);
		LR.train();
		LR.test(datasets, ev);
	}
}
