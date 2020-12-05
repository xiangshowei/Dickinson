package comp132.examples.sorting;

import java.text.DecimalFormat;

/**
 * An experiment that runs linear search and binary search for 
 * different input sizes and reports the time it takes each search
 * for each input size.  Every search is performed for a value that
 * does not appear in the list, thus the worst case time for each
 * size is reported.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 23, 2009
 */
public class LinearBinarySearchComparison {

    private static int[] SIZES = { 10000, 100000, 500000, 1000000, 5000000, 10000000};
    private static int TRIALS = 10;

    public static void main(String[] args) {

        System.out.println("Size\t\tLS Time(s)\tBS Time(s)\tFactor");
        
        for (int size : SIZES) {
            System.out.print(size + "\t\t");
            
            long totalLinearNanos = 0;
            long totalBinaryNanos = 0;
            
            for (int t = 0; t < TRIALS; t++) {
                int[] arr = null;
                System.gc();
                arr = ArrayTools.getRandomIntArray(0, size, size);
                
                long startNanos = System.nanoTime();
                Search.linearSearch(arr, size);
                long endNanos = System.nanoTime();
                totalLinearNanos = totalLinearNanos + (endNanos - startNanos);
                
                arr = MergeSort.sort(arr);
                startNanos = System.nanoTime();
                Search.binarySearch(arr, size);
                endNanos = System.nanoTime();
                totalBinaryNanos = totalBinaryNanos + (endNanos - startNanos);
                
            }
            
            double lsTime = totalLinearNanos / 1E9 / TRIALS;
            double bsTime = totalBinaryNanos / 1E9 / TRIALS;
            int factor = (int)Math.round(lsTime / bsTime);
            
            DecimalFormat df = new DecimalFormat("0.000000");
            
            System.out.println(df.format(lsTime) + "\t" + df.format(bsTime) + "\t\t" + factor);
        }
    }
}
