package comp132.examples.sorting.java;

import java.util.Arrays;
import comp132.examples.sorting.*;

/**
 * Example of sorting and searching an array of int values using the static
 * methods in the Arrays class. Similar methods exist for other primitive java
 * types (byte, char, short, int, long, float, double)
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 29, 2009
 */
public class PrimitiveSortingSearching {

    /**
     * Sort an array of integer values using the Arrays.sort method and then
     * search the sorted list using the Arrays.binarySearch method.
     * 
     * @param args none
     */
    public static void main(String[] args) {

        int size = 10;
        int value = 7;
        int[] arr = ArrayTools.getRandomIntArray(0, size, size);

        Arrays.sort(arr);                               // sort the array.
        int index = Arrays.binarySearch(arr, value);    // search the array.

        if (index > 0) {
            System.out.println("The value " + value + " was found at index " + index + ".");
        }
        else {
            System.out.println("The value " + value + " was not found.");
            System.out.println("It would be inserted at " + (-index-1) + ".");
        }

        ArrayTools.printIntArray(arr);
    }
}
