package comp132.examples.recursion;

/**
 * Checks if a given string is a palindrome. This program does not account for
 * spaces or punctuation. So "ABBA" will be a reported as a palindrome however,
 * "Madam I'm Adam" will not be reported as a palindrome. One of the homework
 * questions asks you to fix this issue.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 21, 2009
 */
public class PalindromeChecker {

    /**
     * Check if the specified string is a palindrome. This solution is a
     * somewhat inefficient because it builds a new String (via substring) for
     * each recursive call. The isPalindrome2 method below avoids this
     * inefficiency.
     * 
     * @param str the string to be checked.
     * @return true if str is a palindrome.
     */
    public static boolean isPalindrome(String str) {
        if (str.length() <= 1) {
            // Any string with 0 or 1 chars is a palindrome.
            return true;
        }
        else {
            // Get the first and last characters of the string.
            char first = Character.toLowerCase(str.charAt(0));
            char last = Character.toLowerCase(str.charAt(str.length() - 1));

            if (first != last) {
                // First and last characters are different - so not a palindrome
                return false;
            }
            else {
                // first and last letters are the same so check without them.
                return isPalindrome(str.substring(1, str.length() - 1));
            }
        }
    }

    /**
     * Check if the characters between start and end in str are a palindrome.
     * This recursive helper method method adds two additional parameters, start
     * and end, indicating the range of characters in the string to be checked.
     * This makes it possible to avoid the inefficiency of creating a new String
     * object for each recursive call as was done in isPalindrome above.
     * 
     * @param str the string to check.
     * @param start the index of the first character in the string to consider.
     * @param end the index of the last character in the string to consider.
     * @return true if the characters between start and end form a palindrome.
     */
    private static boolean isPalindrome2(String str, int start, int end) {
        if (end - start <= 0) {
            // Any string with 0 or 1 chars is a palindrome.
            return true;
        }
        else {
            // Get the first and last characters of the string.
            char first = Character.toLowerCase(str.charAt(start));
            char last = Character.toLowerCase(str.charAt(end));

            if (first != last) {
                // First and last characters are different - so not a palindrome
                return false;
            }
            else {
                // first and last letters are the same so check without them.
                return isPalindrome2(str, start + 1, end - 1);
            }
        }
    }

    /**
     * Check if the specified string is a palindrome. This method makes use of
     * the isPalindrome2Helper method to actually solve the problem. It is
     * provided as a convenience for the client code that calls it. When a
     * recursive helper method is created with additional parameters that make
     * it easier to write or more efficient a convenience method such as this
     * one is often added to make calls from client code more natural.
     * 
     * @param str the string to be checked.
     * @return true if str is a palindrome.
     */
    public static boolean isPalindrome2(String str) {
        return isPalindrome2(str, 0, str.length() - 1);
    }

    /**
     * Check a string to see if it is a palindrome.
     * 
     * @param args the string to be checked.
     */
    public static void main(String[] args) {

        String str = "ABBA";

        // Check the string using the isPalindrome method.
        if (isPalindrome(str)) {
            System.out.println(str + " is a palindrome.");
        }
        else {
            System.out.println(str + " is not a palindrome.");
        }

        // Check the string using the isPalindrome2 method.
        if (isPalindrome2(str)) {
            System.out.println(str + " is a palindrome.");
        }
        else {
            System.out.println(str + " is not a palindrome.");
        }
    }
}
