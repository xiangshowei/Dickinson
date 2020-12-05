package lab01.voting;

import java.util.ArrayList;

public class Ballot {
	
	private String electionName;
	private ArrayList<Candidate> candidates;
	
	/**
	 * Constructs a ballot for an election with the candidates running in said election.
	 * 
	 * @param elecName the name of the election
	 */
	
	public Ballot(String elecName) {
		electionName = elecName;
		candidates = new ArrayList<Candidate>(); 
	}
	
	/**
	 * Gets the name of the election being held
	 * @return the name of the election
	 */
	public String getElection() {
		return electionName;
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
		candidates.add(newCandidate);
	}
	
	/**
	 * Gets the candidate running the ballot by name
	 * @param name the of the Candidate on the ballot
	 * @return the Candidate on the ballot. If the Candidate is not on the ballot, this method returns null
	 */
	public Candidate getCandidate(String name) {
		Candidate targetCandidate = null;
//		for (int i = 0; i < candidates.size(); i++) {
//			if (candidates.get(i).getName().equals(name)) {
//				targetCandidate = candidates.get(i);
//			}//end if statement
//		}// end for loop
		
		for(Candidate c : candidates){
			if(c.getName().equalsIgnoreCase(name)){
				targetCandidate = c;
			}//end if statement
		}// end for each loop
		
		return targetCandidate;
	}
	
	
	/**
	 * Increases the number of votes for the specified candidate by one. 
	 * @param candidateName the candidate that is being voted for
	 */
	public void voteForCandidate(String candidateName) {
		for(Candidate c : candidates) {
			if(c.getName().equals(candidateName)){
				c.increaseVotes();
			}//  end if statement
		}//end for each loop
		
//		Candidate targetCandidate = null;
//		for(int i = 0; i < candidates.size(); i++) {
//			if(candidates.get(i).getName().equals(candidateName)) {
//				targetCandidate = candidates.get(i);
//				targetCandidate.increaseVotes();
//			}
//			else {
//				System.out.println(candidateName + " is not running in this election.");
//			}//end for loop
//		}
	}
	
	/**
	 * Candidates of the same party all receive one vote
	 * @param party the party being voted for
	 */
	public void voteStraightTicket(String party) {
		for(Candidate c : candidates) {
			if(!c.getParty().equals(party)){
				System.out.println("The " + c.getParty() + " party is NOT on the ballot");
			}
			else {
				c.increaseVotes();
			}//end if statement
		}//end for each loop
		
//		for(int i = 0; i < candidates.size(); i++) {
//			if(candidates.get(i).getParty().equals(party)){
//				candidates.get(i).increaseVotes();
//			}
//			else{
//				System.out.println("The " + candidates.get(i).getParty() + " party is NOT on the ballot");
//			}
//		}
	}
	
	/**
	 * Gets all of the candidates on the ballot
	 * @return the ArrayList containing all of the candidates
	 */
	public ArrayList<Candidate> getAllCandidates() {
		return candidates;
	}
	
}
