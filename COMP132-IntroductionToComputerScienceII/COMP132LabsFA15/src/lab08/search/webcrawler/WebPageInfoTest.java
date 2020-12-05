package lab08.search.webcrawler;


import static org.junit.Assert.*;

import org.junit.*;

public class WebPageInfoTest {

    private WebPageInfo wpi;
    
    @Before
    public void setUp() throws Exception {
        wpi = new WebPageInfo("Dickinson College", "http://www.dickinson.edu");
    }

    @Test
    public void testConstructor() {
        assertEquals("Wrong title", "Dickinson College", wpi.getTitle());
        assertEquals("Wrong URL", "http://www.dickinson.edu", wpi.getURL());
        assertEquals("Should have no terms", 0, wpi.getSearchTerms().size());
        assertEquals("Should have no links", 0, wpi.getLinkCount());
    }
    
    @Test
    public void testSetTitle() {
        wpi.setTitle("Dson");
        assertEquals("Wrong title", "Dson", wpi.getTitle());
    }
    
    @Test
    public void testAddSearchTerms() {
        wpi.addSearchTerm("Test");
        wpi.addSearchTerm("123");
        wpi.addSearchTerm("XYZ");
        
        assertEquals("Wrong number of terms", 3, wpi.getSearchTerms().size());
        
        assertEquals("Wrong term", "Test", wpi.getSearchTerms().get(0));
        assertEquals("Wrong term", "123", wpi.getSearchTerms().get(1));   
        assertEquals("Wrong term", "XYZ", wpi.getSearchTerms().get(2));
    }
    
    @Test
    public void testAddDuplicateTerms() {
        wpi.addSearchTerm("Test");
        wpi.addSearchTerm("123");
        wpi.addSearchTerm("Test");

        assertEquals("Wrong number of terms", 2, wpi.getSearchTerms().size());
        
        assertEquals("Wrong term", "Test", wpi.getSearchTerms().get(0));
        assertEquals("Wrong term", "123", wpi.getSearchTerms().get(1));   
    }
    
    @Test
    public void testIncrementLinkCount() {
        wpi.incrementLinkCount();
        wpi.incrementLinkCount();
        
        assertEquals("Wrong link count", 2, wpi.getLinkCount());
    }
    
    @Test
    public void testSetLinkCount() {
        wpi.setLinkCount(22);
        assertEquals("Wrong link count", 22, wpi.getLinkCount());
    }
    
    @Test
    public void testEqualsSameURL() {
        WebPageInfo wpi2 = new WebPageInfo("Dickinson College", "http://www.dickinson.edu");
        assertTrue("Should be equal.", wpi.equals(wpi2));
    }
    
    @Test
    public void testEqualsDifferentURL() {
        WebPageInfo wpi2 = new WebPageInfo("Google", "http://www.google.com");
        assertFalse("Should not be equal.", wpi.equals(wpi2));
    }
    
    @Test
    public void testEqualsDifferentObject() {
        assertFalse("Should not be equal.", wpi.equals("This is a String"));
    }
}
