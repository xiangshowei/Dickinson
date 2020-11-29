package libraryDB;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LibraryTest.
 *
 * @author Xiang Wei
 * @version 11/20/2013
 */
public class LibraryTest {
    
    private Library lib1;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        lib1 = new Library("lib");
        lib1.addBook(new Book("bookTitle", "author", "C123.N456", Book.SUBJECT_HISTORY, 1993, 100));
        lib1.addBook(new Book("bookTitle2", "author2", "C123.N457", Book.SUBJECT_FLY_FISHING, 1985, 200));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertEquals("lib", lib1.getLibraryName());
        assertEquals(2, lib1.getNumBooks());
    }

    @Test
    public void testHasBookByAuthor() {
        assertEquals(true, lib1.hasBookByAuthor("author"));
        assertEquals(false, lib1.hasBookByAuthor("author3"));
    }
    
    @Test
    public void testRemoveBookByAuthor() {
        assertEquals(2, lib1.getNumBooks());
        lib1.removeBookByAuthor("author");
        assertEquals(false, lib1.hasBookByAuthor("author"));
        assertEquals(1, lib1.getNumBooks());
    }


    @Test
    public void testCheckOutBookAndReturnBook() {
        assertEquals(false, lib1.getBook("C123.N456").isCheckedOut());
        lib1.checkOutBook("C123.N456");
        assertEquals(true, lib1.getBook("C123.N456").isCheckedOut());
        lib1.returnBook("C123.N456");
        assertEquals(false, lib1.getBook("C123.N456").isCheckedOut());
    }
}






