package lab04.shapes;

import java.awt.Color;

public class Circle extends Ellipse {
	
	/**
	 * Creates a Circle located at (x,y) with the specified dimension and color.
	 * The Circle's dimensions will be the size of the width regardless of what 
	 * was passed into the height argument
	 *
	 * @param x the x coordinate of the Circle
	 * @param y the y coordinate of the Circle
	 * @param width the width of the Circle
	 * @param height the height of the Circle
	 * @param c the color of the Circle
	 */
	public Circle(int x, int y, int width, int height, Color c) {
		super(x, y, width, width, c);
	}
}
