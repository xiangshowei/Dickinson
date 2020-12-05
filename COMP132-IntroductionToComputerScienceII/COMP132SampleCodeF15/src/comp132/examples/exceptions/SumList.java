package comp132.examples.exceptions;

import java.util.*;

/**
 * Small program that reads integers from the user and computes their
 * total.  Demonstrates basic exception handling by dealing with user
 * input that cannot be converted into an int value.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Oct 11, 2009
 */
public class SumList {

    /**
     * Read a list of integer values from the user and keep a running total
     * of all of the values.  If an invalid value is entered an error is displayed
     * and the value is omitted from the sum.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        int total = 0;
        String input = "";
        Scanner scr = new Scanner(System.in);
        
        while (!input.equals("Q")) {
            System.out.print("Enter an integer to add to sum [Q to quit]: ");
            
            input = scr.nextLine();
            
            if (!input.equals("Q")) {   
                /*
                 * Use try/catch to handle the possibility that the user enters
                 * a value that cannot be converted to an int.  If parseInt is
                 * successful, the remaining code in the try block will execute.
                 * If parseInt throws a NumberFormatException then the code in the
                 * catch for that exception will execute.
                 */
                try {
                    int val = Integer.parseInt(input);
                    total = total + val;
                    System.out.println("Sub Total: " + total);
                }
                catch(NumberFormatException e) {
                    System.out.println(input + " is not an integer.");
                }
            }
        }
        
        System.out.println("Grand Total: " + total);
        
        scr.close();
    }
}
