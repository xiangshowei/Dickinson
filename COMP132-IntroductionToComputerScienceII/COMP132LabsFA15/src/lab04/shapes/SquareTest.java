package lab04.shapes;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class SquareTest {
	
	private Square sqr1;
	private Square sqr2;
	
	@Before
	public void setUp() throws Exception {
		sqr1 = new Square(10, 10, 15, 15, Color.RED);
		sqr2 = new Square(10, 10, 15, 20, Color.RED);
	}

	@Test
	public void testConstructor() {
		assertEquals("the x position of the square should be at 3", 10, sqr1.getX());
		assertEquals("the y position of the square should be at 2", 10, sqr1.getY());
		assertEquals("the width of the square should be 15", 15, sqr1.getWidth());
		assertEquals("the height of the square should be 15", 15, sqr1.getHeight());
		assertEquals("the color of the square should be red", Color.RED, sqr1.getColor());	
	}
	
	@Test
	public void testConstructor2() {
		//testing to see that the argument passed in for height does not matter
		assertEquals("the x position of the square should be at 3", 10, sqr2.getX());
		assertEquals("the y position of the square should be at 2", 10, sqr2.getY());
		assertEquals("the width of the square should be 15", 15, sqr2.getWidth());
		assertEquals("the height of the square should be 15", 15, sqr2.getHeight());
		assertEquals("the color of the square should be red", Color.RED, sqr2.getColor());	
	}

}
