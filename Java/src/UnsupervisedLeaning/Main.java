package UnsupervisedLeaning;

import com.github.pepsi7959.simpleML.ActivationFunction;

public class Main {

	public static void main(String[] args) {
		double x = -1.0;
		for (int i = 0 ; i < 20; i++) {
			
			System.out.println("i : " + x + " output : " + ActivationFunction.Relu(x));
			x += 0.1;
		}

	}

}
