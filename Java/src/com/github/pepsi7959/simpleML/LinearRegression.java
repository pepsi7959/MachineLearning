package com.github.pepsi7959.simpleML;

import java.util.Iterator;
import java.util.LinkedList;

public class LinearRegression {
	private LinkedList<Input> inputs;
	private Matrix weight;
	private LinkedList<Double> expectedValue;
	
	public LinearRegression(LinkedList<Input> input, Matrix weight, LinkedList<Double> expectedValue) {
		this.inputs = input;
		this.weight = weight;
		this.expectedValue = expectedValue;
	}
	
	public Matrix train() {
		Iterator<Input> in = this.inputs.iterator();
		Matrix td = new Matrix(inputs.size(),1);
		double err = 0.0;
		double result = 0.0;
		int i = 0;
		while( in.hasNext() ) {
			//cost function
			//Y = WX
			result = Matrix.multiply(in.next(), this.weight);
			err = result - this.expectedValue.get(i);
			td.setData(i, 0,  err);
			i++;
		}
		return td;
	}
}
