package lab04.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Point {

	private int x2;
	private int y2;

	/**
	 * Creates a Line in the chosen color located at the 2 specified (x,y) coordinates
	 * 
	 * @param x1 the first x coordinate of the Line
	 * @param y1 the first y coordinate of the Line
	 * @param x2 the second x coordinate of the Line
	 * @param y2 the second y coordinate of the Line
	 * @param c the color of the Line
	 * 
	 */

	public Line(int x1, int y1, int x2, int y2, Color c) {
		super(x1, y1, c);
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * Get the x2 value of the Line
	 * 
	 * @return the second x value of the Line
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Get the y2 value of the Line
	 * 
	 * @return the second y value of the Line
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * Moves the line to a new (x,y) location. 
	 * 
	 * Overrides the super class implementation.
	 * 
	 * @param newX the new x value of the Line
	 * @param newY the new y value of the Line
	 */

	@Override
	public void move(int newX, int newY) {		
		super.move(newX, newY);
		x2 += newX;
		y2 += newY;

	}

	/**
	 * Move the Line relative to its current location. The location of the
	 * Line is moved by adding the parameters to the Line's current
	 * location. 
	 * 
	 * Overrides the superclass implementation
	 * 
	 * @param dX the change in the x coordinate. Positive values move the
	 *            Text to the right, negative values move it to the left.
	 *            
	 * @param dY the change in the y coordinate. Positive values move the
	 *            Text down, negative values move it up.
	 * 
	 */

	@Override
	public void translate(int dX, int dY) {
		super.translate(dX, dY);

		//updates the (x2,y2) in this class
		x2 += dX;
		y2 += dY;
	}

	/**
	 * Draw the Line on the graphics context.
	 * 
	 * @param g the Graphics context on which to draw the Point.
	 */
	public void draw(Graphics g) {
		g.setColor(super.getColor());
		g.drawLine(super.getX(), super.getY(), x2, y2);
	}
}
