package comp132.examples.recursion;

/**
 * Our first example of recursion in Java. This function computes the sum of the
 * integers from n down to 1. E.g. sum(5) = 5 + 4 + 3 + 2 + 1 = 15.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 21, 2009
 */
public class RecursiveSum {

    /**
     * Compute the sum of the numbers from the argument (n) down to 1.
     * 
     * @param n the starting number.
     * @return the sum from n down to 1.
     */
    public static int sum(int n) {
        if (n == 1) {
            return 1; // base case
        }
        else {
            return n + sum(n - 1); // recursive case
        }
    }

    public static void main(String[] args) {
        int x = sum(5);
        System.out.println("sum(5) = " + x);
    }
}
