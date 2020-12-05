package comp132.examples.sorting;

import java.text.DecimalFormat;

/**
 * An experiment that runs selection sort on arrays of different sizes
 * and measures its running time.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 24, 2009
 */
public class SelectionSortExperiment {

    private static int[] SIZES = { 1000, 2500, 5000, 7500, 10000, 15000, 20000, 30000, 40000};
    private static int TRIALS = 10;

    public static void main(String[] args) {

        System.out.println("Size\tTime (s)");
        
        for (int size : SIZES) {
            System.out.print(size + "\t");
            
            long totalNanos = 0;
            
            for (int t = 0; t < TRIALS; t++) {
                int[] arr = ArrayTools.getRandomIntArray(0, size, size);
                
                long startNanos = System.nanoTime();
                SelectionSort.iterativeSort(arr);
                long endNanos = System.nanoTime();
                totalNanos = totalNanos + (endNanos - startNanos);
            }
            
            DecimalFormat df = new DecimalFormat("0.00000");
            System.out.println(df.format(totalNanos / 1.0e9 / TRIALS));
        }
    }
}
