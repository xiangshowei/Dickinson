package lab07.recursion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArraySumTest {
	
	private int[] oneElement;
	private int[] multElements;

	@Before
	public void setUp() throws Exception {
		oneElement = new int[] {5};
		multElements = new int[] {5,6,7};
	}

	@Test
	public void testBaseCase() {
		int arrSumOneElem = ArraySum.arraySum(oneElement);
		assertEquals("the sum of the Array should just be the value of the first element", 5, arrSumOneElem);
	}
	
	@Test
	public void testRecrusiveCase() {
		int arrSumMultElems = ArraySum.arraySum(multElements);
		assertEquals("the sum of the Array should just be the value of the first element", 18, arrSumMultElems);
	}

}
