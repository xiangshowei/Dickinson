package lab07.recursion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExponentiationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBaseCase1() {
		long exp05 = Exponentiation.exp(0, 5);
		assertEquals("an exponent with base of 0 should evaluate to 0", 0, exp05);
	}
	
	//n==0
	@Test
	public void testBaseCase2a(){
		long exp80 = Exponentiation.exp(8, 0);
		assertEquals("an exponent with power of 0 should evaluate to 1", 1, exp80);
	}
	
	//x == 1
	@Test
	public void testBaseCase2b(){
		long exp18 = Exponentiation.exp(1, 8);
		assertEquals("an exponent with base of 1 should evaluate to 1", 1, exp18);
	}
	
	@Test
	public void testRecursiveCaseEvenPower() {
		//even base and power
		long exp26 = Exponentiation.exp(2, 6);
		assertEquals("base 2 raised to power 6 should evaluate to 64", 64, exp26);
		
		//odd base, even power
		long exp34 = Exponentiation.exp(3, 4);
		assertEquals("base 3 raised to power 4 should evaluate to 81", 81, exp34);
	}
	
	@Test
	public void testRecursiveCaseOddPower() {
		//even base, odd power
		long exp27 = Exponentiation.exp(2, 7);
		assertEquals("base 2 raised to power 7 should evaluate to 128", 128, exp27);
		
		//odd base and power
		long exp33 = Exponentiation.exp(3, 3);
		assertEquals("base 3 raised to power 3 should evaluate to 27", 27, exp33);
	}

}
