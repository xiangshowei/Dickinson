package lab1.voting;

import java.util.EnumSet;
import java.util.Scanner;

import lab1.voting.Candidate.Party;

public class Election {
	public static void main(String args[]) {
		Ballot djBallot = setup();
		boolean done = false;
		Scanner scr = new Scanner(System.in);
		
		printVotingOptions();
		
		while (!done) {
			String input = scr.nextLine();
			
			if (input.equalsIgnoreCase("e") || input.equalsIgnoreCase("exit")) {
				done = true;
			}
			
			else { 
				// vote by candidate name
				if (input.equals("c") || input.equals("candidate")) {
					Ballot.printCandidatesOnBallot(djBallot);
					
					System.out.print("Please indicate which candidate to vote for: ");
					
					String candidateName = scr.nextLine();
					
					try {
						Candidate candidate = djBallot.getCandidateByName(candidateName);
						
						if (candidate.getName().equals(candidateName)) {
							djBallot.voteForCandidate(candidateName);
							done = true;
							
							System.out.println();
							System.out.println("You voted for " + candidate.getName());
							System.out.println();
						}
					}
					
					catch (NullPointerException npe) {
						System.out.println();
						System.err.println("\"" + candidateName + "\"" + " is not a valid candidate.");
						printVotingOptions();
					}	
				}
				
				// straight ticket vote
				else if(input.equals("p") || input.equals("party")) {
					Ballot.printPartiesOnBallot(djBallot);
					
					System.out.print("Please indicate which party to vote for: ");
					
					EnumSet<Party> politicalAffilication = EnumSet.allOf(Party.class);
					String party = scr.nextLine();
					
					try {
						Party partyToVoteFor = Party.valueOf(party);
						
						if (politicalAffilication.contains(partyToVoteFor)) {
							djBallot.voteStraightTicket(partyToVoteFor);
							Ballot.printBallotSummary(djBallot, partyToVoteFor);
							
							done = true;
						}
						
					} catch (IllegalArgumentException iae) {
						System.out.println();
						System.err.println("\"" + party + "\"" + " is not a valid party.");
						printVotingOptions();
					}
				}

				else if(input.equals("t") || input.equals("tie")) {
					Ballot.printTiedCandidates(djBallot);
					printVotingOptions();
				}
				
				else {
					printVotingOptions();
				}
			}
		}
		scr.close();
	}

	private static Ballot setup() {
		Ballot ballot = new Ballot("DistrictJudge");
		Candidate c1 = new Candidate("Alex", Candidate.Party.DEMOCRAT);
		Candidate c2 = new Candidate("Bob", Candidate.Party.LIBERTARIAN);
		Candidate c3 = new Candidate("Carlos", Candidate.Party.INDEPENDENT);
		Candidate c4 = new Candidate("Diane", Candidate.Party.REPUBLICAN);
		Candidate c5 = new Candidate("Jane", Candidate.Party.LIBERTARIAN);

		ballot.addCandidate(c1);
		ballot.addCandidate(c2);
		ballot.addCandidate(c3);
		ballot.addCandidate(c4);
		ballot.addCandidate(c5);

		return ballot;
	}

	private static void printVotingOptions() {
		System.out.println();
		System.out.print("Please specify if you want to vote by (c)andidate name, (p)arty affiliation, view candidates with (t)ie votes, or (e)xit: ");
	}
}