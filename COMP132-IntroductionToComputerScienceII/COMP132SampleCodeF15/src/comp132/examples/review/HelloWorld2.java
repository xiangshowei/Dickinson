package comp132.examples.review;

/**
 * Hello world program that demonstrates the use of command
 * line arguments in Java.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 10, 2009
 */
public class HelloWorld2 {

    /**
     * Print out "Hello world!" followed by the number and value
     * of each of the command line arguments that were provided
     * to the program.
     * 
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("There are " + args.length + " arguments.");
        for (int i=0; i<args.length; i++) {
            System.out.println("\t" + args[i]);
        }
    }
}
