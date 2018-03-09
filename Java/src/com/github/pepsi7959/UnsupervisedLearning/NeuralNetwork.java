package com.github.pepsi7959.UnsupervisedLearning;

import java.util.Iterator;
import java.util.LinkedList;

import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;
import com.github.pepsi7959.optimization.ActivationFunction;

public class NeuralNetwork {
	private LinkedList<Dataset> datasets;
	private LinkedList<Double> ev;
	private Matrix weight;
	private double step;
	private double lr;

	public NeuralNetwork(LinkedList<Dataset> datasets, LinkedList<Double> ev, Matrix weight) {
		this.datasets = datasets;
		this.ev = ev;
		this.weight = weight;
		initialize();
	}

	public void initialize() {
		this.step = 1000;
		this.lr = 0.0001;
	}

	public LinkedList<Dataset> sample() {
		return null;
	}

	public double costFunc(Dataset dataset, Matrix w) {
		return Matrix.dot(w, dataset);
	}

	public void autoTrain() {
		for (int i = 0; i < this.step; i++) {

		}
	}

	public Matrix train() {
		Matrix td = new Matrix(this.datasets.size());
		Iterator<Dataset> dataset = this.datasets.iterator();
		int row = 0;
		while (dataset.hasNext()) {
			Dataset in = (Dataset) dataset.next();
			double costValue = costFunc(in, this.weight);
			td.setData(row, 0, costValue);
			double af = ActivationFunction.sinusoid(costValue);
			System.out.println("input " + in.getData(1, 0) + " output : " + costValue + " activated : " + af);
			row++;
		}
		return td;
	}
}
