package comp132.examples.staticex;

/**
 * An example illustrating the use of a static field for assigning
 * unique object identification numbers.
 * 
 * This class assigns each student a unique student ID by using a
 * static field.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 16, 2009
 */
public class Student {

    private static int nextID = 1000;
    
    private int studentID;
    private String name;
    
    /**
     * Construct a new Student with the given name and a unique student ID number.
     * 
     * @param name the student's name.
     */
    public Student(String name) {
        this.name = name;
        studentID = nextID;
        nextID++;
    }
    
    /**
     * Return the ID that will be assigned to the next Student object
     * that is created.
     * 
     * @return the next student ID.
     */
    public static int getNextID() {
        return nextID;
    }
    
    /**
     * Assign this Student a new ID.  The new ID is the next ID that 
     * is to be assigned.
     */
    public void assignNewID() {
        studentID = nextID;
        nextID++;
    }
    
    /**
     * Return a string representation of this Student object.
     * 
     * @return a string representation of this Student.
     */
    public String toString() {
        return name + "\t(" + studentID + ")";
        // \t prints a tab.
    }
    
    /**
     * Create a few student objects to illustrate the use of the static field
     * for assigning unique student ID numbers.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        Student s1 = new Student("Bob");
        Student s2 = new Student("Jane");
        Student s3 = new Student("Sam");
        
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
