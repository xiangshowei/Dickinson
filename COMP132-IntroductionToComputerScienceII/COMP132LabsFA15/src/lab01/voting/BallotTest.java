package lab01.voting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BallotTest {

	private Ballot b1; 
	private Candidate joe;
	
	@Before
    public void setUp() throws Exception {
        /*
         * Construct all of the objects being used in the test fixture here.
         */
		joe = new Candidate("Joe", Candidate.DEMOCRAT);
        b1 = new Ballot("Astronomy Club");
  
    }
	
	@Test
	public void testConstructor() {
		assertEquals("the astronomy club should be having an election", "Astronomy Club", b1.getElection());
		assertEquals("the number of candidates running for the astronomy club should be zero", 0, b1.getNumCandidates());
	}
	
	@Test
	public void testAddCandidate(){
		assertEquals("the number of candidates running for the astronomy club should be zero", 0, b1.getNumCandidates());
		b1.addCandidate(joe);
		assertEquals("the number of candidates running for the astronomy club should be one", 1, b1.getNumCandidates());
	}
	
	@Test 
	public void testGetCandidateNull(){
		b1.addCandidate(joe);
		assertEquals("the number of candidates running for the astronomy club should be one", 1, b1.getNumCandidates());
		assertNull("Bob should NOT exist as a Candidate option", b1.getCandidate("Bob"));
	}
	
	@Test
	public void testGetCandidateSuccessful(){
		b1.addCandidate(joe); 
		assertSame("Joe(c1) should be a candidate in the ballot", joe, b1.getCandidate("Joe"));
	}
	
	@Test
	public void testVoteForCandidateSuccessful() {
		b1.addCandidate(joe);
		assertEquals("Joe should have no votes", 0, joe.getVotes());
		b1.voteForCandidate("Joe");
		assertEquals("Joe should have one vote", 1, joe.getVotes());		
	}
	
	@Test
	public void testVoteForCandidateError() {
		b1.addCandidate(joe);
		assertEquals("Joe should have no votes", 0, joe.getVotes());
		assertEquals("There should be one candidate running in this election", 1, b1.getNumCandidates());
		b1.voteForCandidate("Jack");
	}
	
	@Test
	public void voteStraightTicketSuccessful() {
		Candidate c2 = new Candidate("Robert", Candidate.DEMOCRAT, 2);
		Candidate c3 = new Candidate("Sue", Candidate.DEMOCRAT, 9);
		Candidate c4 = new Candidate("Vivian", Candidate.LIBERTARIAN, 5);
		b1.addCandidate(joe);
		b1.addCandidate(c2);
		b1.addCandidate(c3);
		b1.addCandidate(c4);
		b1.voteStraightTicket(Candidate.DEMOCRAT);
		assertEquals("Joe should have one vote", 1, joe.getVotes());
		assertEquals("Robert should have 3 votes", 3, c2.getVotes());
		assertEquals("Sue should have 10", 10, c3.getVotes());
		assertEquals("Vivian's number of votes should NOT have changed", 5, c4.getVotes());
	}
	
	@Test
	public void voteStraightTicketPartyNOTonBallot(){
		b1.addCandidate(joe);
		b1.voteStraightTicket(Candidate.INDEPENDENT);
	}
	
}
