package com.github.pepsi7959.simpleML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class Input extends Matrix {

	public Input(int row, int col) {
		super(row, col);
	}

	public static LinkedList<Input> fromFile(String fileName) {
		File file = new File(fileName);
		BufferedReader br;
		LinkedList<Input> inputs = new LinkedList<Input>();
		try {
			
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			System.out.println("header: ");

			
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String str[] = line.split(",");
				Input m = new Input(str.length, 1);
				m.setData(0, 0, Double.parseDouble(str[0].trim()));
				m.setData(1, 0, Double.parseDouble(str[1].trim()));
				inputs.addLast(m);
			}
			System.out.println("Number of elements : " + inputs.size());
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputs;
	}

	@Override
	public void random(double min, double max) {
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++)
				this.data[i][j] = min + Math.random() * (max - min);
		}
	}

	@Override
	public void Print() {
		DecimalFormat df = new DecimalFormat("#.##");
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
				System.out.print("]\n");
		}
		System.out.print("]");
	}

	public static LinkedList<Double> expectedValueFromFile(String fileName) {
		File file = new File(fileName);
		BufferedReader br;
		LinkedList<Double> ev = new LinkedList<Double>();
		try {
			
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			System.out.println("header: ");
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				ev.addLast(Double.parseDouble(line.trim()));
			}
			System.out.println("Number of elements : "+ev.size());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ev;
	}

}
