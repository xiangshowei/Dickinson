package comp132.examples.sorting.java;

import java.util.*;

/**
 * Demonstration of using the Collections.sort method to sort objects into their
 * natural order (as defined by the compareTo method) and using the
 * Collections.binarySearch method to find an element in the sorted list.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 28, 2009
 */
public class CollectionSortingSearching {

    public static void main(String[] args) {
        Student s1 = new Student(1, "Sam", 2008, 2.3);
        Student s2 = new Student(2, "Pam", 2008, 3.8);
        Student s3 = new Student(3, "Jim", 2007, 2.9);
        Student s4 = new Student(4, "Kim", 2007, 3.5);
        Student s5 = new Student(5, "Bob", 2006, 3.3);

        /*
         * Add the students so that they are not initially in idNumber order.
         * Don't add s5 so that we have one not in the list for later.
         */
        ArrayList<Student> stuList = new ArrayList<Student>();
        stuList.add(s4);
        stuList.add(s2);
        stuList.add(s3);
        stuList.add(s1);

        /*
         * Sort the list into its natural (idNumber) order.  The Collections.sort
         * method uses the Student Object's the compareTo method.
         */
        Collections.sort(stuList);
        for (Student s : stuList) {
            System.out.println(s);
        }
        System.out.println();

        /*
         * Use the binary search built into the Collections class to search the
         * sorted list.  The output is the index at which the object is found or
         * the negative of the index where it would be inserted if it does not
         * appear in the list.
         */
        int kimIndex = Collections.binarySearch(stuList, s4);
        int bobIndex = Collections.binarySearch(stuList, s5);
        System.out.println("Kim (" + kimIndex + ")");
        System.out.println("Bob (" + bobIndex + ")");
    }
}
