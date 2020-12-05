package lab03.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line implements Drawable{

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private Color color;
	private boolean visible;

	/**
	 * Creates a Line with the specified (x,y) coordinates and color
	 * 
	 * @param x1 the first x coordinate of the Line
	 * @param y1 the first y coordinate of the Line
	 * @param x2 the second x coordinate of the Line
	 * @param y2 the second y coordinate of the Line
	 * @param c the color of the Line
	 * 
	 */

	public Line(int x1, int y1, int x2, int y2, Color c) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		color = c;
		visible = true;
	}

	/**
	 * Get the x1 value of the Line
	 * 
	 * @return the x1 value of the Line
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Get the y1 value of the Line
	 * 
	 * @return the y1 value of the Line
	 */
	public int getY1() {
		return y1;
	}


	/**
	 * Get the x2 value of the Line
	 * 
	 * @return the x value of the Line
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Get the y2 value of the Line
	 * 
	 * @return the y value of the Line
	 */
	public int getY2() {
		return y2;
	}
	
	/**
	 * Get the slope of the Line
	 * @param l the Line whose slope that will be calculated
	 * @return the slope of the Line
	 */
	public int getSlope() {
		int xDiff = this.getX2() - this.getX1();
		int yDiff = this.getY2() - this.getY1();
		int slope = (int) Math.round(yDiff/xDiff);
		
		return slope;
	}

	/**
	 * Moves the line to a new (x,y) location. 
	 * 
	 * Overrides the super class implementation.
	 * 
	 * @param newX the new x value of the Line
	 * @param newY the new y value of the Line
	 */

	public void move(int newX, int newY) {		
		x1 = newX;
		y1 = newY;
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

	public void translate(int dX, int dY) {
		x1 += dX;
		y1 += dY;
		x2 += dX;
		y2 += dY;
	}

	// === Implementation of the Drawable interface ===

	/**
	 * Get the Color of this Line.
	 * 
	 * @return the color of the Line.
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}

	/**
	 * Get the Color of this Line.
	 * 
	 * @return the color of the Line.
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Set the Color of this Line.
	 * 
	 * @return the new color of this Line.
	 */
	@Override
	public void setColor(Color newColor) {
		color = newColor;

	}

	/**
	 * Set the visibility of this Line.
	 * 
	 * @return the new visibility of the Line.
	 */
	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;

	}

	/**
	 * Get the visibility of this Line.
	 * 
	 * @return the new visibility of the Line.
	 */
	@Override
	public boolean isVisible() {
		return visible;
	}
}