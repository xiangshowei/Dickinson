package lab08.search.webcrawler;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class WebPageListTest {

    private WebPageList wpl;
    private WebPageInfo wpi1;
    private WebPageInfo wpi2;
    private WebPageInfo wpi3;
    private WebPageInfo wpi4;
    private WebPageInfo wpi5;

    @Before
    public void setUp() throws Exception {
        wpl = new WebPageList();
        wpi1 = new WebPageInfo("Dickinson College", "http://www.dickinson.edu");
        wpi2 = new WebPageInfo("Gettysburg", "http://www.gettysburg.edu");
        wpi3 = new WebPageInfo("F&M", "http://www.fandm.edu");
        wpi4 = new WebPageInfo("Dickinson College", "http://www.dickinson.edu");
        wpi5 = new WebPageInfo("Bucknell", "http://www.bucknell.edu");
    }

    @Test
    public void testConstructor() {
        assertEquals("should be empty list", 0, wpl.getNumPages());
    }

    @Test
    public void testAddGetPage() {
        wpl.addPage(wpi1);
        wpl.addPage(wpi2);
        wpl.addPage(wpi3);

        assertEquals("should have 3 elments.", 3, wpl.getNumPages());
        assertSame("get not working", wpi1, wpl.getPage(wpi1.getURL()));
        assertSame("get not working", wpi2, wpl.getPage(wpi2.getURL()));
        assertSame("get not working", wpi3, wpl.getPage(wpi3.getURL()));
    }

    @Test
    public void testAddExistingPageDistinctObject() {
        wpi1.addSearchTerm("one");
        wpi1.addSearchTerm("two");
        wpi1.incrementLinkCount();
        wpi1.incrementLinkCount();

        wpi4.addSearchTerm("three");
        wpi4.addSearchTerm("four");
        wpi4.incrementLinkCount();
        wpi4.incrementLinkCount();
        wpi4.incrementLinkCount();

        wpl.addPage(wpi1);
        wpl.addPage(wpi2);
        wpl.addPage(wpi3);
        wpl.addPage(wpi4);

        assertEquals("should have 3 elments.", 3, wpl.getNumPages());

        WebPageInfo wpi = wpl.getPage(wpi4.getURL());
        assertEquals("Search terms not added", 4, wpi.getSearchTerms().size());
        assertTrue("Search term not right", wpi.getSearchTerms().contains("one"));
        assertTrue("Search term not right", wpi.getSearchTerms().contains("two"));
        assertTrue("Search term not right", wpi.getSearchTerms().contains("three"));
        assertTrue("Search term not right", wpi.getSearchTerms().contains("four"));
        assertEquals("Wong number of incoming links", 5, wpi.getLinkCount());
    }
   
    @Test
    public void testGetNonExistentPage() {
        wpl.addPage(wpi1);
        wpl.addPage(wpi2);

        assertNull("get not working for nonexistent page", wpl.getPage(wpi3.getURL()));
    }

    @Test
    public void testRemoveFirstPage() {
        wpl.addPage(wpi1);
        wpl.addPage(wpi2);
        wpl.addPage(wpi3);

        assertEquals("wrong first element", wpi1, wpl.removeFirstPage());
        assertEquals("wrong second element", wpi2, wpl.removeFirstPage());
        assertEquals("wrong third element", wpi3, wpl.removeFirstPage());
    }

    @Test
    public void testRemoveFirstPageEmptyList() {
        try {
            wpl.removeFirstPage();
            fail("Should fail on empty list.");
        }
        catch (IllegalStateException e) {
            // test passes.
        }

        wpl.addPage(wpi1);
        wpl.addPage(wpi2);
        wpl.addPage(wpi3);
        wpl.removeFirstPage();
        wpl.removeFirstPage();
        wpl.removeFirstPage();

        try {
            wpl.removeFirstPage();
            fail("Should fail on empty list.");
        }
        catch (IllegalStateException e) {
            // test passes.
        }
    }

    @Test
    public void testWriteToFile() throws FileNotFoundException {      
        wpl.addPage(wpi1);
        wpl.addPage(wpi2);
        wpl.addPage(wpi3);
        wpl.addPage(wpi5);

        wpi1.addSearchTerm("one");
        wpi1.addSearchTerm("two");
        wpi1.incrementLinkCount();
        wpi1.incrementLinkCount();

        wpi2.addSearchTerm("three");
        wpi2.addSearchTerm("four");
        wpi2.addSearchTerm("five");
        wpi2.incrementLinkCount();
        wpi2.incrementLinkCount();
        wpi2.incrementLinkCount();
        wpi2.incrementLinkCount();

        wpi3.addSearchTerm("six");
        wpi3.addSearchTerm("seven");
        wpi3.incrementLinkCount();

        wpl.writeToFile("/var/tmp/WPLTest.txt");
        File f = new File("/var/tmp/WPLTest.txt");
        f.deleteOnExit();

        Scanner scr = new Scanner(new FileInputStream("/var/tmp/WPLTest.txt"));

        assertEquals("Wrong title 1", wpi1.getTitle(), scr.nextLine());
        assertEquals("wrong url 1", wpi1.getURL(), scr.nextLine());
        assertEquals("wrong terms 1", "one,two", scr.nextLine());
        assertEquals("wrong link count 1", "2", scr.nextLine());

        assertEquals("Wrong title 2", wpi2.getTitle(), scr.nextLine());
        assertEquals("wrong url 2", wpi2.getURL(), scr.nextLine());
        assertEquals("wrong terms 2", "three,four,five", scr.nextLine());
        assertEquals("wrong link count 2", "4", scr.nextLine());

        assertEquals("Wrong title 3", wpi3.getTitle(), scr.nextLine());
        assertEquals("wrong url 3", wpi3.getURL(), scr.nextLine());
        assertEquals("wrong terms 3", "six,seven", scr.nextLine());
        assertEquals("wrong link count 3", "1", scr.nextLine());
        
        assertEquals("Wrong title 5", wpi5.getTitle(), scr.nextLine());
        assertEquals("wrong url 5", wpi5.getURL(), scr.nextLine());
        assertEquals("wrong terms 5", "", scr.nextLine());
        assertEquals("wrong link count 5", "0", scr.nextLine());
    }

    @Test
    public void testWriteToFileBadFilename() {
        try {
            wpl.writeToFile("/BadDirectoryNameThatShouldntExist/WPLTest.txt");
            fail("Should throw FileNotFoundException if file cannot be opened.");
        }
        catch (FileNotFoundException e) {
            // test passes.
        }
    }
}
