package lab04.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Ellipse extends Point implements Scaleable{

	private int elliWidth;
	private int elliHeight;
	
	/**
	 * Creates an Ellipse located at (x,y) with the specified width, height, and color.
	 * 
	 * @param x the x coordinate of the Ellipse
	 * @param y the y coordinate of the Ellipse
	 * @param width the width of the Ellipse
	 * @param height the height of the Ellipse
	 * @param c the color of the Ellipse
	 */
	public Ellipse(int x, int y, int width, int height, Color c) {
		super(x, y, c);
		elliWidth = width;
		elliHeight = height;
	}
	
	public int getWidth() {
		return elliWidth;
	}
	
	public int getHeight() {
		return elliHeight;
	}
	
	/**
	 * Draw the Ellipse on the graphics context.
	 * 
	 * @param g the Graphics context on which to draw the Point.
	 */
	public void draw(Graphics g) {
		g.setColor(super.getColor());
		g.drawOval(super.getX(), super.getY(), elliWidth, elliHeight);
		g.fillOval(super.getX(), super.getY(), elliWidth, elliHeight);
	}

	/**
	 * Scale the Ellipse width and height by the specified factor. 
	 * For example a factor of 2.0 will double the width and height of the 
	 * Ellipse while a factor of 0.5 will make it half as large. 
	 * If the factor is negative, then nothing happens to the Rectangle.
	 * 
	 * 
	 * @param factor the amount by which this Ellipse is to be scaled.
	 */
	@Override
	public void scale(double factor) {
		if (factor > 0) {
			elliWidth = (int) Math.round(factor * elliWidth);
			elliHeight = (int) Math.round(factor * elliHeight);
		}//end if statement
		
	}
}
