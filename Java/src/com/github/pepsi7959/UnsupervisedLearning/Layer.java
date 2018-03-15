package com.github.pepsi7959.UnsupervisedLearning;

import java.util.ArrayList;

import com.github.pepsi7959.model.Dataset;
import com.github.pepsi7959.model.Matrix;

public class Layer {

	private int nInput = 1;
	private int nUnit = 0;
	private ArrayList<Unit> units;
	public Dataset output;

	public Layer(int nInput, int nUnit) {
		this.nInput = nInput;
		this.nUnit = nUnit;
		this.output = new Dataset(1, nUnit + 1);
		/*TODO: set default output , It should be set on forward propagation*/
		this.output.setData(0, 0, 1);
		createUnit();
	}

	public Layer(int nInput, int nUnit, boolean isInputLayer) {
		this.nInput = nInput;
		this.nUnit = nUnit;
		createUnit();
	}

	public void createUnit() {
		this.units = new ArrayList<>(this.nUnit);
		for (int i = 0; i < this.nUnit; i++) {
			units.add(new Unit(nInput));
		}
	}


	public int getnInput() {
		return nInput;
	}

	public void setnInput(int nInput) {
		this.nInput = nInput;
	}

	public int getnUnit() {
		return nUnit;
	}

	public void setnUnit(int nUnit) {
		this.nUnit = nUnit;
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

	public void setUnits(ArrayList<Unit> units) {
		this.units = units;
	}

	public Matrix getOutput() {
		return output;
	}

	public void setOutput(Dataset output) {
		this.output = output;
	}
}