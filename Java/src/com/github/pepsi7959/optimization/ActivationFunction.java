package com.github.pepsi7959.optimization;

public class ActivationFunction {
	public static double sinusoid(double x) {
		return 1/(1+(1/Math.exp(x)));
	}
	
	public static double Relu(double x) {
		return (x < 0)?0:x;
	}

}
