package lab04.shapes;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class LineTest {
	
	 private Line line;

	@Before
	public void setUp() throws Exception {
		//straight line of distance 5 from point one to point two
		line = new Line(0, 0, 0, 5, Color.CYAN);
	}
	
	@Test
	public void testConstructor() {
		assertEquals("l1's x1 coordinate should be at 0", 0, line.getX());
		assertEquals("l1's y1 coordiante should be at 0", 0, line.getY());
		assertEquals("the line's x2 coordinate should be at 0", 0, line.getX2());
		assertEquals("the line's y2 coordiante should be at 5", 5, line.getY2());
		assertEquals("the line's color should be cyan", Color.CYAN, line.getColor());
	}

	@Test
	public void testMove() {
		//line's first coordinate before being moved
		assertEquals("l1's x1 coordinate should be at 0", 0, line.getX());
		assertEquals("l1's y1 coordiante should be at 0", 0, line.getY());

		//line's second coordinate before being moved
		assertEquals("l1's x2 coordinate should be at 0", 0, line.getX2());
		assertEquals("l1's y2 coordiante should be at 5", 5, line.getY2());
	
		line.move(5, 5);
		//line's first coordinate after being moved
		assertEquals("the line's x1 coordinate should be at 5", 5, line.getX());
		assertEquals("the line's y1 coordiante should be at 5", 5, line.getY());
		
		//line's second coordinate after being moved
		assertEquals("the line's x2 coordinate should be at 5", 5, line.getX2());
		assertEquals("the line's y2 coordiante should be at 10", 10, line.getY2());
	}

	@Test
	public void testTranslate() {
		//starts off at (0,0) to (0,5)
		assertEquals("l1's x1 coordinate should be at 0", 0, line.getX());
		assertEquals("l1's y1 coordiante should be at 0", 0, line.getY());
		assertEquals("l1's x2 coordinate should be at 0", 0, line.getX2());
		assertEquals("l1's y2 coordiante should be at 5", 5, line.getY2());
		
		//positive translate
		line.translate(3, 3);
		assertEquals("l1's x1 coordinate should be at 3", 3, line.getX());
		assertEquals("l1's y1 coordiante should be at 3", 3, line.getY());
		assertEquals("l1's x2 coordinate should be at 3", 3, line.getX2());
		assertEquals("l1's y2 coordiante should be at 8", 8, line.getY2());
		
		//negative translate
		line.translate(-2,-2);
		assertEquals("l1's x coordinate should be at 1", 1, line.getX());
		assertEquals("l1's y coordiante should be at 1", 1, line.getY());
		assertEquals("l1's x coordinate should be at 1", 1, line.getX2());
		assertEquals("l1's y coordiante should be at 6", 6, line.getY2());
	}
}
