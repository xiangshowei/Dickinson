package comp132.examples.staticex;

/**
 * A class that illustrates the use of static methods in the Integer, Double and
 * Math classes from the Java SDK.
 * 
 * To run this program from a terminal:
 * - open a terminal
 * - cd into the 132SampleCode project folder where ever it is.
 *   - ls should show comp132 as the only contents.
 * - use the command:
 *     java comp132.examples.staticex.JavaStaticMethods 3 4
 * - Try other values (e.g. 2.7 3.5) for the command line arguments.
 * - What happens if you use abc as an argument?
 * - Try computing other results (cube root, 5th power, etc)
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 16, 2009
 */
public class JavaStaticMethods {

    /**
     * Compute the square root of the first command line argument raised to the
     * power of the second command line argument.
     * 
     * @param args two numeric values that will be interpreted as double values.
     */
    public static void main(String[] args) {

        /*
         * Use static methods in the Double class to convert the String
         * arguments into double values that can be used for arithmetic
         * operations.
         */
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);

        /*
         * Use static methods in the Math class to do the computation.
         */
        double xRaisedToY = Math.pow(x, y);
        double sqrtXRaisedToY = Math.sqrt(xRaisedToY);

        System.out.println("The square root of " + x + " raised to " + y + " is " + sqrtXRaisedToY);
    }
}
