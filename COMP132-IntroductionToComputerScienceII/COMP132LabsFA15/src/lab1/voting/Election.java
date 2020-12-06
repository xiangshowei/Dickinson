package lab1.voting;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

import lab1.voting.Candidate.Party;

public class Election {
	public static void main(String args[]) {
		Ballot djBallot = setup();
		boolean voteCasted = false;
		Scanner scr = new Scanner(System.in);
		
		printVotingOptions();
		
		while (!voteCasted) {
			String input = scr.nextLine();
			
			if (input.equalsIgnoreCase("e") || input.equalsIgnoreCase("exit")) {
				voteCasted = true;
			}
			
			else { 
				// vote by candidate name
				if (input.equals("c") || input.equals("candidate")) {
					printCandidatesOnBallot(djBallot);
					
					System.out.print("Please indicate which candidate to vote for: ");
					
					String candidateName = scr.nextLine();
					
					try {
						Candidate candidate = djBallot.getCandidateByName(candidateName);
						
						if (candidate.getName().equals(candidateName)) {
							djBallot.voteForCandidate(candidateName);
							voteCasted = true;
							
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
					printPartiesOnBallot(djBallot);
					
					System.out.print("Please indicate which party to vote for: ");
					
					EnumSet<Party> politicalAffilication = EnumSet.allOf(Party.class);
					String party = scr.nextLine();
					
					try {
						Party partyToVoteFor = Party.valueOf(party);
						
						if (politicalAffilication.contains(partyToVoteFor)) {
							djBallot.voteStraightTicket(partyToVoteFor);
							
							voteCasted = true;
							
							printBallotSummary(djBallot, partyToVoteFor);
						}
						
					} catch (IllegalArgumentException iae) {
						System.out.println();
						System.err.println("\"" + party + "\"" + " is not a valid party.");
						printVotingOptions();
					}
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
		Candidate c3 = new Candidate("Carlos", Candidate.Party.REPUBLICAN);
		Candidate c4 = new Candidate("Hubert", Candidate.Party.REPUBLICAN);
		Candidate c5 = new Candidate("John", Candidate.Party.LIBERTARIAN);
		
		ballot.addCandidate(c1);
		ballot.addCandidate(c2);
		ballot.addCandidate(c3);
		ballot.addCandidate(c4);
		ballot.addCandidate(c5);

		return ballot;
	}

	private static void printVotingOptions() {
		System.out.println();
		System.out.print("Please specify if you want to vote by (c)andidate name, (p)arty affiliation, or (e)xit: ");
	}

	private static void printCandidatesOnBallot(Ballot ballot) {
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

	private static void printPartiesOnBallot(Ballot ballot) {
		HashSet<Party> partiesOnBallot = new HashSet<Party>();
		Iterator<Entry<String, Candidate>> allCandidatesIterator = ballot.getAllCandidates().entrySet().iterator();		
		StringBuilder sb = new StringBuilder("Parties on this ballot: \n");

		while (allCandidatesIterator.hasNext()) {
			Party party = allCandidatesIterator.next().getValue().getParty();
			if(partiesOnBallot.add(party)){
				sb.append(party.name() + "\n");
			}
		}

		System.out.println();
		System.out.println(sb.toString());
	}

	private static void printBallotSummary(Ballot ballot, Party party) {
		Iterator<Entry<String, Candidate>> candidatesIterator = ballot.getAllCandidates().entrySet().iterator();

		StringBuilder sb = new StringBuilder("You voted for: \n");

		while (candidatesIterator.hasNext()){
			Candidate candidate = candidatesIterator.next().getValue();
			
			if(candidate.getParty().equals(party)) {
				sb.append(candidate.getName() + "\n");	
			}
		}

		System.out.println();
		System.out.println(sb.toString());
	}
}