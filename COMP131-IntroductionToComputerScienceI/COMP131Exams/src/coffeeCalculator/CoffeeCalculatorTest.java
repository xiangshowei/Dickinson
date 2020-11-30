package coffeeCalculator;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CoffeeCalculatorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CoffeeCalculatorTest{
    private CoffeeCalculator latte;
    private CoffeeCalculator regularCoffee;
    private CoffeeCalculator mocha;
    private CoffeeCalculator cappuccino;
    private CoffeeCalculator icedCoffee;

    /**
     * Default constructor for test class CoffeeCalculatorTest
     */
    public CoffeeCalculatorTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        latte = new CoffeeCalculator(40, true);
        regularCoffee = new CoffeeCalculator(60, true);
        mocha = new CoffeeCalculator(30, false);
        cappuccino = new CoffeeCalculator(50, false);
        icedCoffee = new CoffeeCalculator(80, false);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testAccessorMethods()
    {
        CoffeeCalculator coffeeCa1 = new CoffeeCalculator(40, true);
        assertEquals(0, coffeeCa1.getAmountSpent());
        assertEquals(true, coffeeCa1.getIsWeekend());
        assertEquals(40, coffeeCa1.getTemperature());
    }

    @Test
    public void testBudgetandAmountSpent()
    {
        CoffeeCalculator coffeeCa1 = new CoffeeCalculator(40, true);
        coffeeCa1.setAmountSpent(600);
        assertEquals(600, coffeeCa1.getAmountSpent());
        assertEquals(true, coffeeCa1.isOverBudget());
        coffeeCa1.setAmountSpent(400);
        assertEquals(false, coffeeCa1.isOverBudget());
    }

    @Test
    public void testBuyCoffeeType()
    {
        assertEquals("latte", latte.calculateCoffeeType());
        assertEquals("regular coffee", regularCoffee.calculateCoffeeType());
        assertEquals("mocha", mocha.calculateCoffeeType());
        assertEquals("cappuccino", cappuccino.calculateCoffeeType());
        assertEquals("iced coffee", icedCoffee.calculateCoffeeType());
    }

    @Test
    public void testEveryCoffee()
    {
        latte.buyCoffee(10);
        regularCoffee.buyCoffee(30);
        mocha.buyCoffee(20);
        cappuccino.buyCoffee(25);
        icedCoffee.buyCoffee(40);
    }
}



