package lab03.shapes;

import java.awt.Color;

public class MickyMouseLollipop {

	/**
	 * Draws a Mickey Mouse shaped lollipop with a box shaped face 
	 * @param args
	 */
	public static void main(String[] args) {
		DrawableObjectList objList = new DrawableObjectList();
        DrawingTablet tablet = new DrawingTablet("Mickey Mouse Lollipop", 200, 200, objList);
        
        Circle leftEar = new Circle(75,50, 20, Color.BLACK);
        objList.addDrawable(leftEar); 
        
        Circle rightEar = new Circle(125,50,20, Color.BLACK);
        objList.addDrawable(rightEar); 
        
        Rectangle face = new Rectangle(75,50, 50, 50, Color.ORANGE);
        objList.addDrawable(face);

        Line body = new Line(100, 100, 100, 150, Color.MAGENTA);
        objList.addDrawable(body);
        
        tablet.repaint();
	}

}
