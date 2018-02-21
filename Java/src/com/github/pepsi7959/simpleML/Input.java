package com.github.pepsi7959.simpleML;

import java.text.DecimalFormat;

public class Input extends Matrix {
	
	public Input(int row, int col) {
		super(row, col);
	}
	
	@Override
	public void random(double min, double max) {
		for( int i = 0; i < this.row; i++ ) {
			for( int j = 0; j < this.col; j++ )
				this.data[i][j] = min + Math.random()*(max-min); 
		}
	}
	
	@Override
	public void Print() {
		DecimalFormat  df = new DecimalFormat ("#.##");
		System.out.print("Matrix : [");
		for( int i = 0; i < this.row; i++ ) {
			System.out.print("[");
			for( int j = 0; j < this.col; j++ ) {
				System.out.print(df.format(this.data[i][j]));
				if( (j+1) < this.col) {
					System.out.print(",");
				}
			}
			if( (i+1) < this.row)
				System.out.print("],\n");
			else
				System.out.print("]\n");
		}
		System.out.print("]");
	}

}
