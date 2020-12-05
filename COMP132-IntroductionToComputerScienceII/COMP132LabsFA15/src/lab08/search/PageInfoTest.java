package lab08.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PageInfoTest {

	private PageInfo pi;
	private PageInfo pi2;
	private PageInfo pi3;
	
	
	@Before
	public void setUp() throws Exception {
		pi = new PageInfo("school website", "www.dickinson.edu", 0);
		pi2 = new PageInfo("google", "www.google.com", 10);
		pi3 = new PageInfo("School site", "www.dickinson.edu", 10);
	}

	@Test
	public void testConstructor() {
		assertEquals("the title should be school website", "school website", pi.getTitle());
		assertEquals("the link should be www.dickinson.edu", "www.dickinson.edu", pi.getURL());
		assertEquals("the link count should be zero", 0, pi.getLinkCount());
	}
	
	@Test
	public void testEqualsFalse() {
		assertFalse("the two PageInfo objects should NOT have the same URL", pi.equals(pi2));
	}
	
	@Test
	public void testEqualsTrue() {
		assertTrue("the two PageInfo objects should have the same URL", pi.equals(pi3)); 
	}
	
	@Test
	public void testCompareToGreaterThan() {
		assertEquals("the comparison should return -1", -1, pi2.compareTo(pi));
	}
	
	@Test
	public void testCompareToLessThan() {
		assertEquals("the comparison should return 1", 1, pi.compareTo(pi2));
	}
	
	@Test
	public void testCompareToEqual() {
		assertEquals("the comparison should return -12", -12, pi2.compareTo(pi3));
	}
	
	@Test
	public void testCompareToSameObject() {
		assertEquals("the comparison should return 0", 0, pi2.compareTo(pi2));
	}

}
