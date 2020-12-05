package comp132.examples.review;

/**
 * Representation of a candidate running for office.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 10, 2009
 */
public class Candidate {
    /**
     * Democratic party.
     */
    public static final String DEMOCRAT = "Democrat";
    
    /**
     * Republican party.
     */
    public static final String REPUBLICAN = "Republican";
    
    /**
     * Libertarian party.
     */
    public static final String LIBERTARIAN = "Libertarian";
    
    /**
     * Independent party.
     */
    public static final String INDEPENDENT = "Independent";
    
    private String name;
    private String party;
    private int votes;
    
    /**
     * Construct a new candidate with the specified name and party.
     * 
     * @param name the candidate's name
     * @param party the candidate's party
     */
    public Candidate(String name, String party) {
        this.name = name;
        this.party = party;
        this.votes = 0;
    }
    
    /**
     * Construct a new candidate with the specified name, party and
     * number of votes.
     * 
     * @param name the candidate's name
     * @param party the candidate's party
     * @param votes the number of votes that this candidate has.
     */
    public Candidate(String name, String party, int votes) {
    	this(name,party);
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
    public String getParty() {
        return party;
    }
    
    /**
     * Get the number of votes that have been cast for this
     * candidate.
     * 
     * @return this candidate's votes.
     */
    public int getVotes() {
        return votes;
    }
    
    /**
     * Change the candidate's party affiliation.
     * @param newParty the candidate's new party.
     */
    public void setParty(String newParty) {
        party = newParty;
    }

    /**
     * Increase the number of votes for this candidate by 1.
     */
    public void increaseVotes() {
        votes++;
    }
    
    /**
     * Increase the number of votes for this candidate by the specified number of votes.
     *
     * @param numVotes the number of new votes for this candidate.
     */
    public void increaseVotes(int numVotes) {
        votes = votes + numVotes;
    }
       
    /**
     * Determine if this candidate defeated another candidate:
     *  e.g. joe.defeated(sam)
     * 
     * @param opp the opponent of this candidate.
     * @return true if this candidate defeated the opponent.
     */
    public boolean defeated(Candidate opp) {
        if (this.getVotes() > opp.getVotes()) {
            return true;
        }
        else {
            return false;
        }
    }
}
