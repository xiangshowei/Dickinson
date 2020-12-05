package lab09.list;

import static org.junit.Assert.*;
import org.junit.*;

public class CS132ArrayListTest {

    /*
     * If you use myList in all of your tests and use only the methods in the
     * CS132List interface, then the CS132SinglyLinkedListTest
     * class will allow you to run all of your tests against your
     * CS132LinkedList class as well.  Thus, you will only need to create
     * one set of tests!
     */
    protected CS132List myList;
    private String str1;
    private String str2;
    private String str3;

    @Before
    public void setUp() throws Exception {
        myList = new CS132ArrayList();
        this.createTestFixtures();  
    }

    @Test
    public void testConstructor() {
    	assertEquals("myList should have no elements", 0, myList.size());
    }
    
    @Test
    public void testAddArrayFull() {
    	for(int i = 0; i <= 5; i++){
    		myList.add(str1);
    	}  
    	
    	//the initial Array only has 5 slots so passing the assert statement means 
    	//that a new Array twice the size of the original Array was created
    	assertEquals("myList should have 6 elements", 6, myList.size());
    }
    
    @Test
    public void testAddArrayNotFull() {
    	assertEquals("myList should have no elements", 0, myList.size());
    	myList.add(str1);
    	myList.add(str2);
    	assertEquals("myList should 2 elements", 2, myList.size());
    	assertEquals("the first element should a String for 'one' ", "one", myList.get(0));
    	assertEquals("the second element should a String for 'a' ", "a", myList.get(1));
    }
    
    @Test 
    public void tetsGetNoExceptionThrown() {
    	myList.add(str1);
    	myList.add(str2);
    	assertEquals("the first element should a String for 'one' ", "one", myList.get(0));
    	assertEquals("the second element should a String for 'a' ", "a", myList.get(1));
    }
    
    @Test 
    public void testGetThrowsExceptionIndexLessThanZero() {
    	try{
    		myList.get(-1);
    		fail("should have thrown IndexOutOfBoundsException");
    	}
    	catch (IndexOutOfBoundsException ioobe) {
    		//pass
    	}
    	catch (Exception e){
    		System.out.println("wrong type of exception was thrown");
    	}
    }
    
    @Test
    public void testGetThrowsExceptionExceedsArraySize() {
    	try{
    		myList.get(5);
    		fail("should have thrown IndexOutOfBoundsException");
    	}
    	catch (IndexOutOfBoundsException ioobe) {
    		//pass
    	}
    	catch (Exception e){
    		System.out.println("wrong type of exception was thrown");
    	}
    }
    
    @Test
    public void testSetNoExpcetionThrown() {
    	myList.add(str1);
    	assertEquals("the first element should a String for 'one' ", "one", myList.get(0));
    	myList.set(0, "two");
    	assertEquals("the String at index 0 should be 'two'" , "two", myList.get(0));
    }
    
    @Test 
    public void testSetThrowsExceptionIndexLessThanZero() {
    	try{
    		myList.set(-1, str1);
    		fail("should have thrown IndexOutOfBoundsException");
    	}
    	catch (IndexOutOfBoundsException ioobe) {
    		//pass
    	}
    	catch (Exception e){
    		System.out.println("wrong type of exception was thrown");
    	}
    }
    
    @Test
    public void testSetThrowsExceptionExceedsArraySize() {
    	try{
    		myList.set(5, str1);
    		fail("should have thrown IndexOutOfBoundsException");
    	}
    	catch (IndexOutOfBoundsException ioobe) {
    		//pass
    	}
    	catch (Exception e){
    		System.out.println("wrong type of exception was thrown");
    	}
    }
    
    @Test
    public void testInsertBeginningOfArrayNotFullNoExceptionThrownExistingElements() {
    	myList.add(str1); 
    	assertEquals("myList should have 1 elements", 1, myList.size());
    	myList.insert(0, str2);
    	assertEquals("the element at index 0 should now be 'a'", "a", myList.get(0));
    	assertEquals("myList should have 2 elements", 2, myList.size()); 
    } 
    
    @Test
    public void testInsertBeginningOfArrayNotFullNoExceptionThrownNoElements() {
    	assertEquals("myList should have no elements", 0, myList.size());
    	myList.insert(0, str1);
    	assertEquals("myList should have one element", 1, myList.size());
    	assertEquals("the element at index 0 should 'one'", "one", myList.get(0));
    }
    
    @Test
    public void testInsertMiddleOfArrayNotFullNoExceptionThrown() {
    	myList.add(str1);
    	myList.add(str2); 
    	myList.add(str3);  
    	assertEquals("myList should have 3 elements", 3, myList.size());
    	
    	myList.insert(1, "two");
    	assertEquals("the element at index 1 should now be 'two'", "two", myList.get(1));
    	assertEquals("myList should now have 4 elements", 4, myList.size());
    	//checking to see if the element after insert() was called was moved correctly
    	assertEquals("the element at index 2 should be 'a'", "a", myList.get(2));
    }
   
    @Test
    public void testInsertArrayFullNoExceptionThrown() {
    	for(int i = 0; i <= 4; i++){
    		myList.add(str1);
    	}  
    	assertEquals("myList should have 5 elements", 5, myList.size());
    	
    	myList.insert(2, "three"); 
    	assertEquals("the element at index 2 should be 'three'", "three", myList.get(2));
    	assertEquals("myList should now have 6 elements", 6, myList.size());
    	//checking to see if the element after insert() was called was moved correctly
    	assertEquals("the element at index 3 should be 'one'", "one", myList.get(3)); 
    }
    
    @Test
    public void testInsertArrayExceptionThrownIndexLessThanZero() {
    	try{
    		myList.insert(-1, str1);
    		fail("should have thrown IndexOutOfBoundsException");
    	}
    	catch (IndexOutOfBoundsException ioobe) {
    		//pass
    	}
    	catch (Exception e){
    		System.out.println("wrong type of exception was thrown");
    	}
    }
    
    @Test
    public void testInsertThrowsExceptionExceedsArraySize() {
    	try{
    		myList.insert(5, str1);
    		fail("should have thrown IndexOutOfBoundsException");
    	}
    	catch (IndexOutOfBoundsException ioobe) {
    		//pass
    	}
    	catch (Exception e){
    		System.out.println("wrong type of exception was thrown");
    	}
    }
    
    @Test
    public void testRemoveNoExceptionThrown() {
    	myList.add(str1); 
    	myList.add(str2);
    	myList.add(str3);
    	myList.add("things");
    	myList.add("stuff");
    	
    	assertEquals("myList should have 5 elements", 5, myList.size());
    	
    	String removedStr = (String) myList.remove(1);
    	assertEquals("myList should now have 2 elements", 4, myList.size());
    	assertEquals("the removed element should a String object that is 'a'", "a", removedStr);
    	assertEquals("the String at index 1 should now be 'three'", "y", myList.get(1)); 
    }
    
    @Test
    public void testRemoveExceptionThrownIndexLessThanZero() {
    	try{
    		myList.remove(-1);
    		fail("should have thrown IndexOutOfBoundsException");
    	}
    	catch (IndexOutOfBoundsException ioobe) {
    		//pass
    	}
    	catch (Exception e){
    		System.out.println("wrong type of exception was thrown");
    	}
    }
    
    @Test
    public void testRemoveExceptionThrownExceedsArraySize() {
    	try{
    		myList.remove(5);
    		fail("should have thrown IndexOutOfBoundsException");
    	}
    	catch (IndexOutOfBoundsException ioobe) {
    		//pass
    	}
    	catch (Exception e){
    		System.out.println("wrong type of exception was thrown");
    	}
    }
    
    protected void createTestFixtures() { 
    	 str1 = "one";
         str2 = "a";
         str3 = "y"; 
    }
}
