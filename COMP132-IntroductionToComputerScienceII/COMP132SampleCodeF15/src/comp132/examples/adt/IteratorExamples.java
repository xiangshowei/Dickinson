package comp132.examples.adt;

import java.util.*;

/**
 * A collection of methods that illustrate the use of Iterators in Java.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Nov 30, 2009
 */
public class IteratorExamples {

    /**
     * Traverse the provided List visiting each element using the get
     * method.
     * 
     * @param list the List to be traversed.
     */
    public static void  listTraverseWithGet(List<String> list) {
        for (int i=0; i<list.size(); i++) {
            String elem = list.get(i);
            // could do something with elem here!
        }
    }
    
    /**
     * Traverse the provided List visiting each element using an Iterator.
     * 
     * @param list the List to be traversed.
     */
    public static void listTraverseWithIterator(List<String> list) {
        /* Get an iterator for the list. The iterator will be
         * positioned before the first element of the list.
         */
        Iterator<String> it = list.iterator();
        
        while (it.hasNext()) {
            String elem = it.next();
            // could do something with elem here!
        }
    }
    
    /**
     * Remove all duplicates of the first element that appears in 
     * the list.  This method assumes that the list contains at least
     * one element.
     * 
     * @param list the list from which to remove the duplicates.
     */
    public static void removeDupsOfFirstWithIterator(List<String> list) {
        Iterator<String> it = list.iterator();
        String first = it.next();
        while (it.hasNext()) {
            String next = it.next();
            if (first.equals(next)) {
                it.remove();
            }
        }
    }
    
    /**
     * A more obvious, but must worse, implementation of removeDupsOfFirst.
     * This method will have very poor performance when run on a LinkedList
     * because every call to get must traverse the list.
     * 
     * @param list the list from which to remove the duplicates.
     */
    public static void removeDupsOfFirstWithGet(List<String> list) {
        String first = list.get(0);
        for (int i=1; i<list.size(); i++) {
            while (list.get(i).equals(first)) {
                list.remove(i);
            }
        }
    }
    
    /**
     * Compare the running time for the two listTraverse methods, one using get
     * and the other using iterator, when run on ArrayList and then LinkedList.
     */
    public static void listTraversalComparison() {
        ArrayList<String> arrayList = new ArrayList<String>();
        LinkedList<String> linkedList = new LinkedList<String>();

        long start = 0;
        long end = 0;
        
        fillList(arrayList);
        start = System.nanoTime();
        listTraverseWithGet(arrayList);
        end = System.nanoTime();
        System.out.println("listTraverseWithGet(arrayList):   \t\t" + (end-start)/1.0E9 + "s");
        
        fillList(linkedList);
        start = System.nanoTime();
        listTraverseWithGet(linkedList);
        end = System.nanoTime();
        System.out.println("listTraverseWithGet(linkedList):   \t\t" + (end-start)/1.0E9 + "s");
        
        fillList(arrayList);
        start = System.nanoTime();
        listTraverseWithIterator(arrayList);
        end = System.nanoTime();
        System.out.println("listTraverseWithIterator(arrayList):\t\t" + (end-start)/1.0E9 + "s");
        
        fillList(linkedList);
        start = System.nanoTime();
        listTraverseWithIterator(linkedList);
        end = System.nanoTime();
        System.out.println("listTraverseWithIterator(linkedList):\t\t" + (end-start)/1.0E9 + "s");
    }
    
   
    /**
     * Compare the running time for the two removeDupsOfFirst implementations
     * when run on ArrayLists and LinkedLists.
     */
    public static void removeDupsComparison() {
        ArrayList<String> arrayList = new ArrayList<String>();
        LinkedList<String> linkedList = new LinkedList<String>();

        fillList(arrayList);
        long start = System.nanoTime();
        removeDupsOfFirstWithGet(arrayList);
        long end = System.nanoTime();
        System.out.println("removeDupsOfFirstWithGet(arrayList):\t\t" + (end-start)/1.0E9 + "s");
        
        fillList(linkedList);
        start = System.nanoTime();
        removeDupsOfFirstWithGet(linkedList);
        end = System.nanoTime();
        System.out.println("removeDupsOfFirstWithGet(linkedList):\t\t" + (end-start)/1.0E9 + "s");
        
        fillList(arrayList);
        start = System.nanoTime();
        removeDupsOfFirstWithIterator(arrayList);
        end = System.nanoTime();
        System.out.println("removeDupsOfFirstWithIterator(arrayList):   \t" + (end-start)/1.0E9 + "s");
        
        fillList(linkedList);
        start = System.nanoTime();
        removeDupsOfFirstWithIterator(linkedList);
        end = System.nanoTime();
        System.out.println("removeDupsOfFirstWithIterator(linkedList):   \t" + (end-start)/1.0E9 + "s");
    }
    
    /*
     * Fill the provided list with copies of the alphabet "ABC...XYZABC..." etc.
     */
    private static void fillList(List<String> list) {
        list.clear();
        for (int i=0; i<75000; i++) {
            list.add(new Character((char)(i%26+65)) + "");
        }
    }
    
    /**
     * Print out the contents of the list.
     * 
     * @param list the List to be printed.
     */
    public static void printList(List<String> list) {
        for(String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {  
//        LinkedList<String> list = new LinkedList<String>();
//        for (int i=0; i<20; i++) {
//            list.add("" + i%4);
//        }
//        printList(list);
//        removeDupsOfFirstIterator(list);
//        printList(list);
//        
//        System.out.println();
       listTraversalComparison();
//        
//        System.out.println();
//        removeDupsComparison();
    }
}
