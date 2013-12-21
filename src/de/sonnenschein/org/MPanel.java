package de.sonnenschein.org;

import java.awt.*;

import javax.swing.JPanel;

public class MPanel extends JPanel {

	// coordinates of mandelbrot frame
	public float real_min = -2.0f;
	public float real_max = 1.0f;

	public float imag_max = 1.5f;
	public float imag_min = -1.5f;

	// Iterator
	private int iter = 100;
	private int n = iter;
	private int counter = 0;

	// define c
	private float c_real = 0.0f;
	private float c_imag = 0.0f;

	// define Z0
	private float z_real = 0.0f;
	private float z_imag = 0.0f;

	// define Zn
	private float zn_real = 0.0f;
	private float zn_imag = 0.0f;

	// define increment
	public float inc = real_max - real_min;

	public MPanel() {

		MMouseListener mml = new MMouseListener(this);
		addMouseListener(mml);

	}

	public Dimension getPreferredSize() {
		return new Dimension(800, 800);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// TODO: Richtiges Datenmodel überlegen

		// draw Mandelbrot

		g.setColor(Color.red);
		g.drawLine(0, 0, 400, 400);

		for (int j = 0; j < this.getHeight(); j++) {
			for (int i = 0; i < this.getWidth(); i++) {

				c_real = real_min + i * inc / this.getWidth();
				c_imag = imag_min + j * inc / this.getHeight();

				for (; iter > 0; iter--) {

					zn_real = z_real * z_real - z_imag * z_imag + c_real;
					zn_imag = 2 * z_real * z_imag + c_imag;

					z_real = zn_real;
					z_imag = zn_imag;

					if (zn_real * zn_real + zn_imag * zn_imag >= 4.0f) {
						iter = 0;
						if (counter < 256) {
							g.setColor(new Color(counter, counter, counter));
						}
						if (counter >= 256) {
							g.setColor(new Color(1, 1, 1));
						}
						g.drawRect(i, j, 1, 1);
					}

					counter++;

				}
				if (zn_real * zn_real + zn_imag * zn_imag < 4.0f) {
					g.setColor(new Color(150, 200, 128));
					g.drawRect(i, j, 1, 1);
				}

				z_real = 0.0f;
				z_imag = 0.0f;
				iter = n;
				counter = 0;

			}
		}

		System.out.println(real_max);
		System.out.println("Drawn!");
	}

}
