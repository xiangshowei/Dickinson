package postalRateCalculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RateCalculatorTest.
 *
 * @author  Xiang Wei
 * @version 10/02/2013
 */
public class RateCalculatorTest {

    private RateCalculator rc1;
    private RateCalculator rc2;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        rc1 = new RateCalculator(15, 1);
        rc2 = new RateCalculator(10, 2);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertEquals(15, rc1.getWeight());
        assertEquals(15, rc1.getWeightChargedAtHighestRate());
        rc1.increaseWeight(5);
        assertEquals(20, rc1.getWeight());
        assertEquals(16, rc1.getWeightChargedAtHighestRate());
        assertEquals(1, rc1.getRateClass());
    }

    @Test
    public void testIsFirstClass() {
        assertEquals(true, rc1.isFirstClass());
        assertEquals(false, rc2.isFirstClass()); 
    }

    @Test
    public void testGetPostBillFirstClass() {
        assertEquals(5, rc1.getPostageBill());
        rc1.increaseWeight(5);
        assertEquals(20, rc1.getWeight());
        assertEquals(6, rc1.getPostageBill());
    }

    @Test
    public void testGetPostBillSecondClass() {
        assertEquals(2, rc2.getPostageBill());
        rc2.increaseWeight(10);
        assertEquals(20, rc2.getWeight());
        assertEquals(3, rc2.getPostageBill());
    }
}
