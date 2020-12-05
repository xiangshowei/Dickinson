package lab04.shapes;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class EllipseTest {
	
	private Ellipse elli1;

	@Before
	public void setUp() throws Exception {
		elli1 = new Ellipse(15, 30, 45, 60, Color.GRAY);
	}

	@Test
	public void testConstructor() {
		assertEquals("the x position of the rectangle should be at 15", 15, elli1.getX());
		assertEquals("the y position of the rectangle should be at 30", 30, elli1.getY());
		assertEquals("the width of the rectangle should be 45", 45, elli1.getWidth());
		assertEquals("the height of the rectangle should be 60", 60, elli1.getHeight());
		assertEquals("the color of the ellipse should be gray", Color.GRAY, elli1.getColor());
		
	}
	
	@Test
	public void testScale(){
		assertEquals("the width of the rectangle should be 45", 45, elli1.getWidth());
		assertEquals("the height of the rectangle should be 60", 60, elli1.getHeight());

		//scale larger
		elli1.scale(2);
		assertEquals("the width of the rectangle should be 90", 90, elli1.getWidth());
		assertEquals("the height of the rectangle should be 120", 120, elli1.getHeight());

		//scale smaller
		elli1.scale(0.5);
		assertEquals("the width of the rectangle should be 45", 45, elli1.getWidth());
		assertEquals("the height of the rectangle should be 60", 60, elli1.getHeight());
	}

}
