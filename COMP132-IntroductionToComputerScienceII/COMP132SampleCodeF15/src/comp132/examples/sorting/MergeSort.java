package comp132.examples.sorting;

/**
 * Implementation of the Merge Sort algorithm.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 24, 2009
 */
public class MergeSort {

    /**
     * Merge sort the provided array.
     * 
     * @param arr the array to be sorted.
     * @return the sorted array.
     */
    public static int[] sort(int[] arr) {
        if (arr.length == 1) {
            // An arrays of length 1 is already sorted!
            return arr;
        }
        else {
            // Split the list into left and right parts.
            int[] leftUnsorted = ArrayTools.getSubarray(arr, 0, arr.length / 2);
            int[] rightUnsorted = ArrayTools.getSubarray(arr, arr.length / 2, arr.length);
            
            // Sort the left part of the array.
            int[] leftSorted = sort(leftUnsorted);

            // Sort the right part of the array.
            int[] rightSorted = sort(rightUnsorted);

            // Merge the two sorted parts into a sorted array.
            int[] merged = merge(leftSorted, rightSorted);
            return merged;
        }
    }

    /**
     * Merge two sorted arrays into a single sorted array.
     * 
     * @param arr1 a sorted array.
     * @param arr2 another sorted array.
     * @return a sorted array containing all of the elements in arr1 and arr2.
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];

        int arr1Index = 0;
        int arr2Index = 0;
        int mergedIndex = 0;

        // Keep taking values from arr1 or arr2 until the merged array is full.
        while (mergedIndex < merged.length) {

            if (arr1Index < arr1.length && arr2Index < arr2.length) {
                /*
                 * Still have values in both arr1 and arr2 that have not been
                 * used, so take the smaller of the two and put it into merged.
                 */
                if (arr1[arr1Index] < arr2[arr2Index]) {
                    merged[mergedIndex] = arr1[arr1Index];
                    arr1Index++;
                }
                else {
                    merged[mergedIndex] = arr2[arr2Index];
                    arr2Index++;
                }
            }
            else if (arr1Index < arr1.length) {
                /*
                 * There are still values in arr1 that have not been used but
                 * arr2 has been exhausted so take the next value from arr1.
                 */
                merged[mergedIndex] = arr1[arr1Index];
                arr1Index++;
            }
            else {
                /*
                 * There are still values in arr2 that have not been used but
                 * arr1 has been exhausted so take the next value from arr2.
                 */
                merged[mergedIndex] = arr2[arr2Index];
                arr2Index++;
            }

            mergedIndex++;
        }

        return merged;
    }

    /**
     * Sort a list of integer values using the merge sort.
     */
    public static void main(String[] args) {
        int[] arr = ArrayTools.getRandomIntArray(0, 10000, 10);
        ArrayTools.printIntArray(arr);
        int[] sorted = MergeSort.sort(arr);
        ArrayTools.printIntArray(sorted);
    }
}
