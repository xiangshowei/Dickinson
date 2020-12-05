package lab08.search.webcrawler;

import static org.junit.Assert.*;

import java.net.*;
import java.util.ArrayList;

import org.htmlparser.Tag;
import org.htmlparser.tags.*;
import org.htmlparser.util.ParserException;
import org.junit.*;
import org.osjava.norbert.NoRobotException;

public class HTMLTagExtractorTest {

    private HTMLTagExtractor p2;

    @Before
    public void setUp() throws Exception {


        URL testFile = HTMLTagExtractor.class.getResource("TestPage.html");
        p2 = new HTMLTagExtractor(testFile.toExternalForm());
    }

    @Test
    public void testGetTitle() {
        try {
            ArrayList<String> t = p2.getTitle();
            assertEquals("Wrong first word", "The", t.get(0));
            assertEquals("Wrong second word", "Title", t.get(1));
        }
        catch (ParserException e) {
            fail("Unexpected exception when getting title");
        }
    }

    @Test
    public void testGetKeywords() {
        try {
            ArrayList<String> kw = p2.getKeyWords();
            assertEquals("unexpected keyword", "one", kw.get(0));
            assertEquals("unexpected keyword", "two", kw.get(1));
            assertEquals("unexpected keyword", "three", kw.get(2));
            assertEquals("unexpected keyword", "four", kw.get(3));
        }
        catch (ParserException e) {
            fail("Unexpected exception when getting keywords");
        }
    }

    @Test
    public void testGetLinks() {
        try {
            ArrayList<Link> t = p2.getLinks();

            Link l1 = t.get(0);
            assertTrue("Link 1 wrong link", l1.getURL().endsWith("link1URL"));
            assertTrue("Link 1 no protocol", l1.getURL().startsWith("file:/"));
            assertEquals("Link 1 wrong text", "Link Number 1", l1.getText());

            Link l2 = t.get(1);
            assertTrue("Link 2 wrong link", l2.getURL().endsWith("link2URL"));
            assertEquals("Link 2 wrong text", "Link Number 2", l2.getText());
            
            Link l3 = t.get(2);
            assertTrue("Link 3 wrong link", l3.getURL().equalsIgnoreCase("http://one.two.three.edu/link3URL"));
            assertEquals("Link 3 wrong text", "Link Number 3", l3.getText());
        }
        catch (ParserException e) {
            fail("Unexpected exception when getting Links");
        }
    }
    
    @Test
    public void testGetLinkHasFullURL() throws MalformedURLException, ParserException, NoRobotException {
        HTMLTagExtractor p = new HTMLTagExtractor("http://www2.dickinson.edu");
        
        try {
            ArrayList<Link> t = p.getLinks();
            for (Link l : t) {             
                assertTrue("Link does not have full path: " + l.getURL(), 
                        l.getURL().startsWith("http://www2.dickinson.edu/") ||
                        l.getURL().startsWith("http://www.dickinson.edu/") ||
                        l.getURL().startsWith("http://lis.dickinson.edu/") );
            }
        }
        catch (ParserException e) {
            fail("Unexpected exception when getting Links");
        }
    }

    @Test
    public void testGetTagTypeLink() {
        try {
            ArrayList<Tag> tags = p2.getTags("LinkTag");
            assertEquals("wrong number of tags", 3, tags.size());

            LinkTag l1 = (LinkTag) tags.get(0);
            assertTrue("Link 1 wrong link", l1.getLink().endsWith("link1URL"));
            assertEquals("Link 1 wrong text", "Link Number 1", l1.getLinkText());

            LinkTag l2 = (LinkTag) tags.get(1);
            assertTrue("Link 2 wrong link", l2.getLink().endsWith("link2URL"));
            assertEquals("Link 2 wrong text", "Link Number 2", l2.getLinkText());
            
            LinkTag l3 = (LinkTag) tags.get(2);
            assertTrue("Link 3 wrong link", l3.getLink().endsWith("link3URL"));
            assertEquals("Link 3 wrong text", "Link Number 3", l3.getLinkText());
        }
        catch (ParserException e) {
            fail("Unexpected exception when getting LinkTags");
        }
    }

    @Test
    public void testGetTagTypeParagraph() {
        try {
            ArrayList<Tag> tags = p2.getTags("ParagraphTag");
            assertEquals("wrong number of tags", 2, tags.size());

            ParagraphTag p1 = (ParagraphTag) tags.get(0);
            assertEquals("Para 1 wrong text", "Some text here.", p1.getStringText());

            ParagraphTag p2 = (ParagraphTag) tags.get(1);
            assertEquals("Para 2 wrong text", "Some more text here.", p2.getStringText());
        }
        catch (ParserException e) {
            fail("Unexpected exception when getting paragraphs");
        }
    }
    
    @Test
    public void testNoRobotsPage() throws MalformedURLException, ParserException {
        try {
            new HTMLTagExtractor("http://www2.dickinson.edu/compass/menuday.cfm");
            fail("Should fail - no robots allowed on that page.");
        }
        catch(NoRobotException e) {
            // test passes.
        }
    }
}
