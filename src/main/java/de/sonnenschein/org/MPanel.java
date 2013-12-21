package de.sonnenschein.org;

import static com.google.common.collect.FluentIterable.from;
import static de.sonnenschein.org.MandelFun.applyColour;
import static de.sonnenschein.org.MandelFun.coordinateToConvergence;
import static de.sonnenschein.org.MandelFun.pointsInRectangle;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import de.sonnenschein.org.MandelFun.ConvergencePoint;

public class MPanel extends JPanel 
{
	private static final long	serialVersionUID	= -3635943524393466539L;

	public Dimension getPreferredSize() {
		return new Dimension(800, 800);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		MandelCalcBaseParams prams = MandelCalcBaseParams.DEFAULT_PARAMS;
		final Point delim = new Point(this.getWidth(), this.getHeight());
		final ConvergencePoint cp = new ConvergencePoint();
		cp.x = 0; cp.y = 0;
		// draw Mandelbrot

		from(pointsInRectangle(delim))
			.transform(coordinateToConvergence(prams, delim, cp))
			.transform(applyColour(g))
			.toList(); //force execution, discard result

		System.out.println("Drawn!");
	}

}
