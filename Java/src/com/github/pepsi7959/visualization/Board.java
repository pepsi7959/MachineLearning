package com.github.pepsi7959.visualization;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board {
	private String name = "Untitiled";
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private int offset = 10;

	private int width = 600;
	private int height = 400;

	public Board() {
		initialize();
	}
	
	public Board(int width, int height, String name) {
		this.width = width;
		this.height = height;
		this.name = name;
		initialize();
	}
	
	public void initialize() {
		mainFrame = new JFrame(this.name);
		mainFrame.setSize(this.width+this.offset, this.height+this.offset);
		mainFrame.setResizable(false);
		mainFrame.setLayout( new GridLayout(3, 1));
		
		headerLabel = new JLabel("Header", JLabel.CENTER);
		headerLabel.setSize(this.width, this.height*10/100);
		
		controlPanel = new ControlPanel(this.width, this.height*80/100);
		
		statusLabel = new JLabel("status", JLabel.CENTER);
		statusLabel.setSize(this.width, this.height*10/100);
		
		
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);

	}
	
	class ControlPanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int width = 600;
		private int height = 160;
		
		public ControlPanel() {
			super();
			this.setLayout(new FlowLayout());
		}
		
		public ControlPanel(int width, int height) {
			super();
			this.width = width;
			this.height = height;
			this.setLayout(new FlowLayout());
		}
		
		public void paint(Graphics g) {
			drawScale(g, 10,10);
		}
		
		public void drawScale(Graphics g, int x , int y) {
			g.setColor(new Color(40, 40, 40));
			g.drawRect(0, 0, this.width, 140);
		}
		
	}
}
