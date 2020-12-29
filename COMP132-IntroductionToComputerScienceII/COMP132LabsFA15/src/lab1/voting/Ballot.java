package lab1.voting;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import lab1.voting.Candidate.Party;

public class Ballot {

	private String electionTitle;
	private HashMap<String, Candidate> candidates;

	/**
	 * Constructs a ballot for an election with the candidates running in said
	 * election.
	 * 
	 * @param electionTitle the name of the election
	 */

	public Ballot(String electionTitle) {
		this.electionTitle = electionTitle;
		candidates = new HashMap<String, Candidate>();
	}

	/**
	 * Gets the name of the election being held
	 * 
	 * @return the name of the election
	 */
	public String getElectionTitle() {
		return electionTitle;
	}

	/**
	 * Gets the number of candidates that are running in the election
	 * 
	 * @return the number of candidates in the election
	 */
	public int getNumCandidates() {
		return candidates.size();
	}

	/**
	 * Adds a candidate to the ballot
	 * 
	 * @param newCandidate the candidate to be added to the ballot
	 */
	public void addCandidate(Candidate newCandidate) {
		candidates.put(newCandidate.getName(), newCandidate);
	}

	/**
	 * Gets the candidate running the ballot by name
	 * 
	 * @param name the of the Candidate on the ballot
	 * @return the Candidate on the ballot. If the Candidate is not on the ballot,
	 *         this method returns null
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
	 * 
	 * @param party the party being voted for
	 */
	public void voteStraightTicket(Party party) {

		Iterator<Entry<String, Candidate>> candidatesIterator = candidates.entrySet().iterator();

		while (candidatesIterator.hasNext()) {
			Candidate candidate = candidatesIterator.next().getValue();
			if (candidate.getParty().equals(party)) {
				candidate.increaseVoteByOne();
			}
		}
	}

	/**
	 * Gets all of the candidates on the ballot
	 * 
	 * @return the ArrayList containing all of the candidates
	 */
	public HashMap<String, Candidate> getAllCandidates() {
		return candidates;
	}

	public static void printCandidatesOnBallot(Ballot ballot) {
		Iterator<Entry<String, Candidate>> ballotIterator = ballot.getAllCandidates().entrySet().iterator();

		System.out.println();
		System.out.println("Candidates and their affiliated party:");
		System.out.println();

		while (ballotIterator.hasNext()) {
			Candidate candidate = ballotIterator.next().getValue();
			System.out.println(candidate.getName() + "	: " + candidate.getParty());
		}

		System.out.println();
	}

	public static void printPartiesOnBallot(Ballot ballot) {
		HashSet<Party> partiesOnBallot = new HashSet<Party>();
		Iterator<Entry<String, Candidate>> allCandidatesIterator = ballot.getAllCandidates().entrySet().iterator();
		StringBuilder sb = new StringBuilder("Parties on this ballot: \n");

		while (allCandidatesIterator.hasNext()) {
			Party party = allCandidatesIterator.next().getValue().getParty();
			if (partiesOnBallot.add(party)) {
				sb.append(party.name() + "\n");
			}
		}

		System.out.println();
		System.out.println(sb.toString());
	}

	public static void printBallotSummary(Ballot ballot, Party party) {
		Iterator<Entry<String, Candidate>> candidatesIterator = ballot.getAllCandidates().entrySet().iterator();

		StringBuilder sb = new StringBuilder("You voted for: \n");

		while (candidatesIterator.hasNext()) {
			Candidate candidate = candidatesIterator.next().getValue();

			if (candidate.getParty().equals(party)) {
				sb.append(candidate.getName() + "\n");
			}
		}

		System.out.println();
		System.out.println(sb.toString());
	}

	private static HashMap<Integer, HashSet<Candidate>> getTiedCandidates(Ballot ballot) {
		HashMap<Integer, HashSet<Candidate>> tiedCandidatesByVote = new HashMap<Integer, HashSet<Candidate>>();
		Iterator<Entry<String, Candidate>> ballotIterator = ballot.getAllCandidates().entrySet().iterator();
		while(ballotIterator.hasNext()) {
			Candidate candidate = ballotIterator.next().getValue();
			
			//no candidate with same number of votes exists
			if(tiedCandidatesByVote.get(candidate.getNumVotes()) == null) {
				HashSet<Candidate> candidatesByVoteCount = new HashSet<Candidate>();
				candidatesByVoteCount.add(candidate);
				tiedCandidatesByVote.put(candidate.getNumVotes(), candidatesByVoteCount);
			}

			//add to existing HashSet
			else {
				HashSet<Candidate> existingTieCandidates = tiedCandidatesByVote.get(candidate.getNumVotes());
				existingTieCandidates.add(candidate);
			}
		}

		return tiedCandidatesByVote;
	}

	public static void printTiedCandidates(Ballot ballot) {
		System.out.println();
		System.out.println("Candidates with tied votes: ");
		System.out.println();
		
		Iterator<Entry<Integer, HashSet<Candidate>>> tiedCandidatesByVoteIterator = getTiedCandidates(ballot).entrySet().iterator();

		while (tiedCandidatesByVoteIterator.hasNext()) {
			//getting the entry so the pointer to the next doesn't get advanced each time a method is called on top of next()
			Entry<Integer, HashSet<Candidate>> entry = tiedCandidatesByVoteIterator.next();
			HashSet<Candidate> candidatesByVoteCount = entry.getValue();
			
			if(candidatesByVoteCount.size() > 1) {
				System.out.println("Votes: " + entry.getKey());
				System.out.println("----------");

				for (Candidate candidate : candidatesByVoteCount) {
					System.out.println(candidate.getName());
				}
			}

			System.out.println();
		}	
	}
}