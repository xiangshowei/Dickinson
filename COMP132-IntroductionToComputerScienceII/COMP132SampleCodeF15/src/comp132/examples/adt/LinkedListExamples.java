package comp132.examples.adt;

/**
 * A collection of methods that illustrate different linked list operations such
 * as traversal, insertion and removal of nodes.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Nov 30, 2009
 */
public class LinkedListExamples {

    private SinglyLinkedNode head;

    /**
     * This method builds a list of three nodes. Each node refers to a String as
     * its data element and each node refers to the next node in the list. The
     * list contains:
     * 
     * head -> one -> two -> three -> null
     */
    public void buildList() {
        String o1 = "One";
        String o2 = "Two";
        String o3 = "Three";

        // Create the nodes that appear in the list.
        SinglyLinkedNode sln0 = new SinglyLinkedNode(o1);
        SinglyLinkedNode sln1 = new SinglyLinkedNode(o2);
        SinglyLinkedNode sln2 = new SinglyLinkedNode(o3);

        // Link them.
        head = sln0;
        sln0.next = sln1;
        sln1.next = sln2;
    }

    /**
     * This method returns the SinglyLinkedNode at the specified index.
     * This method assumes that there are at least index+1 nodes in the list.
     * 
     * @param index the index of the node.
     * @return the SinglyLinkedNode contained at the specified index.
     */
    public SinglyLinkedNode getNode(int index) {
        SinglyLinkedNode cur = head;

        /*
         * Traverse down the list until we reach the node specified by index.
         * Note that if index == 0, cur will not change.
         */
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur;
    }

    /**
     * Insert the new node following the node referred to by pred (i.e. the
     * predecessor of the new node).
     * 
     * @param newNode the new node to be inserted.
     * @param pred the predecessor of the new node.
     */
    public void insertNode(SinglyLinkedNode newNode, SinglyLinkedNode pred) {
        newNode.next = pred.next;
        pred.next = newNode;
    }

    /**
     * Remove the node that comes after pred in the list.
     * 
     * @param pred the predecessor of the node to be removed.
     */
    public void removeNode(SinglyLinkedNode pred) {
        SinglyLinkedNode toRemove = pred.next;
        pred.next = toRemove.next;
    }
    
    /**
     * Return a reference to the last node in the linked list.
     * 
     * @return the last node.
     */
    public SinglyLinkedNode getLast() {
        return null;
    }
    
    /**
     * Insert a new node at the start of the linked list.
     * 
     * @param newNode the node to be inserted/
     */
    public void insertAtStart(SinglyLinkedNode newNode) {
         
    }
    
    /**
     * Add a new node to the end of the linked list.
     * 
     * @param newNode node to be appended to the list.
     */
    public void appendNode(SinglyLinkedNode newNode) {
        
    }
    
    /**
     * Remove the first node in the linked list.
     * 
     * @return the first node in the list.
     */
    public SinglyLinkedNode removeFirst() {
        return null;
    }
    
    /**
     * Remove the last node in the linked list.
     * 
     * @return the last node in the list.
     */
    public SinglyLinkedNode removeLast() {
        return null;
    }
    
    /**
     * Swap the order of the two nodes following pred.  Assume that
     * there are at least three nodes following pred.
     * 
     * @param pred the predecessor of the two nodes to be swapped.
     */
    public void swapNextTwo(SinglyLinkedNode pred) {
        SinglyLinkedNode first = pred.next;
        SinglyLinkedNode second = first.next;
        
        pred.next = second;
        first.next = second.next;
        second.next = first;
    }
           
    /**
     * Print out the contents of the linked list.
     */
    public void printList() {
        SinglyLinkedNode cur = head;
        
        System.out.print("head -> ");
        while(cur != null) {
            System.out.print(cur.element + " -> ");
            cur = cur.next;
        }
        System.out.println("null");
    }

    /**
     * Run the methods contained in this class to illustrate the workings of a
     * singly linked list.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        LinkedListExamples lle = new LinkedListExamples();

        // Build and print the list.
        lle.buildList();
        lle.printList();

        // Get and display a few of the nodes in the list.
        System.out.println();
        SinglyLinkedNode sln1 = lle.getNode(1);
        System.out.println("node 1: " + sln1.element);
        SinglyLinkedNode sln2 = lle.getNode(2);
        System.out.println("node 2: " + sln2.element);

        // Insert a new node and display the list.
        System.out.println();
        SinglyLinkedNode newOne = new SinglyLinkedNode("NewNode");
        lle.insertNode(newOne, sln1);
        lle.printList();
        
        // Remove a node and display the list.
        System.out.println();
        SinglyLinkedNode sln0 = lle.getNode(0);
        lle.removeNode(sln0);
        lle.printList();
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
}
