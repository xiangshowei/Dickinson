package lab08.search;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class KeyWordListTest {

	private static final String FILE = "www.dickinson.edu.index";
	private KeyWordList kwl;
	private KeyWordList kwl2;
	String [] keyWords;
	ArrayList<PageInfo> pageInfoForKW;

	@Before
	public void setUp() throws Exception {
		kwl = new KeyWordList();
		kwl2 = new KeyWordList(FILE);
	}

	@Test
	public void testFirstConstructor() {
		assertTrue("the first constructor should be an empty ArrayList", kwl.getKeyWords().isEmpty());
	}

	@Test 
	public void testSecondConstructor(){
		assertEquals("the ArrayList should have 32 keywords", 32, kwl2.getKeyWords().size());
		assertEquals("the first keyword should be Dickinson", "Dickinson", kwl2.getKeyWords().get(0).getWord()); 
		assertEquals("the 30th keyword should be Skip", "Skip", kwl2.getKeyWords().get(29).getWord());	
		assertEquals("Dickinson keyword should have 2 PageInfo objects", 2, kwl2.getKeyWords().get(0).getPages().size());
	}

	@Test
	public void testSort() {
		assertEquals("the first keyword should be Dickinson before sorting", "Dickinson", kwl2.getKeyWords().get(0).getWord()); 
		kwl2.sort();
		assertEquals("the ArrayList should now have abroad as its first keyword after being sorted", "abroad", kwl2.getKeyWords().get(0).getWord());
	}

	@Test
	public void testSearch() {
		ArrayList<PageInfo> pages = kwl2.search("Dickinson");
		//both links should have the KeyWord for "Dickinson"
		assertEquals("The ArrayList should contain the PageInfo object with URL http://www.dickinson.edu/",
				"http://www.dickinson.edu/", pages.get(1).getURL());
		assertEquals("The ArrayList should contain the PageInfo object with URL http://www.dickinson.edu/#content",
				"http://www.dickinson.edu/#content", pages.get(0).getURL());
	}

	@Test
	public void testSearchAllOnePageInfo() {
		keyWords = new String[] {"Dickinson", "Skip"};
		pageInfoForKW = kwl2.searchAll(keyWords);
		assertEquals("The ArrayList with only both key words should only contain one Page Info", 1, pageInfoForKW.size());
		assertEquals("The ArrayList should contain the PageInfo object with the URL http://www.dickinson.edu/#content", 
				"http://www.dickinson.edu/#content", pageInfoForKW.get(0).getURL());
	}

	@Test
	public void testSearchAllMultiplePageInfo() {
		keyWords = new String[] {"Dickinson", "arts"};
		pageInfoForKW = kwl2.searchAll(keyWords);
		assertEquals("The ArrayList should contain two pages", 2, pageInfoForKW.size());
		//both links should have both of the keywords
		assertEquals("The ArrayList should contain the PageInfo object with the URL http://www.dickinson.edu/#content", 
				"http://www.dickinson.edu/#content", pageInfoForKW.get(0).getURL());
		assertEquals("The ArrayList should contain the PageInfo object with URL http://www.dickinson.edu/",
				"http://www.dickinson.edu/", pageInfoForKW.get(1).getURL());
	}

	@Test
	public void testSearchAny() {
		keyWords = new String[] {"Dickinson", "Skip"};
		pageInfoForKW = kwl2.searchAny(keyWords);
		assertEquals("The ArrayList should contain two pages", 2, pageInfoForKW.size());
		assertEquals("The ArrayList should contain the PageInfo object with the URL http://www.dickinson.edu/#content", 
				"http://www.dickinson.edu/#content", pageInfoForKW.get(0).getURL());
		assertEquals("The ArrayList should contain the PageInfo object with URL http://www.dickinson.edu/",
				"http://www.dickinson.edu/", pageInfoForKW.get(1).getURL());
	}

}
