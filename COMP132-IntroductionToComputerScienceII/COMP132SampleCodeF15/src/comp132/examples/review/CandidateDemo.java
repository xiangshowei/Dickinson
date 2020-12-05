package comp132.examples.review;

public class CandidateDemo {

    /**
     * A main method that constructs and manipulates some Candidate
     * objects for the purposes of illustration.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        Candidate c1 = new Candidate("Bob", Candidate.DEMOCRAT);
        Candidate c2 = new Candidate("Jane", Candidate.LIBERTARIAN);
        
        c1.increaseVotes();
        c2.increaseVotes(20);
        
        System.out.println(c1.getName() + " has " + c1.getVotes() + " votes.");
        System.out.println(c2.getName() + " has " + c2.getVotes() + " votes.");
        
        if (c1.defeated(c2)) {
            System.out.println(c1.getName() + " wins");
        }
        else {
            System.out.println(c2.getName() + " wins");
        }
    }
}
