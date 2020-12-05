package lab07.recursion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PyramidBoxCountTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBaseCase1() {
		int box1 = PyramidBoxCount.computePyramidBoxes(1);
		assertEquals("a base of one should have 1 box total", 1, box1);
	}
	
	@Test
	public void testBaseCase2() {
		int box2 = PyramidBoxCount.computePyramidBoxes(2);
		assertEquals("a base of 2 should have 2 boxes total", 2, box2);
	}
	
	@Test
	public void testRecursiveCaseOddNumberBase(){
		int box5 = PyramidBoxCount.computePyramidBoxes(5);
		assertEquals("a base of 5 should have 9 boxes total", 9, box5);
	}

	@Test
	public void testRecursiveCaseEvenNumberBase(){
		int box8 = PyramidBoxCount.computePyramidBoxes(8);
		assertEquals("a base of 8 should have 20 boxes total", 20, box8);	
	}
}
