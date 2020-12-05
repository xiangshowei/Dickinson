package lab09.list;

import org.junit.Before;

public class ReferenceCS132SinglyLinkedListTest extends ReferenceCS132ArrayListTest {

    /*
     * Because the tests in CS132ArrayListTest all use the CS132List interface
     * this class will inherit all of those tests and use the
     * CS132SinglyLinkedList instead!
     */

    @Before
    public void setUp() throws Exception {
        myList = new CS132SinglyLinkedList();
    }
}
