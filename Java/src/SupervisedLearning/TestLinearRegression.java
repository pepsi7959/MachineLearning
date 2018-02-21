package SupervisedLearning;

import java.util.LinkedList;

import com.github.pepsi7959.simpleML.Input;
import com.github.pepsi7959.simpleML.LinearRegression;
import com.github.pepsi7959.simpleML.Matrix;
import com.github.pepsi7959.simpleML.Optimizer;

public class TestLinearRegression {
	public static void Test() {
		// 1. Data Preparation
		double lr = 0.001;
		int step = 1000000;

		LinkedList<Input> inputs = new LinkedList<Input>();
		LinkedList<Double> ev = new LinkedList<Double>();

		Matrix weight = new Matrix(2, 1);
		weight.random(0, 5);

		Input x0 = new Input(2, 1);
		x0.setData(0, 0, 1.0); // x0
		x0.setData(1, 0, 1.0); // x1
		ev.addLast(3.0);

		Input x1 = new Input(2, 1);
		x1.setData(0, 0, 1.0); // x0
		x1.setData(1, 0, 2.0); // x1
		ev.addLast(5.0);

		Input x2 = new Input(2, 1);
		x2.setData(0, 0, 1.0); // x0
		x2.setData(1, 0, 3.0); // x1
		ev.addLast(7.0);

		Input x3 = new Input(2, 1);
		x3.setData(0, 0, 1.0); // x0
		x3.setData(1, 0, 4.0); // x1
		ev.addLast(9.0);

		Input x4 = new Input(2, 1);
		x4.setData(0, 0, 1.0); // x0
		x4.setData(1, 0, 5.0); // x1
		ev.addLast(11.0);

		Input x5 = new Input(2, 1);
		x5.setData(0, 0, 1.0); // x0
		x5.setData(1, 0, 6.0); // x1
		ev.addLast(13.0);

		inputs.add(x0);
		inputs.add(x1);
		inputs.add(x2);
		inputs.add(x3);
		inputs.add(x4);
		inputs.add(x5);

		// 2. Algorithm selection
		LinearRegression LR = new LinearRegression(inputs, weight, ev);

		for (int i = 0; i < step; i++) {
			Matrix td = LR.train();
			double err = Matrix.sum(td);
			System.out.println("Error : " + err);

			// 4. Evaluation
			if (Math.abs(err) < 0.001) {
				System.out.println("==============================");
				System.out.println("Error rate: " + err);
				System.out.print("Output ");
				weight.Print();
				System.out.println("Step : " + i);
				break;
			}
			// 3. Parameter Tunning
			Optimizer.gradientDescent(inputs, lr, weight, td);
		}

	}
}
