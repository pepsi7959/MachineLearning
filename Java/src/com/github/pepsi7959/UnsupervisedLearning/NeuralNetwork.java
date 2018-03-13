package com.github.pepsi7959.UnsupervisedLearning;

import java.util.Iterator;
import java.util.LinkedList;

import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;
import com.github.pepsi7959.optimization.ActivationFunction;

public class NeuralNetwork {
	private LinkedList<Dataset> datasets;
	private LinkedList<Double> ev;
	private double nLayer = 1;
	private Matrix weight;
	private double step;
	private double lr;
	

	public NeuralNetwork(LinkedList<Dataset> datasets, LinkedList<Double> ev, Matrix weight) {
		this.datasets = datasets;
		this.ev = ev;
		this.weight = weight;
		initialize();
	}
	
	public NeuralNetwork(LinkedList<Input> inputs, LinkedList<Double> ev, Matrix weight, int nLayer) {
		this.inputs = inputs;
		this.nLayer = nLayer;
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
			double afValue = ActivationFunction.sinusoid(costValue);
			System.out.println("input " + in.getData(0, 0)+", "+ in.getData(0, 1) + " output : " + costValue + " activated : " + afValue);
			row++;
		}
		return td;
	}
	
	public static Matrix train(LinkedList<Layer> layers) {
		Iterator<Layer> iter_layer = layers.iterator();
		Layer l = null;
		int i = 0;
		while( iter_layer.hasNext() ) {
			l = iter_layer.next();
			System.out.println("Layer "+i);
			
			i++;
		}
		return l.weight;
	}
	
	static class Layer{
		private LinkedList<Input> inputs;
		private LinkedList<Input> outputs;
		private Matrix weight;
		private int dim;
		private Layer next = null;
		
		public Layer(LinkedList<Input> inputs, Matrix weight, int dim) {
			this.inputs = inputs;
			this.weight = weight;
			this.dim = dim;
		}
		
		public Layer(int dim) {
			this.dim = dim;
		}
		
		
		public void bind(Layer l) { this.next = l;}
		
		
		public static LinkedList<Input> forwardProp(LinkedList<Input> inputs, Matrix Weight, int dim_outputs) {
			LinkedList<Input> outputs = new LinkedList<Input>();
			return outputs;
		}
		
		public static void main(String[] args) {
			LinkedList<Input> inputs = Input.fromFile("src\\UnsupervisedLearning\\inputs.csv");
			Matrix weight = new Matrix(inputs.getFirst().getRow(), 1);
			weight.random(0, 5);
			

			Layer l0 = new Layer(inputs, weight, 2);
			Layer l1 = new Layer(2);
			Layer l2 = new Layer(1);
			
			LinkedList<Layer> layers = new LinkedList<Layer>();
			layers.addLast(l0);
			layers.addLast(l1);
			layers.addLast(l2);
			
			NeuralNetwork.train(layers);
			
		}
	}
}
