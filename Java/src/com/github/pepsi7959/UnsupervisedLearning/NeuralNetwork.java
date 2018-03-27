package com.github.pepsi7959.UnsupervisedLearning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;

public class NeuralNetwork {

	private LinkedList<Dataset> inputs;
	private LinkedList<Double> ev;
	private ArrayList<Layer> layers;
	private int step;

	public NeuralNetwork(LinkedList<Dataset> inputs, LinkedList<Double> ev, int step) {
		this.setInputs(inputs);
		this.setEv(ev);
		this.layers = new ArrayList<Layer>();
		this.step = step;
	}

	public void addNewLayer(Layer layer) {
		this.layers.add(layer);
	}

	public void backwardPropagation(Matrix input) {

	}

	public void forwardPropagation(Dataset input) {
		for (int l = 0; l < getNumOfLayer(); l++) {
			
			Layer layer = layers.get(l);
			System.out.println("Layer : " + l);
			
			for (int i = 0; i < layer.getnUnit(); i++) {
				// Input layer
				if (l == 0) {
					layer.setOutput(input);

					// Hidden and output layer
				} else {
					Layer prev_layer = layers.get(l - 1);
					Unit unit = layer.getUnits().get(i);
					unit.output = unit.activatedFunc(unit.hypothesis(prev_layer.output));
					System.out.println("Unit " + (i + 1) + " : " + unit.output);
					layer.output.setData(0, i + 1, unit.output);
				}
			}
			
			System.out.println("Layer Output: " + this.layers.get(l).getOutput().toString());
		}
	}

	public void train() {

		double err = 0.0;
		Iterator<Dataset> iter_in = this.inputs.iterator();
		Iterator<Double> iter_ev = this.ev.iterator();

		// The Step will be used for training model
		for (int s = 0; s < this.step; s++) {

			// Select a input to train model
			System.out.println("======== Step " + s + "========");
			iter_in = this.inputs.iterator();
			while (iter_in.hasNext()) {

				Dataset in = iter_in.next();

				// Forward Propagation
				this.forwardPropagation(in);

				// Loss function
				double ev = iter_ev.next();
				double delta = this.layers.get(this.layers.size() - 1).output.getData(0, 1) - ev;

				// Backward Propagation
				backwardPropagation(in);

				System.out.println("\n-------------------------\n");
				System.out.println("Delta: " + delta);
				System.out.println("Last Layer Output: " + this.layers.get(this.layers.size() - 1).output.toString());
				System.out.println("\n=========================\n");
			}

			if (err < 0.01) {
				break;
			}
		}
	}

	public int getNumOfLayer() {
		return this.layers.size();
	}

	public LinkedList<Double> getEv() {
		return ev;
	}

	public void setEv(LinkedList<Double> ev) {
		this.ev = ev;
	}

	public LinkedList<Dataset> getInputs() {
		return inputs;
	}

	public void setInputs(LinkedList<Dataset> inputs) {
		this.inputs = inputs;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
}
