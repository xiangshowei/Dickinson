package lab07.recursion;

/**
 * Recursive computation of x^n.
 *
 * @author Xiang Wei
 * @version 11/10/15
 */
public class Exponentiation {

	/**
	 * Compute the value of x^n.
	 * 
	 * @param x the base (non-negative integer)
	 * @param n the exponent (non-negative integer)
	 * @return x^n
	 */
	public static long exp(int x, int n) {
		//base case 1
		if(x == 0){
			return 0;
		}// end if statement
		
		//base case 2
		else if (n == 0 || x == 1) {
			return 1;
		}// end else if statement
		
		//recursive cases
		else {
			//even power
			if(n % 2 == 0){
				return exp(x, n/2) * exp(x, n/2);
			} // end if statement
			
			//odd power
			else {
				return x * exp(x, n -1);
			}// end else to if statement @ line 32
		}// end else statement to if statement @ line 20
	}
}
