package com.github.pepsi7959.simpleML;

import java.text.DecimalFormat;

public class Matrix {
	
	public Object[][] data;
	protected int row = 0;
	protected int col = 0;

	public Matrix(int row, int col) {
		this.row = row;
		this.col = col;
		this.data = new Object[row][col];
		setZeros(0);
	}
	
	public Matrix(int row) {
		this.row = row;
		this.col = 1;
		this.data = new Object[row][col];
		setZeros(0);
	}

	public Object getData(int atRow, int atCol) {
		return this.data[atRow][atCol];
	}

	public void setData(int atRow, int atCol, Object item) {
		this.data[atRow][atCol] = item;
	}

	private void setZeros(Object object) {
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				this.data[i][j] = object;
			}
		}
	}

	public static Matrix transpose(Matrix a) {
		Matrix c = new Matrix(a.col, a.row);
		for (int i = 0; i < a.row; i++) {
			for (int j = 0; j < a.col; j++) {
				c.data[j][i] = a.data[i][j];
			}
		}
		return c;
	}

	public Matrix transpose() {
		return transpose(this);
	}

	// c = a^T * b
	public static double dot(Matrix a, Matrix b) {
		return multiply(a.transpose(), b);
	}

	// c = a * b
	public static double multiply(Matrix a, Matrix b) {
		if (a.col != b.row) {
			throw new RuntimeException("Illigal Vector dimensions");
		}
		double sum = 0.0;
		for (int i = 0; i < a.row; i++) {
			for (int k = 0; k < b.col; k++) {
				for (int j = 0; j < b.row; j++) {
					sum += ((double) a.data[i][j]) * ((double) b.data[j][k]);
				}
			}
		}
		return sum;
	}

	// c = a * 3
	public static Matrix multiply(Matrix a, double b) {
		Matrix c = copy(a);
		for (int i = 0; i < a.row; i++) {
			for (int j = 0; j < a.col; j++) {
				c.data[i][j] = (double)a.data[i][j] * b;
			}
		}
		return c;
	}
	
	// c = a + b
	public static Matrix add(Matrix a, Matrix b) {
		if (a.row == b.row && a.col == b.col) {
			throw new RuntimeException("Illegel vector dimensions");
		}
		Matrix c = new Matrix(a.row, a.col);
		for (int i = 0; i < a.row; i++) {
			for (int j = 0; j < a.col; i++) {
				c.data[i][j] = (double) a.data[i][j] + (double) b.data[i][j];
			}
		}
		return c;
	}

	public void Print() {
		DecimalFormat df = new DecimalFormat("#.######");
		System.out.print("Matrix : [");
		for (int i = 0; i < this.row; i++) {
			System.out.print("[");
			for (int j = 0; j < this.col; j++) {
				System.out.print(df.format(this.data[i][j]));
				if ((j + 1) < this.col) {
					System.out.print(",");
				}
			}
			if ((i + 1) < this.row)
				System.out.print("],\n");
			else
				System.out.print("]");
		}
		System.out.println("]");
	}

	public void random(double min, double max) {
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++)
				this.data[i][j] = min + Math.random() * (max - min);
		}
	}

	public static Matrix copy(Matrix a) {
		Matrix c = new Matrix(a.row, a.col);
		for(int i = 0; i < a.row; i++) {
			for(int j = 0; j < a.col; j++) {
				c.data[i][j] = a.data[i][j];
			}
		}
		return c;
	}

	public static double sum(Matrix a) {
		double sum = 0.0;
		for(int i = 0 ;i < a.row; i++) {
			for(int j = 0; j < a.col; j++) {
				sum += (double)a.getData(i, j);
			}
		}
		return sum;
	}
	
}
