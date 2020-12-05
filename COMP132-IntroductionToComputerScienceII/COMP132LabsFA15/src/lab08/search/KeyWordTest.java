package lab08.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KeyWordTest {
	
	private KeyWord kw;
	private KeyWord kw2;

	@Before
	public void setUp() throws Exception {
		kw = new KeyWord("stuff");
		kw2 = new KeyWord("things");
	}

	@Test
	public void testConstructor() {
		assertEquals("the keyword should be stuff", "stuff", kw.getWord());
		assertTrue("the ArrayList of PageInfo should be empty", kw.getPages().isEmpty());
	}
	
	@Test
	public void testAddPageKeywordAlreadyExist() {
		PageInfo pi1 = new PageInfo("school website", "www.dickinson.edu", 0);
		PageInfo pi2 = new PageInfo("school site", "www.dickinson.edu", 100);
		kw.addPage(pi1);
		kw.addPage(pi2);
		assertEquals("the ArrayList should have one PageInfo object", 1, kw.getPages().size());
		
	}
	
	@Test
	public void testGetPages() {
		PageInfo pi1 = new PageInfo("school website", "www.dickinson.edu", 0);
		kw.addPage(pi1);
		assertTrue("the ArrayList should contain pi1", kw.getPages().contains(pi1));
	}
	
	@Test
	public void testCompareToBefore() {
		assertEquals("the comparison should return -1", -1, kw.compareTo(kw2));
	}
	
	@Test
	public void testCompareToAfter() {
		assertEquals("the comparison should return 1", 1, kw2.compareTo(kw));
	}

}
