package comp132.examples.interfaces;

import java.util.*;

/**
 * Demonstration of the Arrays.sort method that is able to sort objects that
 * implement the Comparable interface.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 20, 2009
 */
public class SortingDolphins {

    /**
     * Create an array of 10 Dolphins with random lengths, print them out, sort
     * them and then print them again to show that they are now in sorted order
     * by length.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        System.out.println("Sorting Dolphins:");
        
        // Create an array of 10 random length Dolphins.
        Dolphin[] dolphins = new Dolphin[10];
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            dolphins[i] = new Dolphin(rnd.nextInt(20));
        }

        // Print out the Dolphin's lengths before sorting them.
        System.out.print("Before: ");
        for (Dolphin d : dolphins) {
            System.out.print(d.getLength() + " ");
        }
        System.out.println();

        /*
         * Sort the Dolphins based upon the compareTo method in the Dolphin
         * class.
         */
        Arrays.sort(dolphins);

        // Print out the Dolphin's lengths after sorting them.
        System.out.print(" After: ");
        for (Dolphin d : dolphins) {
            System.out.print(d.getLength() + " ");
        }
        System.out.println();
    }
}
