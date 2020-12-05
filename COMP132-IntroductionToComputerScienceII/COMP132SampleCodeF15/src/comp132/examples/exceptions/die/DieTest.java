package comp132.examples.exceptions.die;

import static org.junit.Assert.*;

import org.junit.*;

public class DieTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNotEnoughSides() {
		try {
			// Do something that should throw an exception.
			new Die(3);

			/*
			 * If the expected exception was thrown then execution would have
			 * jumped to the catch block. Because that didn't happen, we know
			 * the exception was not thrown and this test should fail.
			 */
			fail("Expected exception was not thrown.");
		} catch (IllegalArgumentException e) {
			/*
			 * We caught the expected type of exception. So the test should
			 * pass. Any test that does not fail an assertion passes. So doing
			 * nothing in here allows the test to pass.
			 */
		} catch (Exception e) {
			/*
			 * An exception was thrown but it was not an
			 * IllegalArgumentException as was expected. So this test should
			 * fail under this circumstance.
			 */
			fail("Incorrect exception type caught.");
		}
	}
}
