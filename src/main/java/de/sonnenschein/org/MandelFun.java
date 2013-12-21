package de.sonnenschein.org;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayListWithExpectedSize;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import com.google.common.base.Function;

public class MandelFun {
	public static class ConvergencePoint {
		public float x, y;
	}

	public static class ColouredPoint extends Point {
		private static final long serialVersionUID = -7345850759319898885L;
		private Color colour;

		public Color colour() {
			return colour;
		}

		/**
		 * 
		 * @param p
		 *            not <code>null</code>
		 * @param c
		 *            not <code>null</code>
		 */
		public ColouredPoint(Point p, Color c) {
			super(checkNotNull(p));
			this.colour = checkNotNull(c);
		}
	}

	public static final Iterable<Point> pointsInRectangle(Point bounds) {
		List<Point> points = newArrayListWithExpectedSize(bounds.x * bounds.y);
		// TODO: replace this with a more elegant implementation
		for (int y = 0; y < bounds.y; y++)
			for (int x = 0; x < bounds.x; x++)
				points.add(new Point(x, y));
		return points;
	}

	/**
	 * 
	 * @param params
	 *            not <code>null</code>
	 * @param mandelDelim
	 *            overall width and height of all investigated points
	 * @return the colour to which the given point ought to be
	 */
	public final static Function<Point, ColouredPoint> coordinateToConvergence(
			final MandelCalcBaseParams params, final Point mandelDelim,
			final ConvergencePoint cp) {
		return new Function<Point, ColouredPoint>() {
			final int MAX_CONV_ITER = 100;

			@Override
			public ColouredPoint apply(Point input) {
				float c_real = params.REAL_MIN + input.x * params.INC
						/ mandelDelim.x;
				float c_imag = params.IMAG_MIN + input.y * params.INC
						/ mandelDelim.y;

				float z_real = cp.x;
				float z_imag = cp.y;

				for (int iter = 0; iter < MAX_CONV_ITER; iter++) {
					float zn_real = z_real * z_real - z_imag * z_imag + c_real;
					float zn_imag = 2 * z_real * z_imag + c_imag;

					z_real = zn_real;
					z_imag = zn_imag;

					if (zn_real * zn_real + zn_imag * zn_imag >= 4.0f) {// point
																		// does
																		// not
																		// converge
						if (iter < 256)
							return new ColouredPoint(input, new Color(iter,
									iter, iter));
						return new ColouredPoint(input, new Color(1, 1, 1));
					}

				}
				// point belongs to mandelset
				return new ColouredPoint(input, new Color(150, 200, 128));
			}
		};
	}

	public final static Function<ColouredPoint, ColouredPoint> applyColour(
			final Graphics g) {
		return new Function<ColouredPoint, ColouredPoint>() {
			@Override
			public ColouredPoint apply(ColouredPoint input) {
				g.setColor(input.colour());
				g.drawRect(input.x, input.y, 1, 1);
				return input;
			}

		};

	}
}
