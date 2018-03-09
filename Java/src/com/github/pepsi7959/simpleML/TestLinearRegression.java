package com.github.pepsi7959.simpleML;

import java.util.LinkedList;

import com.github.pepsi7959.SupervisedLearning.LinearRegression;
import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;

public class TestLinearRegression {
	public static void main(String[] args) {
		/* Prepare parameter */
		double learningRate = 0.0001;
		int numOfStep = 10000;

		/* Read dataset from inputs.csv file */
		LinkedList<Dataset> datasets = Dataset.fromFile("src\\com\\github\\pepsi7959\\simpleML\\linear-regression-inputs.csv");

		/* Read expected value from ExpectedValue.csv */
		LinkedList<Double> ev = Dataset
				.expectedValueFromFile("src\\com\\github\\pepsi7959\\simpleML\\linear-regression-expectedValue.csv");

		/* Initialize coefficient (weight) and random value from 0 to 5 */
		Matrix weight = new Matrix(datasets.getFirst().getCol(), 1);
		weight.random(0, 5);

		/* Create Linear Regression object */
		LinearRegression LR = new LinearRegression(datasets, ev, learningRate, numOfStep, weight);

		/* Train model until it reach number of step */
		LR.train();

		/*
		 * Test model by using the inputs, but we recommend you should have dataset for
		 * testing the model particularly
		 */
		LR.test(datasets, ev);
	}
}
