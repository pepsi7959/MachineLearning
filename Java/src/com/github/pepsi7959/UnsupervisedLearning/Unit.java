package com.github.pepsi7959.UnsupervisedLearning;

import com.github.pepsi7959.model.Matrix;
import com.github.pepsi7959.optimization.ActivationFunction;

public class Unit {
	
	Matrix weight;
	double output = 0.0;
	int numOfInput = 1;
	
	public Unit(int numOfInput) {
		this.numOfInput = numOfInput;
		initialize();
	}
	
	public void initialize() {
		this.weight = new Matrix(numOfInput, 1);
		weight.random(0, 5);
	}
	
	// TODO: The same layer must use the same hypothesis.
	public double hypothesis(Matrix input) {
		return Matrix.multiply(input, this.weight);
	}
	
	// Use sinusoid as activation function.
	public double activatedFunc(double value) {
		return this.output = ActivationFunction.sinusoid(value);
	}
	
}
