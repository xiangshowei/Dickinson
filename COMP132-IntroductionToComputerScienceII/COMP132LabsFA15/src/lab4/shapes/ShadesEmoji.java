package lab04.shapes;

import java.awt.Color;

public class ShadesEmoji {

	/**
	 * Draws a round face wearing sunglasses
	 *  
	 * @param args none
	 */
	public static void main(String[] args) {
		DrawableObjectList shapesList = new DrawableObjectList();
		DrawingTablet tablet = new DrawingTablet("Face With Glasses", 500, 500, shapesList);
		
				
		Circle face = new Circle(25, 35, 450, 450, Color.YELLOW);
		shapesList.addDrawable(face);
		
		Ellipse leftLense = new Ellipse(75, 150, 150, 100, Color.GRAY);
		shapesList.addDrawable(leftLense);
		
		Ellipse rightLense = new Ellipse(275, 150, 150, 100, Color.GRAY);
		shapesList.addDrawable(rightLense);
		
		Line glassesFrame = new Line(225, 200, 275, 200, Color.BLACK);
		shapesList.addDrawable(glassesFrame);
		
		Square nose = new Square(230, 265, 50,50, Color.RED);
		shapesList.addDrawable(nose); 
		
		Rectangle mouth = new Rectangle(180, 350, 150, 75, Color.MAGENTA);
		shapesList.addDrawable(mouth);
		
		Text caption = new Text("Check out these shades though", 170, 390, Color.BLACK);
		shapesList.addDrawable(caption);
		
		tablet.repaint();
	}

}
