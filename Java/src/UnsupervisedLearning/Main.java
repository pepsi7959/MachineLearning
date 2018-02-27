package UnsupervisedLearning;

import java.util.LinkedList;

import com.github.pepsi7959.simpleML.Input;
import com.github.pepsi7959.simpleML.Matrix;

public class Main {

	public static void main(String[] args) {
		
		LinkedList<Input> inputs = Input.fromFile("src\\UnsupervisedLearning\\inputs.csv");
		Matrix weight = new Matrix(inputs.getFirst().getRow(), 1);
		weight.random(0, 5);
		
		NeuralNetwork nn = new NeuralNetwork(inputs, null, weight);
		nn.train();
	}

}
