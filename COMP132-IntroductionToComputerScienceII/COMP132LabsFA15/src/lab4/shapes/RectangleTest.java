package lab04.shapes;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class RectangleTest {
	
	private Rectangle rect1;

	@Before
	public void setUp() throws Exception {
		rect1 = new Rectangle(3,2,8,10,Color.GREEN);
	}

	@Test
	public void testConstructor() {
		assertEquals("the x position of the rectangle should be at 3", 3, rect1.getX());
		assertEquals("the y position of the rectangle should be at 2", 2, rect1.getY());
		assertEquals("the width of the rectangle should be 8", 8, rect1.getWidth());
		assertEquals("the height of the rectangle should be 10", 10, rect1.getHeight());
		assertEquals("the color of the rectangle should be red", Color.GREEN, rect1.getColor());	
	}

	@Test
	public void testScale(){
		assertEquals("the width of the rectangle should be 8", 8, rect1.getWidth());
		assertEquals("the height of the rectangle should be 10", 10, rect1.getHeight());
		
		//scale larger
		rect1.scale(2);
		assertEquals("the width of the rectangle should be 16", 16, rect1.getWidth());
		assertEquals("the height of the rectangle should be 20", 20, rect1.getHeight());
		
		//scale smaller
		rect1.scale(0.5);
		assertEquals("the width of the rectangle should be 8", 8, rect1.getWidth());
		assertEquals("the height of the rectangle should be 16", 10, rect1.getHeight());
	}
}
