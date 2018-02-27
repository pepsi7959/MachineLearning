package SupervisedLearning;

import java.util.LinkedList;
import java.util.Iterator;
import com.github.pepsi7959.simpleML.Input;
import com.github.pepsi7959.simpleML.Matrix;
import com.github.pepsi7959.simpleML.Optimizer;

public class LogisticRegression {

	private int samplingRate = 1000;
	private LinkedList<Input> inputs = null;// patch inputs
	private LinkedList<Double> ev = null; // Expected Value
	private double lr = 0.01; // Learnging rate
	private int step = 1000000; // steps
	private Matrix w = null; // weight
	private Matrix td = null; // temporal defference

	public LogisticRegression(LinkedList<Input> inputs, LinkedList<Double> ev, double lr, int step, Matrix w) {
		this.inputs = inputs;
		this.ev = ev;
		this.lr = lr;
		this.step = step;
		if (w == null)
			initializeWeight(inputs.getFirst().getRow(), 1);
		else
			this.w = w;
	}

	private void initializeWeight(int row, int col) {
		this.w = new Matrix(row, col);
		this.w.random(0, 100);
	}

	/*
	 * costFunc : It will calculate cost function, which return sum of different
	 * value between input and expected value and even store temporal difference
	 * into matrix td
	 */
	private double costFunc() {

		Iterator<Input> iter_inputs = this.inputs.iterator();
		this.td = new Matrix(inputs.size(), 1);
		int numOfInput = inputs.size();
		double evi = 0.0;
		double jTheta = 0.0;
		double hThetaX = 0.0;
		double z = 0.0;
		double sum = 0.0;
		int i = 0;
		Input in = null;

		while (iter_inputs.hasNext()) {
			evi = ev.get(i);
			in = iter_inputs.next();
			z = Matrix.multiply(in, w);
			hThetaX = 1.0 / (1.0 + Math.exp(-z));
			jTheta = evi * Math.log(hThetaX) + (1 - evi) * Math.log(hThetaX);
			sum += jTheta;
			td.setData(i, 0, jTheta);
			i++;
		}

		return -(sum / numOfInput);
	}

	public Matrix train() {
		double costVal = 0.0;
		int s = 0;
		for (s = 0; s < this.step; s++) {
			costVal = costFunc();
			if (costVal < 0.001) {
				System.out.print("Training is complete: Cost value is less than " + 0.001);
				break;
			}
			
			System.out.println("Cost value: " + costVal);
			Optimizer.gradientDescent(this.inputs, this.lr, this.w, this.td);
		}
		System.out.println("========================================================================================");
		System.out.println("Step : " + s);
		System.out.println("Error: " + costVal);
		System.out.println("out:");
		w.Print();
		return this.w;
	}
}
