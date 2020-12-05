package comp132.examples.review;

/**
 * An example illustrating the difference between primitive types and object
 * types in java.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 10, 2009
 */
public class AliasingExample {

    public static void main(String[] args) {
        int x = 7;
        int y = 9;
        int z = 4;

        System.out.println("Primitives Before:");
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
       
        x = 10;
        y = 5;
        z = y;
        z = 12;
        y = 2;
        
        System.out.println("Primitives After:");
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        
        System.out.println();
        
        Candidate c1 = new Candidate("Bob", Candidate.DEMOCRAT);
        Candidate c2 = new Candidate("Jane", Candidate.LIBERTARIAN);
        Candidate c3 = new Candidate("Joe", Candidate.REPUBLICAN);
        
        System.out.println("Objects Before:");
        System.out.println("c1: " + c1.getName() + " : " + c1.getVotes());
        System.out.println("c2: " + c2.getName() + " : " + c2.getVotes());
        System.out.println("c3: " + c3.getName() + " : " + c3.getVotes());
        
        c1.increaseVotes(5);
        c2.increaseVotes(7);
        c3 = c2;
        c3.increaseVotes(3);
        c2.increaseVotes(2);
        
        System.out.println("Objects After:");
        System.out.println("c1: " + c1.getName() + " : " + c1.getVotes());
        System.out.println("c2: " + c2.getName() + " : " + c2.getVotes());
        System.out.println("c3: " + c3.getName() + " : " + c3.getVotes());
    }
}
