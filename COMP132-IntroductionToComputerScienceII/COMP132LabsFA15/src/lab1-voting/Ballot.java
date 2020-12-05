package lab01.voting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Ballot {
	
	private String electionTitle;
	private HashMap<String, Candidate> candidates;
	
	/**
	 * Constructs a ballot for an election with the candidates running in said election.
	 * 
	 * @param electionTitle the name of the election
	 */
	
	public Ballot(String electionTitle) {
		this.electionTitle = electionTitle;
		candidates = new HashMap<String, Candidate>();
	}
	
	/**
	 * Gets the name of the election being held
	 * @return the name of the election
	 */
	public String getElectionTitle() {
		return electionTitle;
	}
	
	/**
	 * Gets the number of candidates that are running in the election
	 * @return the number of candidates in the election
	 */
	public int getNumCandidates() {
		return candidates.size();
	}
	
	/**
	 * Adds a candidate to the ballot
	 * @param newCandidate the candidate to be added to the ballot
	 */
	public void addCandidate(Candidate newCandidate){
		candidates.put(newCandidate.getName(), newCandidate);
	}
	
	/**
	 * Gets the candidate running the ballot by name
	 * @param name the of the Candidate on the ballot
	 * @return the Candidate on the ballot. If the Candidate is not on the ballot, this method returns null
	 */
	public Candidate getCandidateByName(String name) {		
		return candidates.get(name);
	}
	
	/**
	 * Increases the number of votes for the specified candidate by one. 
	 * 
	 * @param candidateName the candidate that is being voted for
	 */
	public void voteForCandidate(String candidateName) {
		candidates.get(candidateName).increaseVoteByOne();
	}
	
	/**
	 * Candidates of the same party all receive one vote
	 * @param party the party being voted for
	 */
	public void voteStraightTicket(String party) {

		Iterator<Entry<String, Candidate>> candidatesIterator = candidates.entrySet().iterator();

		while(candidatesIterator.hasNext()) {
			Candidate candidate = candidatesIterator.next().getValue();
			if(candidate.getParty().equals(party)) {
				candidate.increaseVoteByOne();
			}
		}
	}
	
	/**
	 * Gets all of the candidates on the ballot
	 * @return the ArrayList containing all of the candidates
	 */
	public HashMap<String, Candidate> getAllCandidates() {
		return candidates;
	}
}