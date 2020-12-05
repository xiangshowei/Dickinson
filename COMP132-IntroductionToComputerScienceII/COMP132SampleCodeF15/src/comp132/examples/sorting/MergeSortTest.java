package comp132.examples.sorting;


import static org.junit.Assert.*;

import org.junit.*;

public class MergeSortTest {

    private int[] arr;
    private int[] arr2;
    private int[] arr3;
    
    @Before
    public void setUp() throws Exception {
        arr = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        arr2 = new int[] {1,3,5,7,9,10,11};
        arr3 = new int[] {2,4,6,8};
    }
    
    @Test
    public void testMergeTailInA1() {
        int[] merged = MergeSort.merge(arr2, arr3);
        assertEquals("wrong length", 11, merged.length);
        for (int i=0; i<merged.length; i++) {
            assertEquals("wrong " + i + "th value", i+1, merged[i]);
        }
    }

    @Test
    public void testMergeTailInA2() {
        int[] merged = MergeSort.merge(arr3, arr2);
        assertEquals("wrong length", 11, merged.length);
        for (int i=0; i<merged.length; i++) {
            assertEquals("wrong " + i + "th value", i+1, merged[i]);
        }
    }
}
