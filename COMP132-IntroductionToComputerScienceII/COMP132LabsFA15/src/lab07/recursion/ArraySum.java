package lab07.recursion;

/**
 * Recursive computation of the sum of all of the elements in an array.
 * 
 * @author Xiang Wei
 * @version 11/10/15
 */
public class ArraySum {

	/**
	 * Compute the total of all of the elements in arr using recursion. You may
	 * assume that the array contains at least 1 element (i.e. arr.length >= 1)
	 * 
	 * @param arr the array to sum up.
	 * @return the total of all of the elements in the array.
	 */
	public static int arraySum(int[] arr) {
		return arraySum2(arr, arr.length - 1);
	}

	private static int arraySum2(int[] arr, int start){
		//base case
		if(start == 0) {
			return arr[start];
		}// end if statement
		
		//recursive case
		else {
			return arr[start] + arraySum2(arr, start - 1); 
		}// end else statement
	}
}
