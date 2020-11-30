package coffeeCalculator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CoffeeCalculatorTest.
 *
 * @author Xiang Wei
 * @version 10/09/2013
 */
public class CoffeeCalculatorTest {
    private CoffeeCalculator coffeeCalc1;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        coffeeCalc1 = new CoffeeCalculator(60, true);
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
        assertEquals(60, coffeeCalc1.getTemperature());
        assertEquals(true, coffeeCalc1.getIsWeekend());
        assertEquals(0, coffeeCalc1.getAmountSpent());
    }

    @Test
    public void testIsOverBudget() {
        assertEquals(false, coffeeCalc1.isOverBudget());
        coffeeCalc1.setAmountSpent(600);
        assertEquals(600, coffeeCalc1.getAmountSpent());
        assertEquals(true, coffeeCalc1.isOverBudget());
    }

    @Test
    public void testCalculateCoffeeType() {
        assertEquals("regular", coffeeCalc1.calculateCoffeeType());
        coffeeCalc1.setTemperature(35);
        assertEquals("latte", coffeeCalc1.calculateCoffeeType());

        CoffeeCalculator coffeeCalc2 = new CoffeeCalculator(25, false);
        assertEquals("mocha", coffeeCalc2.calculateCoffeeType());
        coffeeCalc2.setTemperature(50);
        assertEquals("cappuccino", coffeeCalc2.calculateCoffeeType());
        coffeeCalc2.setTemperature(75);
        assertEquals("iced", coffeeCalc2.calculateCoffeeType());
    }

    @Test
    public void testBuyCoffee() {
        coffeeCalc1.buyCoffee(50);
        assertEquals(412, coffeeCalc1.getAmountSpent());
    }
}