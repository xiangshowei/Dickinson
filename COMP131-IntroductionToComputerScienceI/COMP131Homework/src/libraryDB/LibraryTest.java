package libraryDB;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LibraryTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LibraryTest
{
    /**
     * Default constructor for test class LibraryTest
     */
    public LibraryTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testLibrary()
    {
        Library library1 = new Library("Harry");
        Book book1 = new Book("haha", "Harry", "3456", "history", 1994, 100);
        library1.getBook("86");
        library1.addBook(book1);
        assertEquals(1, library1.getNumBooks());
        library1.getBook("3456");
        assertEquals("Harry", library1.getLibraryName());
        library1.deleteBookByAuthor("Harry");
        Book book2 = new Book("history", "Harry", "765", "history", 1995, 200);
        library1.addBook(book2);
        library1.addBook(book1);
        library1.getBook("3456");
        Book book3 = new Book("Harry", "Harry", "86", "history", 1994, 200);
        library1.addBook(book3);
        library1.getBook("3456");
    }

    @Test
    public void testCheck()
    {
        Library library1 = new Library("Harry");
        library1.checkOutBook("222");
        library1.returnBook("222");
        Book book1 = new Book("Harry", "Harry", "Harry", "Harry", 1994, 1994);
        library1.addBook(book1);
        library1.checkOutBook("Harry");
        library1.checkOutBook("Harry");
        library1.returnBook("Harry");
        library1.returnBook("Harry");
    }

    @Test
    public void testNum()
    {
        Library library1 = new Library("Harry");
        Book book1 = new Book("Harry", "Harry", "Harry", "History", 1994, 1995);
        Book book2 = new Book("Harry", "Harry", "Harry", "Computer Science", 1995, 1995);
        Book book3 = new Book("Harry", "Harry", "Harry", "Math", 200, 200);
        Book book4 = new Book("Harry", "Harry", "Harry", "Fly Fishing", 200, 1995);
        library1.addBook(book1);
        library1.addBook(book2);
        library1.addBook(book3);
        library1.addBook(book4);
        library1.countBooksBySubject();
        Book book5 = new Book("Fly Fishing", "Fly Fishing", "Fly Fishing", "Harry", 1995, 200);
        library1.addBook(book5);
        library1.countBooksBySubject();
    }


    @Test
    public void testAA()
    {
        Library library1 = new Library("Harry");
        Book book1 = new Book("Harry", "Harry", "Harry", "Math", 200, 200);
        Book book2 = new Book("Math", "Math", "lala", "Harry", 200, 100);
        Book book3 = new Book("lala", "lala", "lala", "Computer Science", 100, 1995);
        Book book4 = new Book("Computer Science", "Computer Science", "Computer Science", "History", 1995, 1995);
        library1.addBook(book1);
        library1.addBook(book2);
        library1.addBook(book3);
        library1.addBook(book4);
        library1.countBooksBySubject();
        library1.addBook(book2);
        library1.countBooksBySubject();
        library1.addBook(book3);
        library1.countBooksBySubject();
    }

    @Test
    public void testAAAAAAAA()
    {
        Library library1 = new Library("Harry");
        library1.print();
        Book book1 = new Book("Harry", "Harry", "Harry", "Harry", 1995, 1995);
        library1.addBook(book1);
        library1.print();
        assertEquals("Harry", library1.getSpecificCallNum("Harry"));
        assertEquals(true, library1.hasBookByAuthor("Harry"));
        library1.deleteBookByAuthor("Harry");
        library1.deleteBookByAuthor("Harry");
    }
}






