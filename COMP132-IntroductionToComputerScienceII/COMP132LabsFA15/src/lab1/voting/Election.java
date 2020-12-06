package lab1.voting;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

import lab1.voting.Candidate.Party;

public class Election {

	/**
	 * Prints the party affiliation of all of the candidates
	 * 
	 * @param ballot the race that the candidates are running in
	 */
	public static void printCandidateAffiliation(Ballot ballot) {

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

	public static void printBallotSummary() {

	}

	public static void main(String args[]) {
		Ballot dJ = new Ballot("DistrictJudge");
		Candidate c1 = new Candidate("Alex", Candidate.Party.DEMOCRAT);
		Candidate c4 = new Candidate("Bob", Candidate.Party.LIBERTARIAN);
		Candidate c5 = new Candidate("Carlos", Candidate.Party.DEMOCRAT);
		Candidate c6 = new Candidate("Hubert", Candidate.Party.REPUBLICAN);
		Candidate c2 = new Candidate("John", Candidate.Party.REPUBLICAN);
		Candidate c3 = new Candidate("Sarah", Candidate.Party.INDEPENDENT);

		dJ.addCandidate(c1);
		dJ.addCandidate(c2);
		dJ.addCandidate(c3);
		dJ.addCandidate(c4);
		dJ.addCandidate(c5);
		dJ.addCandidate(c6);

		System.out.println();
		System.out.println("Please specify if you want to vote by (c)andidate name, (p)arty affiliation, or (e)xit: ");
		System.out.println();

		Scanner scr = new Scanner(System.in);
		boolean voteCasted = false;

		while (!voteCasted) {
			String input = scr.nextLine();

			if (input.equalsIgnoreCase("e") || input.equalsIgnoreCase("exit")) {
				voteCasted = true;
			}

			else { // vote by candidate name
				if (input.equals("c") || input.equals("candidate")) {
					printCandidateAffiliation(dJ);

					System.out.println("Please vote for one of the candidates above.");
					System.out.println();

					String candidateName = scr.nextLine();
					
					try {
						Candidate candidate = dJ.getCandidateByName(candidateName);

						if (candidate.getName().equals(candidateName)) {
							dJ.voteForCandidate(candidateName);
							voteCasted = true;

							System.out.println("You voted for " + candidate.getName());
						}
					}

					catch (NullPointerException npe) {
						System.err.println(candidateName + " is not a valid candidate.");
						System.out.println();
						System.out.print("Please specify if you want to vote by (c)andidate name, (p)arty affiliation, or (e)xit:");
						System.out.println();

					}

					// EnumSet<Party> politicalAffilication = EnumSet.allOf(Party.class);
					// if (politicalAffilication.contains(Party.valueOf(input))) {
					// 	ge.voteStraightTicket(Candidate.Party.valueOf(Candidate.Party.class, input));

					// 	voteCasted = true;

						// System.out.println("You voted for the " +
						// Candidate.Party.valueOf(Candidate.Party.class, input).toString());
					}
				}
			}
			scr.close();
		}


	}
