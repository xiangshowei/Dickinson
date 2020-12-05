package lab03.shapes;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class RectangleTest {
	private Rectangle rect1;
	private Rectangle rect2;
	private Rectangle rect3;

	@Before
	public void setUp() throws Exception {
		rect1 = new Rectangle(3, 2, 2, 3, Color.GREEN);
		rect2 = new Rectangle(10, 15, -2, 3, Color.GREEN);
		rect3 = new Rectangle(15, 20, 2, -3, Color.GREEN);
	}

	@Test
	public void testConstructor() {
		assertEquals("the x position of the rectangle should be at 3", 3, rect1.getX());
		assertEquals("the y position of the rectangle should be at 2", 2, rect1.getY());
		assertEquals("the height of the rectangle should be 2", 2, rect1.getHeight());
		assertEquals("the width of the rectangle should be 3", 3, rect1.getWidth());
		assertEquals("the perimeter of the rectangle should be 10", 10, (int)rect1.getPerimeter());
		assertEquals("the area of the rectangle should be 10", 6, (int)rect1.getArea());
		assertEquals("the color of the rectangle should be green", Color.GREEN, rect1.getColor());
		assertTrue("the rectangle should be visible", rect1.isVisible());
	}

	@Test
	public void testGetPerimterError() {//prints error message
		rect2.getPerimeter();
		rect3.getPerimeter();
	}

	@Test
	public void testGetAreaError() { //prints error message
		rect2.getArea();
		rect3.getArea();
	}

	@Test
	public void testMove() {
		assertEquals("the x position of the rectangle should be at 3", 3, rect1.getX());
		assertEquals("the y position of the rectangle should be at 2", 2, rect1.getY());
		//moving to position (5,7)
		rect1.move(5, 7);
		assertEquals("the x position of the rectangle should be at 5", 5, rect1.getX());
		assertEquals("the y position of the rectangle should be at 7", 7, rect1.getY());
		//moving to position (1,1)
		rect1.move(1, 1);
		assertEquals("the x position of the rectangle should be at 1", 1, rect1.getX());
		assertEquals("the y position of the rectangle should be at 1", 1, rect1.getY());
	}

	@Test
	public void testTranslate(){
		assertEquals("the x position of the rectangle should be at 3", 3, rect1.getX());
		assertEquals("the y position of the rectangle should be at 2", 2, rect1.getY());
		//positive translation
		rect1.translate(2, 1);
		assertEquals("the x position of the rectangle should be at 5", 5, rect1.getX());
		assertEquals("the y position of the rectangle should be at 3", 3, rect1.getY());
		//negative translation
		rect1.translate(-5, -2);
		assertEquals("the x position of the rectangle should be at 0", 0, rect1.getX());
		assertEquals("the y position of the rectangle should be at 1", 1, rect1.getY());
	}

	@Test
	public void testScale() {
		assertEquals("the x position of the rectangle should be at 3", 3, rect1.getX());
		assertEquals("the y position of the rectangle should be at 2", 2, rect1.getY());

		rect1.scale(2);
		assertEquals("the height of the rectangle should be 4", 4, rect1.getHeight());
		assertEquals("the width of the rectangle should be 6", 6, rect1.getWidth());
		assertEquals("the perimeter of the rectangle should be 20", 20, (int)rect1.getPerimeter());
		assertEquals("the area of the rectangle should be 24", 24, (int)rect1.getArea());

		rect1.scale(0.5);
		assertEquals("the height of the rectangle should be 2", 2, rect1.getHeight());
		assertEquals("the width of the rectangle should be 3", 3, rect1.getWidth());
		assertEquals("the perimeter of the rectangle should be 10", 10, (int)rect1.getPerimeter());
		assertEquals("the area of the rectangle should be 6", 6, (int)rect1.getArea());
	}

	@Test
	public void testSetColor() {
		assertEquals("the color of the rectangle should be green", Color.GREEN, rect1.getColor());
		rect1.setColor(Color.BLUE);
		assertEquals("the color of the rectangle should be green", Color.BLUE, rect1.getColor());
	}
	
	@Test
	public void testSetVisibility() {
		assertTrue("the rectangle should be visible", rect1.isVisible());
		rect1.setVisible(false);
		assertFalse("the rectangle should be visible", rect1.isVisible());
	}

}
