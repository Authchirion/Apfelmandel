package de.sonnenschein.org;


import javax.swing.*;

public class Main {


	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Mandelbrot");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MPanel());
		frame.pack();
		frame.setVisible(true);
		

	}

}
