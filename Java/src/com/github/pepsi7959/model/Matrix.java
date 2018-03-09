package com.github.pepsi7959.model;

import java.text.DecimalFormat;

public class Matrix {

	public double[][] data;
	protected int row = 0;
	protected int col = 0;

	public Matrix(int row, int col) {
		this.row = row;
		this.col = col;
		this.data = new double[row][col];
		setZeros(0);
	}

	public Matrix(int row) {
		this.row = row;
		this.col = 1;
		this.data = new double[row][col];
		setZeros(0);
	}

	public Matrix(double data[][]) {
		this.row = data.length;
		this.col = data[0].length;
		this.data = data;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public double getData(int atRow, int atCol) {
		return this.data[atRow][atCol];
	}

	public void setData(int atRow, int atCol, double item) {
		this.data[atRow][atCol] = item;
	}

	private void setZeros(double item) {
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				this.data[i][j] = item;
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
			throw new RuntimeException("Illigal Vector dimensions "+a.col+":"+b.row);
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
				c.data[i][j] = (double) a.data[i][j] * b;
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
		for (int i = 0; i < a.row; i++) {
			for (int j = 0; j < a.col; j++) {
				c.data[i][j] = a.data[i][j];
			}
		}
		return c;
	}

	public static double sum(Matrix a) {
		double sum = 0.0;
		for (int i = 0; i < a.row; i++) {
			for (int j = 0; j < a.col; j++) {
				sum += (double) a.getData(i, j);
			}
		}
		return sum;
	}

	//Invert Matrix
	//ref:
	//	- https://www.khanacademy.org/math/algebra-home/alg-matrices/alg-determinants-and-inverses-of-large-matrices/v/inverting-3x3-part-2-determinant-and-adjugate-of-a-matrix
	//  - http://www.mathsmutt.co.uk/files/det.htm
	//  - least squares estimates z = ((x'x)^-1)x'y : https://onlinecourses.science.psu.edu/stat501/node/382
	
	public static double getMinorOf(int atRow, int atCol, Matrix m) {
		double sum = 0.0;
		double a = 1;
		for (int j = 0, r = (atRow + 1) % m.row, c = (atCol + 1) % m.col; j < m.row - 1; j++, r = ++r % m.row, c = ++c
				% m.col) {
			//System.out.println("r :" + r + " c :" + c + " " + m.getData(r, c));
			a *= m.getData(r, c);
		}
		sum += a;
		//System.out.println("a : " + a);

		double b = 1;
		for (int j = 0, r = (atRow + (m.row - 1)) % m.row, c = (atCol + 1) % m.col; j < m.row - 1; j++, r = (--r<0)?m.row-1:r
				% m.row, c = ++c % m.col) {
			//System.out.println("r :" + r + " c :" + c + " " + m.getData(Math.abs(r), c));
			b *= m.getData(Math.abs(r), c);

		}
		//System.out.println("b : " + b);
		sum -= b;
		//System.out.println("sum: " + sum);
		return sum;
	}
	
	public static double det(Matrix m) {
		double sum = 0.0;
		for(int i = 0 ; i < m.col; i++) {
			if( i%2 == 0) {
				sum += m.getData(0, i)*Matrix.getMinorOf(0, i, m);
			}else {
				sum += m.getData(0, i)*Matrix.getMinorOf(0, i, m);
			}
		}
		return sum;
	}

	public static Matrix adj(Matrix m) {
		Matrix adj_m = new Matrix(m.row, m.col);
		for (int i = 0; i < m.row; i++) {
			for (int j = 0; j < m.col; j++) {
				double v = getMinorOf(i, j, m);
				adj_m.setData(i, j, v);
			}
		}
		return adj_m;
	}
	
	public static Matrix invert(Matrix m) {
		double det = det(m);
		return multiply(m, 1/det);
	}

	public void copyTo(Matrix m) {
		if( this.col != m.col || this.row != m.row) {
			throw new RuntimeException("Matrix is not the same dimension");
		}
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				m.data[i][j] = this.data[i][j];
			}
		}
	}
}
