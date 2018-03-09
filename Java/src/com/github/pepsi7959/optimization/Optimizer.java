package com.github.pepsi7959.optimization;

import java.util.LinkedList;

import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;

public class Optimizer {

	/*
	 * Y := W1*X1 + W0*X0 W0 : indicate as bias X0 : always is 1
	 */
	public static void gradientDescent(LinkedList<Dataset> dataset, double lr, Matrix weight, Matrix td) {
		Matrix w = Matrix.copy(weight); // temporary matrix
		int tdNum = td.getCol();

		// Adjust a bias
		// Wi := Wi - lr*(sum(td)/numberOfTd)
		// w.setData(0, 0, (double) weight.getData(0, 0) - lr * (Matrix.sum(td) / tdNum));
		
		for (int j = 0; j < w.getRow(); j++) {
			double sumTd = 0.0;
			for (int i = 0; i < dataset.size(); i++) {
				double tdi = td.getData(i, 0);
				sumTd += tdi * dataset.get(i).getData(0, j);
			}
			// W[j] = W[j] - learningRate/numOfInput * SUM(( Value[i] -
			// ExpectedValue[i])*input[i])
			System.out.println(String.format("W%d = %f", j, weight.getData(j, 0)));
			w.setData(j, 0, weight.getData(j, 0) - lr * (sumTd / tdNum));
		}
		w.copyTo(weight);
	}
}
