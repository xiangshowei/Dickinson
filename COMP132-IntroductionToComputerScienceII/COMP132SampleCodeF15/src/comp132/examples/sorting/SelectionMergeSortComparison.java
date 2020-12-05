package comp132.examples.sorting;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * An experiment that runs selection sort and merge sort for 
 * different input sizes and reports the time it takes each sort
 * for each input size.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 23, 2009
 */
public class SelectionMergeSortComparison {

    private static int[] SIZES = { 1000, 2500, 5000, 7500, 10000, 15000, 20000, 30000, 40000};
    private static int TRIALS = 10;

    public static void main(String[] args) {

        System.out.println("Size\t\tSS Time(s)\tMS Time(s)\tFactor");
        
        for (int size : SIZES) {
            
            long totalSelNanos = 0;
            long totalMergeNanos = 0;
            
            for (int t = 0; t < TRIALS; t++) {
                int[] arr1 = null;
                int[] arr2 = null;
                System.gc();
                arr1 = ArrayTools.getRandomIntArray(0, size, size);
                
                /*
                 *  Make a copy of the array for merge sort to sort so that it
                 *  is not sorting an already ordered array.
                 */
                arr2 = new int[arr1.length];
                System.arraycopy(arr1, 0, arr2, 0, arr1.length);
                
                long startNanos = System.nanoTime();
                SelectionSort.iterativeSort(arr1);
                long endNanos = System.nanoTime();
                totalSelNanos = totalSelNanos + (endNanos - startNanos);
                
                startNanos = System.nanoTime();
                MergeSort.sort(arr2);
                endNanos = System.nanoTime();
                totalMergeNanos = totalMergeNanos + (endNanos - startNanos);
                
            }
            
            double ssTime = totalSelNanos / 1E9 / TRIALS;
            double msTime = totalMergeNanos / 1E9 / TRIALS;
            int factor = (int)Math.round(ssTime / msTime);
            
            DecimalFormat df = new DecimalFormat("0.0000");
            
            System.out.println(size + "\t\t" + df.format(ssTime) + "\t\t" + df.format(msTime) + "\t\t" + factor);
        }
    }
}
