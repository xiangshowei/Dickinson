package golf;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * The test class GolfScoreCardTest.
 *
 * @author Xiang Wei
 * @version 7/23/2016
 */
public class GolfScoreCardTest {

	private GolfScoreCard gsc;

	/**
	 * Sets up the test fixture.
	 *
	 * Called before every test case method.
	 */
	
	@Before
	public void setUp() {
        gsc = new GolfScoreCard("Bob", 2);
        gsc.setHoleScore(0, 1);
        gsc.setHoleScore(1, 2);
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
		assertEquals("Bob", gsc.getPlayersName());
		assertEquals(2, gsc.getNumHoles());
        assertEquals(0, gsc.getTotalScore());
        assertEquals(1, gsc.getHoleScore(0));
        assertEquals(3, gsc.getTotalScore());

        assertEquals(-1, gsc.getHoleScore(-7));
    }

    @Test
	public void testHasAce() {
        assertEquals(true, gsc.hasAce());
        gsc.setHoleScore(0, 3);
        assertEquals(false, gsc.hasAce());
    }

    @Test
	public void testPlayWithProfessionals() {
        assertEquals(true, gsc.canPlayWithProfessionals());
        gsc.setHoleScore(0, 10);
        assertEquals(false, gsc.canPlayWithProfessionals());
    }

    @Test
	public void testGetIndexOfMaxScore() {
        assertEquals(1, gsc.getIndexOfMaxScore());

        gsc.setHoleScore(0, 10);
        assertEquals(0, gsc.getIndexOfMaxScore());
    }

    @Test
	public void testAdjustScoresForHandicap() {
        gsc.adjustScoresForHandicap(1);
        assertEquals(1, gsc.getHoleScore(0));
        assertEquals(1, gsc.getHoleScore(1));
    }

    @Test
	public void testCheat() {
        gsc.setHoleScore(0, 10);
        gsc.setHoleScore(1, 5);
        GolfScoreCard gsc2 = gsc.cheat(2);

        assertEquals(8, gsc2.getHoleScore(0));
        assertEquals(5, gsc2.getHoleScore(1));
    }
}