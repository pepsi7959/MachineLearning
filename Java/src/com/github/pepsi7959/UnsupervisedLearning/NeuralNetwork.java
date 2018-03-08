package com.github.pepsi7959.UnsupervisedLearning;

import java.util.Iterator;
import java.util.LinkedList;

import com.github.pepsi7959.simpleML.ActivationFunction;
import com.github.pepsi7959.simpleML.Input;
import com.github.pepsi7959.simpleML.Matrix;

public class NeuralNetwork {
	private LinkedList<Input> inputs;
	private LinkedList<Double> ev;
	private Matrix weight;
	private double step;
	private double lr;

	public NeuralNetwork(LinkedList<Input> inputs, LinkedList<Double> ev, Matrix weight) {
		this.inputs = inputs;
		this.ev = ev;
		this.weight = weight;
		initialize();
	}

	public void initialize() {
		this.step = 1000;
		this.lr = 0.0001;
	}

	public LinkedList<Input> sample() {
		return null;
	}

	public double costFunc(Input input, Matrix w) {
		return Matrix.dot(w, input);
	}

	public void autoTrain() {
		for (int i = 0; i < this.step; i++) {

		}
	}

	public Matrix train() {
		Matrix td = new Matrix(this.inputs.size());
		Iterator<Input> input = this.inputs.iterator();
		int row = 0;
		while (input.hasNext()) {
			Input in = (Input) input.next();
			double costValue = costFunc(in, this.weight);
			td.setData(row, 0, costValue);
			double af = ActivationFunction.sinusoid(costValue);
			System.out.println("input " + in.getData(1, 0) + " output : " + costValue + " activated : " + af);
			row++;
		}
		return td;
	}
}
