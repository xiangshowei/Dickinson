package lab07.recursion;

/**
 * Compute the number of boxes in a pyramid of boxes based on the number of
 * boxes in the base of the pyramid. All boxes are stacked directly on top of
 * another box.
 * 
 * Shown below are two examples. One pyramid with base 5 and another with base
 * 6. The number of boxes in the the pyramid with base 5 is 9. The number of
 * boxes in the pyramid with base 6 is 12.
 * 
 * <pre>
 *     []           [][]           
 *   [][][]       [][][][]
 * [][][][][]   [][][][][][]
 *</pre>
 * 
 * @author Xiang Wei
 * @version 11/10/15
 */
public class PyramidBoxCount {

	/**
	 * Compute and return the number of boxes in a pyramid with the specified
	 * number of boxes in its base.
	 * 
	 * @param base the size of the base (positive integer)
	 * @return the number of boxes in the pyramid.
	 */
	public static int computePyramidBoxes(int base) {
		//base case 1
		if(base == 1){
			return 1;
		}// end if statement
		
		//base case 2
		else if(base == 2){
			return 2;
		}// end else if statement

		//recursive case
		else {
			return base + computePyramidBoxes(base - 2);
		}// end else statement
	}
}
