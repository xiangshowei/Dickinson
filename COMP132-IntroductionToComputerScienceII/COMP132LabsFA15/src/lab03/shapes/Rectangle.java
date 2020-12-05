package lab03.shapes;

import java.awt.Color;
import java.awt.Graphics;


public class Rectangle implements Drawable, Scaleable {

	private int x;
	private int y;
	private int height;
	private int width;
	private double perimeter;
	private double area;
	private Color color;
	private boolean visible;
	
	/**
	 * A Rectangle is an object representing a rectangle. A Rectangle has a starting point
	 * (x,y),height, width, and color. It can be moved, translated, scaled and drawn.
	 */

	public Rectangle(int x, int y, int height, int width, Color color) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		perimeter =(2*this.height) + (2*this.width);;
		area = this.height * this.width;
		this.color = color;
		visible = true;
	}

	 /**
     * Get the x coordinate of the Rectangle.
     * 
     * @return the x coordinate of the Rectangle
     */
	public int getX() {
		return x;
	}

	/**
     * Get the y coordinate of the Rectangle.
     * 
     * @return the y coordinate of the Rectangle
     */
	public int getY() {
		return y;
	}

	/**
     * Get the height of the Rectangle.
     * 
     * @return the height of the Rectangle
     */
	public int getHeight() {
		return height;
	}
	
	/**
     * Get the width of the Rectangle.
     * 
     * @return the width of the Rectangle
     */
	public int getWidth() {
		return width;
	}
	
	/**
     * Get the area of the Rectangle. An error will be displayed if
     * a negative value was entered for either height or width of 
     * the Rectangle.
     * 
     * @return the area of the Rectangle
     */
	public double getArea() {
		if((height < 0) || (width < 0)){
			System.out.println("Dimensions of the rectangle cannot be negative");
		}
		return area;
	}
	
	/**
     * Get the perimeter of the Rectangle. An error will be displayed if
     * a negative value was entered for either height or width of 
     * the Rectangle.
     * 
     * @return the perimeter of the Rectangle
     */
	public double getPerimeter() {
		if((height < 0) || (width < 0)){
			System.out.println("Dimensions of the rectangle cannot be negative");
		}
		return perimeter;
	}

	/**
	 * Move the Rectangle to a new location. This method changes the center point
	 * of the Rectangle using the values provided by the parameters.
	 * 
	 * @param newX the new x coordinate
	 * @param newY the new y coordinate
	 */
	public void move(int newX, int newY) {
		x = newX;
		y = newY;
	}

	/**
	 * Move the Rectangle relative to its current location. The center of the
	 * Rectangle is moved by adding the parameters to the Rectangle's current
	 * location.
	 * 
	 * @param dX the change in the x coordinate. Positive values move the
	 *            Rectangle to the right, negative values move it to the left.
	 * @param dY the change in the y coordinate. Positive values move the
	 *            Rectangle down, negative values move it up.
	 */
	public void translate(int dX, int dY) {
		x += dX;
		y += dY;
	}

	/**
     * Scale the Rectangle's perimeter and area by the specified factor. 
     * For example a factor of 2.0 will make the perimeter twice as big and
     * a factor of 0.5 will make it half as large. For example, a factor of 2.0 
     * will double both the height and width of the Rectangle and thus the area
     * will be (2.0*height) * (2.0*width) or (factor*factor*area). 
     *  
     * If the factor is negative, then the perimeter and area are not changed.
     * 
     * @param factor the factor by which this Rectangle is to be scaled.
     */
	@Override
	public void scale(double factor) {
		if (factor > 0) {
			height = (int)Math.round(factor*height);
			width = (int)Math.round(factor* width);
			perimeter = factor*perimeter;
			area *= (factor*factor); //===> area = area * (factor*factor)
		}
	}

	/**
     * Draw this DrawableRectangle onto the specified Graphics object.
     * 
     * @param g the Graphics object on which to draw this DrawableRectangle.
     */
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawRect(x, y, width, height);
		g.fillRect(x, y, width, height);
	}

	 /**
     * Get the Color of this Rectangle.
     * 
     * @return the color.
     */
	@Override
	public Color getColor() {
		return color;
	}

	/**
     * Change the color of this Rectangle to the newColor.
     * 
     * @param newColor the new color.
     */
	@Override
	public void setColor(Color newColor) {
		color = newColor;
	}

	 /**
     * Set whether or not this Rectangle will be visible. If it is visible its draw
     * method will be invoked when the DrawingTablet is repainted. If it is not
     * visible its draw method will not be invoked.
     * 
     * @param visible true to make this Rectangle visible, false to make it
     *            invisible.
     */
	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
     * Find out if this Rectangle is visible or not.
     * 
     * @return true if the Rectangle is visible, false if it is not.
     */
	@Override
	public boolean isVisible() {
		return visible;
	}

}
