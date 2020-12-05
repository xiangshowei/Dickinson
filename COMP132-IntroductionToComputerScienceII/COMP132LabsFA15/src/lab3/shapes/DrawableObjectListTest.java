package lab03.shapes;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class DrawableObjectListTest {

	private DrawableObjectList drawList;

	private Circle redCirc;
	private Circle orangeCirc;
	private Circle yellowCirc;
	private Circle greenCirc;
	private Circle cyanCirc;
	private Circle blueCirc;
	private Circle pinkCirc;
	private Circle grayCirc;
	private Circle blackCirc;
	private Circle whiteCirc;

	@Before
	public void setUp() throws Exception {
		drawList = new DrawableObjectList();

		redCirc =  new Circle(0, 0, 1, Color.RED);
		orangeCirc = new Circle(0, 0, 2, Color.ORANGE);
		yellowCirc = new Circle(0, 0, 3, Color.YELLOW);
		greenCirc = new Circle(0, 0, 4, Color.GREEN);
		cyanCirc = new Circle(0, 0, 5, Color.CYAN);
		blueCirc = new Circle(0, 0, 6, Color.BLUE);
		pinkCirc = new Circle(0, 0, 7, Color.PINK);
		grayCirc = new Circle(0, 0, 8, Color.GRAY);
		blackCirc = new Circle(0, 0, 9, Color.BLACK);
		whiteCirc = new Circle(0, 0, 10, Color.WHITE);
	}

	@Test
	public void testConstructor() {
		assertEquals("the list should be empty", 0, drawList.getSize());
	}

	@Test
	public void testAddDrawable()  {		
		drawList.addDrawable(redCirc);
		drawList.addDrawable(orangeCirc);
		drawList.addDrawable(yellowCirc);
		drawList.addDrawable(greenCirc);
		drawList.addDrawable(cyanCirc);
		drawList.addDrawable(blueCirc);
		drawList.addDrawable(pinkCirc);
		drawList.addDrawable(grayCirc);
		drawList.addDrawable(blackCirc);
		drawList.addDrawable(whiteCirc);
		assertEquals("the list should have 10 drawable objects", 10, drawList.getSize());
	}

	@Test
	public void testRemoveDrawable() {
		
		drawList.addDrawable(redCirc);
		drawList.addDrawable(blueCirc);
		drawList.addDrawable(yellowCirc);
		
		assertEquals("the list should have 3 drawable objects", 3, drawList.getSize());
		drawList.removeDrawable(redCirc);
		assertEquals("the list should be 2 drawable objects", 2, drawList.getSize());
	}
	
	@Test
	public void testScaleAll() {
	
		assertEquals("the red circle should have a radius of 1", 1, redCirc.getRadius());
		assertEquals("the orange circle should have a radius of 2", 2, orangeCirc.getRadius());
		assertEquals("the yellow circle should have a radius of 3", 3, yellowCirc.getRadius());
		assertEquals("the gray circle should have a radius of 8", 8, grayCirc.getRadius());
		assertEquals("the black circle should have a radius of 9", 9, blackCirc.getRadius());
		
		drawList.addDrawable(redCirc);
		drawList.addDrawable(orangeCirc);
		drawList.addDrawable(yellowCirc);
		drawList.addDrawable(grayCirc);
		drawList.addDrawable(blackCirc);
		
		//scaleAll() should still scale the circle by the specified factor 
		//even if the circle is not visible
		grayCirc.setVisible(false);
		blackCirc.setVisible(false);
		
		drawList.scaleAll(2);
		
		assertEquals("the red circle should have a radius of 2", 2, redCirc.getRadius());
		assertEquals("the orange circle should have a radius of 4", 4, orangeCirc.getRadius());
		assertEquals("the yellow circle should have a radius of 6", 6, yellowCirc.getRadius());
		assertEquals("the gray circle should have a radius of 16", 16, grayCirc.getRadius());
		assertEquals("the black circle should have a radius of 18", 18, blackCirc.getRadius());
	}


}
