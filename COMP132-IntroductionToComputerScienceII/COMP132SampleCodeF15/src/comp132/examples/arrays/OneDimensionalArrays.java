package comp132.examples.arrays;

import comp132.examples.review.Candidate;

/**
 * This class contains a collection of methods that illustrate various aspects
 * of the mechanics of one-dimensional arrays.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 14, 2009
 */
public class OneDimensionalArrays {

    /**
     * Example of creating and accessing a 1-dimensional array of int values.
     */
    public static void basic1DArrays() {
        int[] vals = new int[5];

        vals[3] = 7;            // set int at index 3 to 7
        int x = vals[3] - 3;    // set x to be 4
        vals[x] = 2;            // set int at index 4 to be 2
        vals[vals[4]] = 12;     // set int at index 2 to be 12

        System.out.print("vals: ");
        for (int i = 0; i < vals.length; i++) {
            System.out.print(vals[i] + " ");
        }
        System.out.println();
        
        // Double all values
        for (int i=0; i<vals.length; i++) {
        	vals[i] = vals[i] * 2;
        }
        
        for (int val : vals) {
        	System.out.println(val);
        }
    }

    /**
     * Example illustrating that an array variable holds a reference to to the
     * array.  After the assignment vals2 = vals the two variables refer to the 
     * same array.  Thus, the array can be accessed or modified via either the
     * reference in vals or the reference in vals2. 
     */
    public static void arrayReferences() {
        int[] vals = new int[5];

        vals[3] = 12;
        int[] vals2 = vals;
        System.out.println("vals[3] = " + vals[3] + " : vals2[3] = " + vals2[3]);

        vals2[3] = 9;
        vals[2] = 7;
        System.out.println("vals[2] = " + vals[2] + " : vals2[2] = " + vals2[2]);
        System.out.println("vals[3] = " + vals[3] + " : vals2[3] = " + vals2[3]);
    }

    /**
     * Example illustrating that the elements of an array of primitives are
     * treated as primitives.  The assignment vals[0] = vals[1] copies the value
     * of vals[1] into vals[0]. Because they are primitive values, future changes to
     * vals[0] do not affect vals[1] and vice versa.
     */
    public static void arrayOfPrimitives() {
        int[] vals = new int[2];

        vals[1] = 8;
        vals[0] = vals[1];
        vals[1] = 4;
        System.out.println(" vals = {" + vals[0] + ", " + vals[1] + "}");
        
        /*
         * The above is analogous to the following code using primitive values.
         */
        int x = 8;
        int y = x;
        y = 4;
        System.out.println(" x = " + x + ", y = " + y);
    }

    /**
     * Example illustrating an array of of Objects and highlighting the fact
     * that each element of the array holds a reference to an Object. The assignment
     * list[0] = list[1] copies the reference in list[1] into list[0].  list[0] and 
     * list[1] then refer to the same Candidate object. Thus, the Candidate object can
     * be modified via either reference.
     */
    public static void arraysOfObjects() {
        Candidate[] list = new Candidate[2];

        list[0] = new Candidate("Joe", Candidate.DEMOCRAT);
        list[1] = new Candidate("Sam", Candidate.REPUBLICAN);

        list[0].increaseVotes(5);
        list[1].increaseVotes();
        
        System.out.println("list[0] = [" + list[0].getName() + ", " + list[0].getVotes() + "]");
        System.out.println("list[1] = [" + list[1].getName() + ", " + list[1].getVotes() + "]");

        list[0] = list[1];
        list[1].increaseVotes();

        System.out.println("list[0] = [" + list[0].getName() + ", " + list[0].getVotes() + "]");
        System.out.println("list[1] = [" + list[1].getName() + ", " + list[1].getVotes() + "]");
        
        list[0].increaseVotes();
        System.out.println("list[0] = [" + list[0].getName() + ", " + list[0].getVotes() + "]");
        System.out.println("list[1] = [" + list[1].getName() + ", " + list[1].getVotes() + "]");
    }

    /**
     * Run each of the above methods.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        System.out.println("basic1DArrays()");
        basic1DArrays();

        System.out.println();
        System.out.println("arrayReferences()");
        arrayReferences();

        System.out.println();
        System.out.println("arrayOfPrimitives()");
        arrayOfPrimitives();

        System.out.println();
        System.out.println("arraysOfObjects()");
        arraysOfObjects();
    }
}
