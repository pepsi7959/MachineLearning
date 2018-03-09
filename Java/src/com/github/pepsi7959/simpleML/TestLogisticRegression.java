package com.github.pepsi7959.simpleML;

import java.util.LinkedList;

import com.github.pepsi7959.SupervisedLearning.LogisticRegression;
import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;

public class TestLogisticRegression {
	public static void main(String[] args) {
		/* Prepare parameter */
		double learningRate = 0.001;
		int numOfStep = 1000000;

		/* Read dataset from inputs.csv file */
		LinkedList<Dataset> datasets = Dataset
				.fromFile("src\\com\\github\\pepsi7959\\UnsupervisedLearning\\inputs.csv");

		/* Read expected value from ExpectedValue.csv */
		LinkedList<Double> ev = Dataset
				.expectedValueFromFile("src\\com\\github\\pepsi7959\\UnsupervisedLearning\\ExpectedValue.csv");

		/* Initialize coefficient (weight) and random value from 0 to 5 */
		Matrix weight = new Matrix(datasets.getFirst().getCol(), 1);
		weight.random(0, 5);

		/* Create Logistic Regression object */
		LogisticRegression LR = new LogisticRegression(datasets, ev, learningRate, numOfStep, weight);

		/* Train model until it reach number of step */
		LR.train();

		/*
		 * Test model by using the inputs, but we recommend you should have dataset for
		 * testing the model particularly
		 */
		LR.Test(datasets, ev);
	}

}
