package comp132.examples.sorting;

import static org.junit.Assert.*;

import org.junit.*;

public class ArrayToolsTest {

    private int[] arr;
    
    @Before
    public void setUp() throws Exception {
        arr = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    @Test
    public void testMinMaxLengthIntArray() {
        int[] arr = ArrayTools.getRandomIntArray(-5, 6, 1000);
        assertEquals("wrong length", 1000, arr.length);
        int min = 10;
        int max = -10;
        for (int v : arr) {
            if (v < min) min = v;
            if (v > max) max = v;
        }
        assertEquals("min doesn't appear", -5, min);
        assertEquals("max doesn't appear", 5, max);
    }
    
    @Test
    public void testRandomnessIntArray() {
        int[] arr = ArrayTools.getRandomIntArray(0, 25, 1000);
        boolean[] vals = new boolean[25];
        for (int v : arr) {
            vals[v] = true;
        }
        
        for (int i=0; i<vals.length; i++) {
            assertTrue("value " + i + " doesn't appear", vals[i]);
        }
    }
    
    @Test
    public void testRandomnessIntArrayNegVals() {
        int[] arr = ArrayTools.getRandomIntArray(-10, 0, 1000);
        boolean[] vals = new boolean[11];
        for (int v : arr) {
            vals[-v] = true;
        }
        
        for (int i=1; i<vals.length; i++) {
            assertTrue("value " + i + " doesn't appear", vals[i]);
        }
    }
    
    @Test
    public void testRandomnessIntArrayPosNegVals() {
        int[] arr = ArrayTools.getRandomIntArray(-10, 11, 1000);
        boolean[] vals = new boolean[21];
        for (int v : arr) {
            vals[v+10] = true;
        }
        
        for (int i=0; i<vals.length; i++) {
            assertTrue("value " + i + " doesn't appear", vals[i]);
        }
    }
    
    @Test
    public void testPrintArray() {
        int[] arr = ArrayTools.getRandomIntArray(0, 25, 50);
        ArrayTools.printIntArray(arr);
    }
    
    @Test
    public void testGetSubarrayStart() {
        int[] sub = ArrayTools.getSubarray(arr, 0, 5);
        assertEquals("wrong length", 5, sub.length);
        assertEquals("wrong 0th element", 0, sub[0]);
        assertEquals("wrong last element", 4, sub[4]);
    } 
    
    @Test
    public void testGetSubarrayMiddle() {
        int[] sub = ArrayTools.getSubarray(arr, 2, 8);
        assertEquals("wrong length", 6, sub.length);
        assertEquals("wrong 0th element", 2, sub[0]);
        assertEquals("wrong last element", 7, sub[5]);
    }
    
    @Test
    public void testGetSubarrayEnd() {
        int[] sub = ArrayTools.getSubarray(arr, 5, 10);
        assertEquals("wrong length", 5, sub.length);
        assertEquals("wrong 0th element", 5, sub[0]);
        assertEquals("wrong last element", 9, sub[4]);
    }
}
