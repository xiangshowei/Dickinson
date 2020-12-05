package comp132.examples.sorting.java;

import java.util.*;

/**
 * Demonstration of using the Collections.sort method to sort objects into an
 * order defined by a Comparator object and using the Collections.binarySearch
 * method to find an element in the sorted list.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 28, 2009
 */
public class ComparatorSortingSearching {

    public static void main(String[] args) {
        Student s1 = new Student(1, "Sam", 2008, 2.3);
        Student s2 = new Student(2, "Pam", 2008, 3.8);
        Student s3 = new Student(3, "Jim", 2007, 2.9);
        Student s4 = new Student(4, "Kim", 2007, 3.5);
        Student s5 = new Student(5, "Bob", 2006, 3.3);

        /*
         * Add the students so that they are not initially in year/gpa order.
         * Don't add s5 so that we have one not in the list for later.
         */
        ArrayList<Student> stuList = new ArrayList<Student>();
        stuList.add(s4);
        stuList.add(s2);
        stuList.add(s3);
        stuList.add(s1);

        /*
         * Sort the list into the order imposed by the YearGPAOrdering
         * Comparator.  The Student Object's compareTo method is not used,
         * instead the compare method of the YearGPAOrdering method is used
         * to compare Students.
         */
        Comparator<Student> yearGPA = new YearGPAOrdering();
        Collections.sort(stuList, yearGPA);
        for (Student s : stuList) {
            System.out.println(s);
        }

        System.out.println();

        /*
         * Use the binary search built into the Collections class to search the
         * list sorted by the YearGPAOrdering Comparator. The output is the
         * index at which the object is found or the negative of the index where
         * it would be inserted if it does not appear in the list.
         */
        int kimIndex = Collections.binarySearch(stuList, s4, yearGPA);
        int robIndex = Collections.binarySearch(stuList, s5, yearGPA);
        System.out.println("Kim (" + kimIndex + ")");
        System.out.println("Bob (" + robIndex + ")");
    }
}
