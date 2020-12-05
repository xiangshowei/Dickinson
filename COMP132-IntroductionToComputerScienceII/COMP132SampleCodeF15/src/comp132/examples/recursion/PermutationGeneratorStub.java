package comp132.examples.recursion;

import java.util.*;

/**
 * Generates a list of all of the permutations of the characters of a given
 * string.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 21, 2009
 */
public class PermutationGeneratorStub {

    /**
     * Generate all of the permutations of the characters in the specified
     * String.
     * 
     * @param str the string of characters to permute.
     * @return the permutations of the characters in str.
     */
    public static ArrayList<String> generatePermutations(String str) {
        return null;
    }

    /**
     * Generate and print all permutations of a given string.
     */
    public static void main(String[] args) {

        ArrayList<String> perms = generatePermutations("ABC");
        
        for (String s : perms) {
            System.out.println(s);
        }
    }
}
