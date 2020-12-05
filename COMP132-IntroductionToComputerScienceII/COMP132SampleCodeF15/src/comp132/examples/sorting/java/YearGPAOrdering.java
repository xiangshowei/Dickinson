package comp132.examples.sorting.java;

import java.util.Comparator;

/**
 * A Comparator for Student objects that orders them first by
 * increasing graduation year and then by decreasing GPA. So at the
 * top of the list is the earliest graduate with the highest gpa.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 28, 2009
 */
public class YearGPAOrdering implements Comparator<Student> {

    public int compare(Student o1, Student o2) {
        if (o1.getGradYear() < o2.getGradYear()) {
            return -1;      // o1 graduates before o2
        }
        else if (o1.getGradYear() > o2.getGradYear()) {
            return 1;       // o1 graduates after o2
        }
        else {
            // o1 and o2 graduate in the same year.
            if (o1.getGpa() > o2.getGpa()) {
                return -1;  // o1 has a higher gpa than o2
            }
            else if (o1.getGpa() < o2.getGpa()) {
                return 1;   // o1 has a lower gpa than o2
            }
            else {
                return 0;   // o1 and o2 have the same gpa.
            }
        }
    }
}