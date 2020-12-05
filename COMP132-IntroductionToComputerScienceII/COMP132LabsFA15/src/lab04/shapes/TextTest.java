package lab04.shapes;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class TextTest {
	
	private Text t1;

	@Before
	public void setUp() throws Exception {
		t1 = new Text("new text", 5, 5, Color.RED);
	}

	@Test
	public void testConstructor() {
		assertEquals("the string should be 'new text'", "new text", t1.getText());
		assertEquals("the x position of the text should be at 5", 5, t1.getX());
		assertEquals("the x position of the text should be at 5", 5, t1.getY());	
		assertEquals("the color of the text should be red", Color.RED, t1.getColor());	
	}
	
//	@Test
//	public void testMove() {
//		assertEquals("the x position of the text should be at 5", 5, t1.getX());
//		assertEquals("the x position of the text should be at 5", 5, t1.getY());
//		t1.move(3, 2);
//		assertEquals("the x position of the text should be at 3", 3, t1.getX());
//		assertEquals("the x position of the text should be at 2", 2, t1.getY());
//	}
//	
//	@Test
//	public void testTranslate() {
//		assertEquals("the x position of the text should be at 5", 5, t1.getX());
//		assertEquals("the x position of the text should be at 5", 5, t1.getY());
//		t1.translate(15, 20);
//		assertEquals("the x position of the text should be at 20", 20, t1.getX());
//		assertEquals("the x position of the text should be at 25", 25, t1.getY());
//	}

}
