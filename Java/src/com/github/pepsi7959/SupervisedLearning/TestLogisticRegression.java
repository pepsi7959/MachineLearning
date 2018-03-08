package com.github.pepsi7959.SupervisedLearning;

import java.util.LinkedList;

import com.github.pepsi7959.simpleML.Input;
import com.github.pepsi7959.simpleML.Matrix;

public class TestLogisticRegression {
	public static void main(String []args) {
		
		LinkedList<Input> inputs = Input.fromFile("src\\com\\github\\pepsi7959\\UnsupervisedLearning\\inputs.csv");
		LinkedList<Double> ev = Input.expectedValueFromFile("src\\com\\github\\pepsi7959\\UnsupervisedLearning\\ExpectedValue.csv");
		Matrix weight = new Matrix(inputs.getFirst().getCol(), 1);
		weight.random(0, 5);
		
		LogisticRegression LR = new LogisticRegression(inputs, ev, 0.001, 1000000, weight);
		LR.train();
		LR.Test(inputs, ev);
	}
	
	
}
