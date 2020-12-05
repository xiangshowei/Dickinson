package lab07.recursion;

import javax.swing.*;
import java.awt.*;

/**
 * Draw a Sierpinski Triangle of a given order on a JPanel.
 * 
 * @author Grant Braught
 * 
 * @author Xiang Wei
 * @version 11/10/15
 */
public class SierpinskiPanel extends JPanel {

    private static final int WIDTH = 810;
    private static final int HEIGHT = 830;
    
    private int order;

    /**
     * Construct a new SierpinskiPanel.
     */
    public SierpinskiPanel(int order) {
        
        this.order = order;
        
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Draw an inverted triangle at the specified location on this JPanel.
     * 
     * @param x the x coordinate of the upper left corner of the triangle
     * @param y the y coordinate of the upper left corner of the triangle
     * @param size the width and height of the triangle
     * @param g the graphics object on which to draw the triangle
     */
    private void drawTriangle(int x, int y, int size, Graphics g) {
        Polygon triangle = new Polygon();
        triangle.addPoint(x, y);
        triangle.addPoint(x + size / 2, y + size);
        triangle.addPoint(x + size, y);
        g.fillPolygon(triangle);
    }
    
    /**
     * Draw a Sierpinski triangle of the specified order at the specified location
     * on this JPanel.
     * 
     * @param order the order of the Sierpinski triangle to draw.
     * @param x the x coordinate of the upper left corner of the central order 1 triangle
     * @param y the y coordinate of the upper left corner of the central order 1 triangle
     * @param size the width and height of the triangle
     * @param g the graphics object on which to draw the triangle
     */
    private void drawSierpinski(int order, int x, int y, int size, Graphics g) {
        if(order == 1) {
        	drawTriangle(x, y, size, g);
        }// end if statement
        else{
        	//original triangle
        	drawTriangle(x, y, size, g);
        	//top triangle 
        	drawSierpinski(order - 1, x + (size/4), y - size/2, size/2, g);
        	//left triangle
        	drawSierpinski(order - 1, x - (size/4), y + size/2, size/2, g);
        	//right triangle
        	drawSierpinski(order - 1, x + (3 * size/4), y + size/2, size/2, g);
        }// end else statement
    }

    /**
     * Override the paintComponent method so that each time the JPanel is painted on
     * the screen we can draw the Sierpinski Triangle on the JPanel.
     * 
     * @param g the graphics object on which to draw the triangle.
     */
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        drawSierpinski(order, 205, 425, 400, g);
    }

    /*
     * NOTE: Some code adopted/adapted from an earlier COMP132 lab created by
     * Tim Wahls who in turn borrowed some of the code from Robert Sedgewick.
     */
}
