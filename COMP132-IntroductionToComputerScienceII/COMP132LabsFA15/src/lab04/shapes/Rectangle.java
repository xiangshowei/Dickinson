package lab04.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Point implements Scaleable{

	private int rectWidth;
	private int rectHeight;
	
	/**
	 * Creates a Rectangle located at location (x,y) with the specified width, height, and color
	 * 
	 * @param x the x coordinate of the Rectangle
	 * @param y the y coordinate of the Rectangle
	 * @param width the width of the Rectangle
	 * @param height the height of the Rectangle
	 * @param c the color the Rectangle
	 */
	public Rectangle(int x, int y, int width, int height, Color c) {
		super(x, y, c);
		rectWidth = width;
		rectHeight = height;
	}

	/**
	 * Get the width of the Rectangle
	 * 
	 * @return the width of the Rectangle
	 */
	public int getWidth() {
		return rectWidth;
	}

	/**
	 * Get the height of the Rectangle
	 * 
	 * @return the height of the Rectangle
	 */
	public int getHeight() {
		return rectHeight;
	}

	/**
	 * Draw the Rectangle on the graphics context.
	 * 
	 * @param g the Graphics context on which to draw the Point.
	 */
	public void draw(Graphics g) {
		g.setColor(super.getColor());
		g.drawRect(super.getX(), super.getY(), rectWidth, rectHeight);
		g.fillRect(super.getX(), super.getY(), rectWidth, rectHeight);
	}


	/**
	 * Scale the Rectangle's width and height by the specified factor. 
	 * For example a factor of 2.0 will double the width and height of the 
	 * Rectangle while a factor of 0.5 will make it half as large. 
	 * If the factor is negative, then nothing happens to the Rectangle.
	 * 
	 * 
	 * @param factor the amount by which this Rectangle is to be scaled.
	 */
	public void scale(double factor) {
		if (factor > 0) {
			rectWidth = (int) Math.round(factor * rectWidth);
			rectHeight = (int) Math.round(factor * rectHeight);
		}//end if statement
	}

}
