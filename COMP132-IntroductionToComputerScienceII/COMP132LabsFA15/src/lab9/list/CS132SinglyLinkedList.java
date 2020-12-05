package lab09.list;

/**
 * An implementation of the List interface using a singly linked list of
 * Objects.
 * 
 * @author Xiang Wei
 * @version 12/2/15
 */
public class CS132SinglyLinkedList implements CS132List {

	private int size;
	private SinglyLinkedNode head;

	/**
	 * Construct a new empty CS132SinglyLinkedList.
	 */
	public CS132SinglyLinkedList() {
		size = 0;
		head = null;
	}

	/*
	 * Structure used to represent a node in the linked list.
	 */
	private static class SinglyLinkedNode {
		public SinglyLinkedNode next;
		public Object element;

		public SinglyLinkedNode(Object element) {
			this.element = element;
			next = null;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(Object element) {
		SinglyLinkedNode toBeAdded = new SinglyLinkedNode(element);

		//there is nothing in the "list" so we just point head to the element that we are adding
		if(head == null) {
			head = toBeAdded;
		}// end if statement

		else {
			SinglyLinkedNode lastNode = this.getNodeAtIndex(size - 1); 

			//set the last node's next to be the node that we want to add to the "list"
			lastNode.next = toBeAdded;
		}// end else statement

		//increase the size of the "list" every time we add to it
		size++;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		this.checkForIndexOutOfBoundsException(index);

		if(index == 0) {
			return head.element;
		}// end if statement
		
		else {
			return this.getNodeAtIndex(index).element; 
		}// end else statement
	}

	@Override
	public void set(int index, Object element) throws IndexOutOfBoundsException {
		this.checkForIndexOutOfBoundsException(index);

		if(index == 0) {
			head.element = element;
		}// if statement

		else {
			this.getNodeAtIndex(index).element = element;
		}//else statement

	}

	@Override
	public void insert(int index, Object element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index " + index + " not a valid position");
		}// end if statement

		SinglyLinkedNode toBeInsertedNode = new SinglyLinkedNode(element);

		//nothing is in the "list" so just put it there
		if(index == 0 && size == 0) {
			head = toBeInsertedNode;
		}

		//inserting at head but there's something already there
		else if(index == 0 && size!= 0) {
			toBeInsertedNode.next = head;
			head = toBeInsertedNode;	
		}// end else if statement

		//inserting elsewhere in the of the "list"
		else {
			SinglyLinkedNode nodeBeforeIndex = this.getNodeAtIndex(index - 1);
			SinglyLinkedNode nodeAtIndex = this.getNodeAtIndex(index);

			toBeInsertedNode.next = nodeAtIndex;
			nodeBeforeIndex.next = toBeInsertedNode;
		}// end else statement

		//increase the size of the "list" since we've inserted a node
		size++;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		this.checkForIndexOutOfBoundsException(index);
		
		SinglyLinkedNode toBeRemovedNode = this.getNodeAtIndex(index);

		//removing at head
		if(index == 0) {
			head = toBeRemovedNode;  
		}// end if statement
		
		//removing elsewhere in the "list"
		else {
			SinglyLinkedNode nodeBeforeToBeRemovedNode = this.getNodeAtIndex(index - 1);
			nodeBeforeToBeRemovedNode.next = toBeRemovedNode.next;
		}//end else statement
		
		//decrease the size since a node was removed from the "list"
		size--;
	
		return toBeRemovedNode.element;
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
	 * This helper method returns the SinglyLinkedNode located at the specified index
	 * @param index the index where the SinglyLinkedNode is located
	 * @return the SinglyLinkednode located at index
	 */
	private SinglyLinkedNode getNodeAtIndex(int index) {
		SinglyLinkedNode curNode = head;

		// use a for loop to get to the last node in the "list"
		for(int i = 0; i < index; i++) {
			curNode = curNode.next;
		}// end for loop with counter

		return curNode;
	}
}
