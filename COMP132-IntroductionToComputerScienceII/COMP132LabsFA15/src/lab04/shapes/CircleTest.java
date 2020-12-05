package lab04.shapes;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class CircleTest {
	private Circle circ1;
	private Circle circ2;
	

	@Before
	public void setUp() throws Exception {
		circ1 = new Circle(5,8,10,10,Color.GREEN);
		circ2 = new Circle(10,16,10,15,Color.BLUE);
	}

	@Test
	public void testConstructor() {
		assertEquals("the x coordinate of the circle should be at 5", 5, circ1.getX());
		assertEquals("the x coordinate of the circle should be at 8", 8, circ1.getY());
		assertEquals("the width of the circle should be 10", 10, circ1.getWidth());
		assertEquals("the height of the circle should be 10", 10, circ1.getHeight());
		assertEquals("the color of the circle should be green", Color.GREEN, circ1.getColor());
		
		//similar to Square is to Rectangle, Circle is to Ellipse such that only the 
		//width of the Circle matters in creating a Circle
		assertEquals("the x coordinate of the circle should be at 10", 10, circ2.getX());
		assertEquals("the x coordinate of the circle should be at 16", 16, circ2.getY());
		assertEquals("the width of the circle should be 10", 10, circ2.getWidth());
		assertEquals("the height of the circle should be 10", 10, circ2.getHeight());
		assertEquals("the color of the circle should be blue", Color.BLUE, circ2.getColor());
		
	}

}
