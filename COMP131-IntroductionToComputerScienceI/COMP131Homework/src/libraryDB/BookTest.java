package libraryDB;

/**
 * The test class BookTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BookTest extends junit.framework.TestCase
{
    private Book standing;
    
    /**
     * Default constructor for test class BookTest
     */
    public BookTest()
    { 
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        standing = new Book("Standing in a River Waving a Stick", "John Gierach", "QA76.709.A 1996", "Fly Fishing", 1996, 247);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

	public void testConstructor()
	{
		assertEquals("hint: title not set correctly or getTitle not working",
		             "Standing in a River Waving a Stick", standing.getTitle());
		assertEquals("hint: author not set correctly or getAuthor not working",
		             "John Gierach", standing.getAuthor());
		assertEquals("hint: call number not set correctly or getCallNumber not working",
		             "QA76.709.A 1996", standing.getCallNumber());
		assertEquals("hint: subject not set correctly or getSubject not working",
		             "Fly Fishing", standing.getSubject());
		assertEquals("hint: publication year not set correctly or getPublicationYear not working",
		             1996, standing.getPublicationYear());
		assertEquals("hint: number of pages not set correctly or getNumPages not working",
		             247, standing.getNumPages());
		assertEquals("hint: a newly created book is not checked out (or isCheckedOut not working)",
		             false, standing.isCheckedOut());
	}

	public void testCheckOut()
	{
		standing.checkOutBook();
		assertEquals("hint: checkOutBook or isCheckedOut not working correctly",
		              true, standing.isCheckedOut());
	}

	public void testCheckOutCheckedOut()
	{
		standing.checkOutBook();
		standing.checkOutBook();
		assertEquals("hint: checkOutBook is not working for a book that is already checked out or isCheckedOut not working correctly",
		              true, standing.isCheckedOut());
    }

	public void testReturnBook()
	{
		standing.checkOutBook();
		standing.returnBook();
		assertEquals("hint: returnBook or isCheckedOut not working correctly",
		             false, standing.isCheckedOut());
	}

	public void testReturnBookNotCheckedOut()
	{
		standing.returnBook();
		assertEquals("hint: returnBook is not working for a book that is not checked out or isCheckedOut not working correctly",
		             false, standing.isCheckedOut());
	}

	public void testPrintNotCheckedOut()
	{
		standing.print();
	}

	public void testPrintCheckedOut()
	{
	    standing.checkOutBook();
		standing.print();
	}

    public void testNew()
    {
        standing.getTitle();
        assertEquals("Fly Fishing", standing.getSubject());
        Book book1 = new Book("Harry", "Harry", "Harry", "Math", 1994, 1994);
        assertEquals("Math", book1.getSubject());
        Book book2 = new Book("Harry", "Harry", "Harry", "Computer Science", 1994, 1994);
        assertEquals("Computer Science", book2.getSubject());
        Book book3 = new Book("Harry", "Harry", "Harry", "History", 1994, 1994);
        assertEquals("History", book3.getSubject());
    }

    public void testIsOld()
    {
        assertEquals(false, standing.isOld());
        Book book1 = new Book("Harry", "Harry", "Harry", "Harry", 1994, 1994);
        assertEquals(false, book1.isOld());
        Book book2 = new Book("Harry", "Harry", "Harry", "Harry", 200, 200);
        assertEquals(true, book2.isOld());
    }
}










