package lab03.shapes;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class LineTest {
	
	 private Line l1;

	@Before
	public void setUp() throws Exception {
		//straight line of distance 5
		l1 = new Line(0, 0, 0, 5, Color.CYAN);
	}
	
	@Test
	public void testConstructor() {
		assertEquals("l1's x1 coordinate should be at 0", 0, l1.getX1());
		assertEquals("l1's y1 coordiante should be at 0", 0, l1.getY1());
		assertEquals("l1's x2 coordinate should be at 0", 0, l1.getX2());
		assertEquals("l1's y2 coordiante should be at 5", 5, l1.getY2());
		assertEquals("l1's color should be cyan", Color.CYAN, l1.getColor());
	}

	@Test
	public void testMove() {
		//(x1,y1)
		assertEquals("l1's x1 coordinate should be at 0", 0, l1.getX1());
		assertEquals("l1's y1 coordiante should be at 0", 0, l1.getY1());
		//(x2,y2)
		assertEquals("l1's x2 coordinate should be at 0", 0, l1.getX2());
		assertEquals("l1's y2 coordiante should be at 5", 5, l1.getY2());
	
		l1.move(5, 5);
		
		//(x1,y1)
		assertEquals("l1's x1 coordinate should be at 5", 5, l1.getX1());
		assertEquals("l1's y1 coordiante should be at 5", 5, l1.getY1());
		//(x2,y2)
		assertEquals("l1's x2 coordinate should be at 5", 5, l1.getX2());
		assertEquals("l1's y2 coordiante should be at 10", 10, l1.getY2());
	}

	@Test
	public void testTranslate() {
		//starts off at (0,0) to (0,5)
		assertEquals("l1's x1 coordinate should be at 0", 0, l1.getX1());
		assertEquals("l1's y1 coordiante should be at 0", 0, l1.getY1());
		assertEquals("l1's x2 coordinate should be at 0", 0, l1.getX2());
		assertEquals("l1's y2 coordiante should be at 5", 5, l1.getY2());
		
		//positive translate
		l1.translate(3, 3);
		assertEquals("l1's x1 coordinate should be at 3", 3, l1.getX1());
		assertEquals("l1's y1 coordiante should be at 3", 3, l1.getY1());
		assertEquals("l1's x2 coordinate should be at 3", 3, l1.getX2());
		assertEquals("l1's y2 coordiante should be at 8", 8, l1.getY2());
		
		
		//negative translate
		l1.translate(-2,-2);
		assertEquals("l1's x coordinate should be at 1", 1, l1.getX1());
		assertEquals("l1's y coordiante should be at 1", 1, l1.getY1());
		assertEquals("l1's x coordinate should be at 1", 1, l1.getX2());
		assertEquals("l1's y coordiante should be at 6", 6, l1.getY2());
	}
	
	@Test
	public void testGetSlope() {
		Line l2 = new Line(1, 2, 10, 9, Color.CYAN);
		int slope = (int) Math.round(7/9);
		assertEquals("the slop of the line should be 7/9", slope, l2.getSlope());
	}
}
