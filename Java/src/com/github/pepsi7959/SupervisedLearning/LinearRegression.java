package com.github.pepsi7959.SupervisedLearning;

import java.util.Iterator;
import java.util.LinkedList;

import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;
import com.github.pepsi7959.model.Model;
import com.github.pepsi7959.optimization.Optimizer;;

public class LinearRegression extends Model{

	public LinearRegression(LinkedList<Dataset> datasets, LinkedList<Double> ev, double lr, int step, Matrix w) {
		super();
		this.datasets = datasets;
		this.ev = ev;
		this.lr = lr;
		this.step = step;
		if (w == null)
			initialize(datasets.getFirst().getRow(), 1);
		else
			this.w = w;
	}

	private void initialize(int row, int col) {
		this.w = new Matrix(row, col);
		this.w.random(0, 100);
	}

	@Override
	public double hypothesis(Dataset dataset, Matrix w) {
		return Matrix.multiply(dataset, w);
	}

	/*
	 * costFunc : It will calculate cost function, which return sum of different
	 * value between input and expected value and even store temporal difference
	 * into matrix td
	 */
	@Override
	public double costFunc() {

		Iterator<Dataset> iterOfInputs = this.datasets.iterator();
		this.td = new Matrix(datasets.size(), 1);
		int numOfInput = datasets.size();
		double evi = 0.0;
		double hThetaX = 0.0;
		double sum = 0.0;
		int i = 0;
		Dataset in = null;

		while (iterOfInputs.hasNext()) {
			evi = ev.get(i);
			in = iterOfInputs.next();
			hThetaX = hypothesis(in, w);
			
			//Find cost value
			double tdValue = (hThetaX - evi);
			td.setData(i, 0, tdValue);
			sum += tdValue;
			i++;
		}

		return sum/numOfInput;
	}

	@Override
	public Matrix train() {
		double costVal = 0.0;
		int s = 0;
		for (s = 0; s < this.step; s++) {
			costVal = costFunc();
			if (Math.abs(costVal) < 0.000001) {
				System.out.print("Training is complete: Cost value is less than " + 0.000001);
				break;
			}
			
			System.out.println("\rStep: " + s + " Cost value: " + costVal);
			Optimizer.gradientDescent(this.datasets, this.lr, this.w, this.td);
		}
		System.out.println("\n========================================================================================");
		System.out.println("Step : " + s);
		System.out.println("Error: " + costVal);
		System.out.println("out:");
		w.Print();
		return this.w;
	}

	@Override
	public void test(LinkedList<Dataset> datasets, LinkedList<Double> expectedValue) {
		LinkedList<Dataset> in = (datasets != null) ? datasets : this.datasets;
		LinkedList<Double> ev = (expectedValue != null) ? expectedValue : this.ev;
		Iterator<Dataset> itOfInput = in.iterator();
		Iterator<Double> itOfEv = ev.iterator();
		while (itOfInput.hasNext()) {
			Dataset dataset = itOfInput.next();
			double out = hypothesis(dataset, w);
			System.out.println(String.format("Input: %s , output: %f, ev: %f", dataset.toString(), out, itOfEv.next()));
		}
	}
}
