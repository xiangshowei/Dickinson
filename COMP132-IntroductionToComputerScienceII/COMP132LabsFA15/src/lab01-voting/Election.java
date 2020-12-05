package lab01.voting;

import java.util.ArrayList;
import java.util.Scanner;

public class Election {

	/**
	 * Prints the party affiliation of all of the candidates
	 * @param ballot the race that the candidates are running in
	 */
	public static void printCandidateAffiliation(Ballot ballot) {
		ArrayList<Candidate> runners = ballot.getAllCandidates();
		for(Candidate c : runners){
			System.out.println(c.getName() + " is from the " + c.getParty() + " party.");
		}
		System.out.println();
	}

	public static void main(String args[]) {
		Ballot ge = new Ballot("General Election");
		Candidate c1 = new Candidate("Jane", Candidate.DEMOCRAT);
		Candidate c2 = new Candidate("John", Candidate.REPUBLICAN);
		Candidate c3 = new Candidate("Sarah", Candidate.INDEPENDENT);
		Candidate c4 = new Candidate("Bob", Candidate.LIBERTARIAN);
		Candidate c5 = new Candidate("Jillian", Candidate.DEMOCRAT);
		Candidate c6 = new Candidate("Samuel", Candidate.REPUBLICAN);
		
		ge.addCandidate(c1);
		ge.addCandidate(c2);
		ge.addCandidate(c3);
		ge.addCandidate(c4);
		ge.addCandidate(c5);
		ge.addCandidate(c6);

		printCandidateAffiliation(ge);

		Scanner scr = new Scanner(System.in);
		
		System.out.print("Please vote for a candidate or a specific party: ");
		String inputName = scr.nextLine(); 
		
		Candidate candidate = ge.getCandidateByName(inputName);
		if(inputName.equals(candidate.getName())) {
			ge.voteForCandidate(inputName);
			System.out.println("You have just voted for " + candidate.getName());
			System.out.println(candidate.getName() + " has " + candidate.getNumVotes() + " number of vote(s)"  );
		}
		
		scr.close(); 
	}
}
