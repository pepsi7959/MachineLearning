package com.github.pepsi7959.simpleML;

import java.util.LinkedList;

public class Optimizer {
	public Optimizer(Input in, Output out) {

	}
	
	public void RMSE(Output out, Output expected_out) {
		for (int i = 0; i < out.row; i++) {
			for (int j = 0; j < out.col; i++) {

			}
		}
	}

	public static void gradientDescent(LinkedList<Input> input, double lr, Matrix weight, Matrix td) {
		Matrix w = Matrix.copy(weight);
		double w0 = (double) w.getData(0, 0);
		double w1 = (double) w.getData(1, 0);
		int td_num = td.col;

		System.out.println("w0 = " + weight.getData(0, 0) + ", w1 = " + weight.getData(1, 0));

		w.setData(0, 0, (double) weight.getData(0, 0) - lr * (Matrix.sum(td) / td_num));

		for (int j = 1; j < w.row; j++) {
			for (int i = 0; i < input.size(); i++) {
				double tdi = (double) td.getData(i, 0);
				tdi = tdi * (double) input.get(i).getData(1, 0);
				td.setData(i, 0, tdi);
			}
			// W[j] = W[j] - learningRate/numOfInput * SUM(( Value[i] -
			// ExpectedValue[i])*input[i])
			w.setData(1, 0, (double) weight.getData(1, 0) - lr * (Matrix.sum(td) / td_num));
		}

		// weight.setData(0, 0 , w0);
		// weight.setData(1, 0 , w1);
		for (int i = 0; i < weight.row; i++) {
			for (int j = 0; j < weight.col; j++) {
				weight.setData(i, j, w.getData(i, j));
			}
		}
		System.out.println("w0 = " + weight.getData(0, 0) + ", w1 = " + weight.getData(1, 0));
	}
}
