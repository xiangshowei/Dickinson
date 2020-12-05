package lab09.list;

/**
 * An implementation of the CS132List interface using an array of Objects.
 * 
 * @author Xiang Wei
 * @version 12/1/15
 */
public class CS132ArrayList implements CS132List {

	private int size; // number of the elements in the Array
	private Object[] elems;

	/**
	 * Construct a new empty CS132ArrayList.
	 */
	public CS132ArrayList() {
		elems = new Object[5];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(Object element) {
		// if the Array is full, create a bigger one
		if (size == elems.length) {
			this.copyCurrentArrayIntoBiggerArray();
		}// end if statement

		elems[size] = element;

		// increment the size of the Array after adding an element to it
		size++;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		this.checkForIndexOutOfBoundsException(index);

		return elems[index];
	}

	@Override
	public void set(int index, Object element) throws IndexOutOfBoundsException {
		checkForIndexOutOfBoundsException(index);

		elems[index] = element;
	}

	@Override
	public void insert(int index, Object element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index " + index + " not a valid position");
		}// end if statement

		// make a new Array if the current Array is full
		if (size == elems.length) {
			this.copyCurrentArrayIntoBiggerArray();
		}//end if statement
		
		//start at the end of the Array so that nothing gets overwritten
		this.shiftOneSlotOver(size, index);

		// put the element at index
		elems[index] = element;

		// increase the size after adding an element
		size++;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		this.checkForIndexOutOfBoundsException(index);

		Object removedElem = get(index);

		//since we already stored the element we want to remove in a local variable, 
		//it does not matter if it gets overwritten
		this.shiftOneSlotOver(index, size - 1);

		size--;

		return removedElem;
	}
	
	/**
	 * This helper method checks to see if the index in the Array will throw an
	 * IndexOutOfBoundsException.
	 * 
	 * @param index the index to check for in the Array
	 */
	private void checkForIndexOutOfBoundsException(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " not a valid position in the Array");
		}// end if statement
	}

	/**
	 * This helper method doubles the same of the current Array. First a
	 * temporary Array twice the size of the original Array is created and
	 * elements from the original Array are copied over to the temporary Array.
	 * Lastly the temporary points to the original Array so that code that
	 * depended on the original Array will still work.
	 */
	private void copyCurrentArrayIntoBiggerArray() {
		// create a temporary Array of double size
		Object[] tempArr = new Object[elems.length * 2];

		// copy things over from elems into tempArr
		for (int i = 0; i < elems.length; i++) {
			tempArr[i] = elems[i];
		}// end for loop with counter

		// elems now points to tempArr so we can do things in elems
		// but with a double the size of the original elems
		elems = tempArr;
	}

	/**
	 * This helper method moves the elements either to the left(up) OR to the
	 * right(down) in an Array from the start index to the end index. This
	 * method only makes necessary number of moves to shift the elements even if
	 * there are extra spaces in the Array.
	 * 
	 * @param startIndex the index to start shifting elements
	 * @param endIndex the index to stop shifting elements
	 */
	private void shiftOneSlotOver(int startIndex, int endIndex) {
		// increase index
		if (endIndex < startIndex) {
			for (int i = startIndex; i > endIndex; i--) {
				elems[i] = elems[i - 1];
			}// end for loop with counter
		}// end if statement

		// decrease index
		else {
			for (int i = startIndex; i < endIndex; i++) {
				elems[i] = elems[i + 1];
			}// end for loop with counter
		}// end else statement
	}
}
