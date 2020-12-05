package comp132.examples.recursion;

import static org.junit.Assert.*;
import org.junit.*;

public class PalindromeCheckerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testPalindromeEmptyString() {
        boolean b = PalindromeChecker.isPalindrome("");
        assertTrue("empty string is a palindrome", b);
        
        boolean bp = PalindromeChecker.isPalindrome("");
        assertTrue("empty string is a palindrome", bp);
    }
    
    @Test
    public void testPalindromeOneChar() {
        boolean b = PalindromeChecker.isPalindrome("A");
        assertTrue("one char string is a palindrome", b);
        
        boolean bp = PalindromeChecker.isPalindrome("A");
        assertTrue("one char string is a palindrome", bp);
    }
    
    
    @Test
    public void testVariousNonPalindromes() {
        boolean b = PalindromeChecker.isPalindrome("xABBAy");
        assertFalse("non-match at start", b);
        boolean bp = PalindromeChecker.isPalindrome("xABBAy");
        assertFalse("non-match at start", bp);
        
        
        boolean b2 = PalindromeChecker.isPalindrome("AxBByA");
        assertFalse("non-match in middle", b2);
        boolean bp2 = PalindromeChecker.isPalindrome("AxBByA");
        assertFalse("non-match in middle", bp2);
        
        boolean b3 = PalindromeChecker.isPalindrome("ABxyBA");
        assertFalse("non-match at end", b3);
        boolean bp3 = PalindromeChecker.isPalindrome("ABxyBA");
        assertFalse("non-match at end", bp3);
        
        boolean b4 = PalindromeChecker.isPalindrome("Madam I'm Adam!");
        assertFalse("is not a palindrome by this program", b4);
        boolean bp4 = PalindromeChecker.isPalindrome("Madam I'm Adam!");
        assertFalse("is not a palindrome by this program", bp4);
        
        boolean b5 = PalindromeChecker.isPalindrome("A man, a plan, a canal, PANAMA.");
        assertFalse("is not a palindrome by this program", b5);
        boolean bp5 = PalindromeChecker.isPalindrome("A man, a plan, a canal, PANAMA.");
        assertFalse("is not a palindrome by this program", bp5);
    }
    
    @Test
    public void testVariousPalandromes() {
        boolean b = PalindromeChecker.isPalindrome("ABBA");
        assertTrue("should be a palindrome", b);
        boolean bp = PalindromeChecker.isPalindrome("ABBA");
        assertTrue("should be a palindrome", bp);
        
        boolean b2 = PalindromeChecker.isPalindrome("ABCBA");
        assertTrue("should be a palindrome", b2);
        boolean bp2 = PalindromeChecker.isPalindrome("ABCBA");
        assertTrue("should be a palindrome", bp2);
    }
}
