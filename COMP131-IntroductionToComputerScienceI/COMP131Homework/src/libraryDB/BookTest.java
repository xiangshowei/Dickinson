package libraryDB;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BookTest.
 *
 * @author  Xiang Wei
 * @version (a version number or a date)
 */
public class BookTest {
    private Book standing;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
	@Before
    public void setUp() {
        standing = new Book("Standing in a River Waving a Stick", "John Gierach", "QA76.709.A 1996", "Fly Fishing", 1996, 247);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
	@After
    public void tearDown(){
    }

	@Test
	public void testConstructor(){
		assertEquals("Standing in a River Waving a Stick", standing.getTitle());
		assertEquals("John Gierach", standing.getAuthor());
		assertEquals("QA76.709.A 1996", standing.getCallNumber());
		assertEquals("Fly Fishing", standing.getSubject());
		assertEquals(1996, standing.getPublicationYear());
		assertEquals(false, standing.isOld());
		assertEquals(247, standing.getNumPages());
		assertEquals(false, standing.isCheckedOut());
	}

	@Test
	public void testCheckOut() {
		standing.checkOutBook();
		assertEquals(true, standing.isCheckedOut());
	}

	@Test
	public void testReturnBook() {
		standing.returnBook();
		assertEquals(false, standing.isCheckedOut());
	}
}