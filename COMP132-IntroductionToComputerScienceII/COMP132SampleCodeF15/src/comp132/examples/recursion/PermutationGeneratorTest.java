package comp132.examples.recursion;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class PermutationGeneratorTest {

    @Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void testPermsBaseCase() {
        ArrayList<String> perms = PermutationGenerator.generatePermutations("A");
        assertEquals("should only be 1 permutation", 1, perms.size());
        assertEquals("perm should be itself", "A", perms.get(0));
    }

    @Test
    public void testPermutationsExplicitly() {
        ArrayList<String> perms = PermutationGenerator.generatePermutations("ABC");
        assertEquals("should be 6 permutations", 6, perms.size());
        assertEquals("first", "ABC", perms.get(0));
        assertEquals("second", "ACB", perms.get(1));
        assertEquals("third", "BAC", perms.get(2));
        assertEquals("fourth", "BCA", perms.get(3));
        assertEquals("fifth", "CAB", perms.get(4));
        assertEquals("sixth", "CBA", perms.get(5));
    }
    
    @Test
    public void testPermutationsSize() {
        ArrayList<String> perms = PermutationGenerator.generatePermutations("ABCDEF");
        assertEquals("wrong # of permutations", 6*5*4*3*2*1, perms.size());
    }
}
