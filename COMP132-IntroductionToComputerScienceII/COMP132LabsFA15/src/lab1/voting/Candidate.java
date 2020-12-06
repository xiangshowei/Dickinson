package lab1.voting;

/**
 * Representation of a candidate running for office.
 *
 * @author Grant Braught
 * @author Dickinson College 
 * 
 * @author Xiang Wei
 * @version 09/01/2015
 */
public class Candidate {

    public enum Party {
        DEMOCRAT("DEMOCRAT"),
        REPUBLICAN("REPUBLICAN"),
        LIBERTARIAN("LIBERTARIAN"),
        INDEPENDENT("INDEPENDENT");
        
        private final String politicalParty;

        private Party(String politicalAffiliation) {
            politicalParty = politicalAffiliation;
        }

        public String getParty() {
            return politicalParty;
        }
    }
    
    private String name;
    private Party party;
    private int votes;
    
    /**
     * Construct a new candidate with the specified name and party.
     * 
     * @param name the candidate's name
     * @param party the candidate's party
     */
    public Candidate(String name, Party party) {
        this(name, party, 0);
    }
    
    /**
     * Construct a new candidate with the specified name, party and number of votes.
     * 
     * @param name the candidate's name
     * @param party the candidate's party
     * @param votes the number of votes that this candidate has.
     */
    public Candidate(String name, Party party, int votes) {
        this.name = name;
        this.party = party;
        this.votes = votes;
    }
    
    /**
     * Get the candidate's name.
     * @return the candidate's name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the candidate's party.
     * @return the candidate's party.
     */
    public Party getParty() {
        return party;
    }
    
    /**
     * Change the candidate's party affiliation.
     * @param newParty the candidate's new party.
     */
    public void setParty(Party newParty) {
        party = newParty;
    }
    
    /**
     * Get the number of votes that have been cast for this
     * candidate.
     * 
     * @return this candidate's votes.
     */
    public int getNumVotes() {
        return votes;
    }
    
    /**
     * Increase the number of votes for this candidate by 1.
     */
    public void increaseVoteByOne() {
        votes++;
    }
    
    /**
     * Increase the number of votes for this candidate by the specified number of votes.
     *
     * @param numVotes the number of new votes for this candidate.
     */
    public void increaseVotes(int numVotes) {
        votes += numVotes;
    }
       
    /**
     * Determine if this candidate defeated another candidate.
     * 
     * @param opp the opponent of this candidate.
     * @return true if this candidate defeated the opponent.
     */
    public boolean hasMoreVotesThan(Candidate opp) {
        return votes > opp.getNumVotes();
    }
    
    /**
     * Determine if this candidate and another candidate are affiliated with
     * the same political party.
     * 
     * @param other the other candidate.
     * @return true if this candidate an the other candidate are affiliated with
     * the same party.
     */
    public boolean isSamePartyAs(Candidate other) {
    	return party.equals(other.getParty());
    }
}
