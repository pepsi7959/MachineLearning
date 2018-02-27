package SupervisedLearning;

import java.util.LinkedList;

import com.github.pepsi7959.simpleML.Input;
import com.github.pepsi7959.simpleML.LinearRegression;
import com.github.pepsi7959.simpleML.Matrix;
import com.github.pepsi7959.simpleML.Optimizer;

public class TestLinearRegression {
	public static void Test() {
		// 1. Data Preparation
		double lr = 0.0001;
		int step = 1000000;

		Matrix weight = new Matrix(2, 1);
		//weight.setData(0, 0, 1.0); // w0
		//weight.setData(1, 0, 2.0); // w1
		weight.random(0, 5);

		LinkedList<Input> inputs = Input.fromFile("src\\SupervisedLearning\\inputs.csv");
		LinkedList<Double> ev = Input.expectedValueFromFile("src\\SupervisedLearning\\ExpectedValue.csv");
		
		// 2. Algorithm selection
		LinearRegression LR = new LinearRegression(inputs, weight, ev);

		double acceptedError = 100000000;
		
		for (int i = 0; i < step; i++) {
			Matrix td = LR.train();
			double err = Math.abs(Matrix.sum(td));
			System.out.println("Error : " + err);
			
			// 3. Parameter Tunning
			if (  err < 0.001 ) {
				System.out.println("==============================");
				System.out.println("Error rate: " + err);
				System.out.print("Output ");
				weight.Print();
				System.out.println("Step : " + i);
				break;
			}else if( err > acceptedError){
				System.out.println("==============================");
				System.out.println("The error in not acceptable: "+err+" > "+acceptedError);
				break;
			}

			// 4. Evaluation
			Optimizer.gradientDescent(inputs, lr, weight, td);
		}

	}
}
