package lab04.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Text extends Point {

	private String text;	
	/**
	 * Creates a Text with the specified String and color at location (x,y)
	 * 
	 * @param str the String that will be created
	 * @param x the x value of the Text
	 * @param y the y value of the Text
	 * @param c the color of the Text
	 */

	public Text(String str, int x, int y, Color c) {
		super(x, y, c);
		text = str;
	}

	/**
	 * Get the String of the Text
	 * 
	 * @return the String of the Text
	 */

	public String getText() {
		return text;
	}

	/**
	 * Draw the Text on the graphics context.
	 * 
	 * Overrides the superclass implementation
	 * 
	 * @param g the Graphics context on which to draw the Line.
	 */
	public void draw(Graphics g) {
		g.setColor(super.getColor());
		g.drawString(text, super.getX(), super.getY());
	}

}
