package lab04.shapes;

import java.awt.Color;

public class Square extends Rectangle{

	/**
	 * Creates a Square located at (x,y) with the specified dimension and color.
	 * The Square's dimensions will be the size of the width regardless of what 
	 * was passed into the height argument
	 * 
	 * @param x the x coordinate of the Square
	 * @param y the y coordinate of the Square
	 * @param width the width of the Square
	 * @param height the height of the Square
	 * @param c the color the Square
	 */
	public Square(int x, int y, int width, int height, Color c) {
		//going with the width as the dimension that matters
		super(x, y, width, width, c);
	}
}
