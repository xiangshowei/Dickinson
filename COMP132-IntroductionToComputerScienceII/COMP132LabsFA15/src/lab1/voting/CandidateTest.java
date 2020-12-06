package lab1.voting;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CandidateTest {

    /*
     * Candidate for the test fixture. This variable is a field and is thus
     * available in all of the tests.
     */
    private Candidate joeD;
    private Candidate bobI;
    private Candidate sarahL;
    private Candidate janeR;
    
    @Before
    public void setUp() {
        joeD = new Candidate("Joe", Candidate.Party.DEMOCRAT, 10);
        bobI = new Candidate("Bob", Candidate.Party.INDEPENDENT, 8);
        sarahL = new Candidate("Sarah", Candidate.Party.LIBERTARIAN, 8);
        janeR = new Candidate("Jane", Candidate.Party.REPUBLICAN, 10);
    }

    @After
    public void tearDown () {

    }

    @Test
    public void testConstructor() {
        assertEquals("Joe", joeD.getName());
        assertEquals(Candidate.Party.DEMOCRAT, joeD.getParty());
        assertEquals(10, joeD.getNumVotes()); 
    }

    @Test
    public void testIncreaseVotes() {
        assertEquals(10, joeD.getNumVotes()); 
        joeD.increaseVoteByOne();
        assertEquals(11, joeD.getNumVotes());
        joeD.increaseVotes(5);
        assertEquals(16, joeD.getNumVotes()); 
    }
    
    @Test
    public void testDefeated() {
    	assertEquals(10, joeD.getNumVotes());
        assertEquals(8, bobI.getNumVotes());
        assertEquals(true, joeD.hasMoreVotesThan(bobI));
        assertEquals(false, bobI.hasMoreVotesThan(joeD));
        assertEquals(false, bobI.hasMoreVotesThan(janeR));
    }
    
    @Test
    public void testIsSamePartyAs() {
        assertEquals(false, sarahL.isSamePartyAs(bobI));
        sarahL.setParty(Candidate.Party.INDEPENDENT);
        assertEquals(true, sarahL.isSamePartyAs(bobI));
    }
}
