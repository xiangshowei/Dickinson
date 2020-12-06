package lab1.voting;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BallotTest {

	private Ballot ballot; 
	private Candidate bob;
	private Candidate sarah;
	
	@Before
    public void setUp() throws Exception {
		bob = new Candidate("Bob", Candidate.Party.INDEPENDENT);
		sarah = new Candidate("Sarah", Candidate.Party.LIBERTARIAN);

		ballot = new Ballot("DistrictJudge");
		ballot.addCandidate(bob);
		ballot.addCandidate(sarah);
	}
	
	@After
	public void tearDown() {

	}
	
	@Test
	public void testConstructor() {
		assertEquals("DistrictJudge", ballot.getElectionTitle());
		assertEquals(2, ballot.getNumCandidates());

		assertEquals(bob, ballot.getCandidateByName("Bob"));
		assertEquals(sarah, ballot.getCandidateByName("Sarah"));
		assertEquals(null, ballot.getCandidateByName("Joe"));
	}
	
	@Test
	public void testAddCandidate(){
		assertEquals(2, ballot.getNumCandidates());
		ballot.addCandidate(new Candidate("Joe", Candidate.Party.DEMOCRAT));
		assertEquals(3, ballot.getNumCandidates());
	}
	
	@Test
	public void testVoteForCandidate() {
		assertEquals(0, sarah.getNumVotes());
		ballot.voteForCandidate("Sarah");
		assertEquals(1, sarah.getNumVotes());	
	}
	
	@Test
	public void testVoteStraightTicket() {
		Candidate jack = new Candidate("Jack", Candidate.Party.LIBERTARIAN);
		ballot.addCandidate(jack);

		assertEquals(0, bob.getNumVotes());
		assertEquals(0, sarah.getNumVotes());
		assertEquals(0, jack.getNumVotes());	
		
		ballot.voteStraightTicket(Candidate.Party.LIBERTARIAN);

		assertEquals(0, bob.getNumVotes());
		assertEquals(1, sarah.getNumVotes());
		assertEquals(1, jack.getNumVotes());
	}
}
