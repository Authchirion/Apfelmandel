package de.sonnenschein.org;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MMouseListener extends MouseAdapter {

	public MPanel p;

	MMouseListener(MPanel p) {
		this.p = p;
	}

	public void mousePressed(MouseEvent e) {

		float x_local = e.getX() * p.inc / p.getWidth() + p.real_min;
		float y_local = e.getY() * p.inc / p.getHeight() + p.imag_min;

		if (e.getX() < p.getWidth() - e.getX()
				&& e.getY() < p.getHeight() - e.getY()) {
			p.real_max = p.real_max - 2;
			p.imag_max = p.imag_max - 2;

			p.repaint();
		}

		System.out.println("X: " + e.getX() + " Y: " + e.getY());
		System.out.println(x_local + " " + y_local);

	}

}
