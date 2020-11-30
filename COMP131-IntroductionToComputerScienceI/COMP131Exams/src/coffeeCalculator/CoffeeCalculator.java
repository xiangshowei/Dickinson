package coffeeCalculator;

/**
 * This class defines an object that can be used to compute what type of coffee
 * to buy on a particular day, and to keep track of how much has been spent on
 * coffee that day. The type of coffee drink to buy (for example, cappuccino or
 * latte) depends on the current temperature, and whether or not it is a
 * weekend.
 * 
 * @author John MacCormick
 * @author Xiang Wei
 * @version 10/09/2013
 */
public class CoffeeCalculator {
    private int temperature; // in degrees Farenheit
    private boolean isWeekend;
    private int amtSpent; // in cents

    /**
     * This constructor creates a new CoffeeCalculator using the given initial
     * temperature (a whole number measured in degrees Fahrenheit) and a Boolean
     * value specifying whether or not it is a weekend. The amount spent on coffee
     * so far, which should be stored as a whole number of cents.
     * 
     * @param initTemperature the temperature
     * @param initIsWeekend   if the day is weekend or not
     */
    public CoffeeCalculator(int initTemperature, boolean initIsWeekend) {
        temperature = initTemperature;
        isWeekend = initIsWeekend;
        amtSpent = 0;
    }

    /**
     * Get the temperature.
     * 
     * @return the temperature
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Whether or not it is a weekend.
     * 
     * @return whether or not it is a weekend
     */
    public boolean getIsWeekend() {
        return isWeekend;
    }

    /**
     * Returns the amount spent today on coffee.
     * 
     * @return the amount spent on coffee so far today, measured in cents
     * 
     */
    public int getAmountSpent() {
        return amtSpent;
    }

    /**
     * Sets the amount of money spend to a specified value.
     * 
     * @param newAmountSpent the new amount Spent
     */
    public void setAmountSpent(int newAmountSpent) {
        amtSpent = newAmountSpent;
    }

    /**
     * Sets the the temperature of coffee purchase date.
     * 
     * @param newAmountSpent the new amount Spent
     */
    public void setTemperature(int newTemperature) {
        temperature = newTemperature;
    }

    /**
     * The daily coffee budget is five dollars. This method returns true if the
     * amount spent on coffee so far today is more than five dollars.
     * 
     * @return if money spent is over the allowed budget
     */
    public boolean isOverBudget() {
        return amtSpent > 500;
    }

    /**
     * Return the type of coffee to be ordered based on current conditions.
     * 
     * On weekends, the rule is simple: when the temperature is below 50, a latte is
     * ordered; whereas if the temperature is 50 or above, a regular coffee is
     * ordered.
     * 
     * On weekdays, the rule is more complex: for temperatures below 40, a mocha is
     * ordered; for temperatures between 40 and 70 inclusive, a cappuccino is
     * ordered; and for temperatures above 70, an iced coffee is ordered.
     * 
     * @return the type of coffee ordered
     */
    public String calculateCoffeeType() {
        String coffeeType = "";
        if (isWeekend) {
            if (temperature < 50) {
                coffeeType = "latte";
            }

            else {
                coffeeType = "regular";
            }
        }

        else {// weekday
            if (temperature < 40) {
                coffeeType = "mocha";
            }

            else if (temperature >= 40 && temperature <= 70) {
                coffeeType = "cappuccino";
            }

            else {
                coffeeType = "iced";
            }
        }
        return coffeeType;
    }

    /**
     * Increase the amount spent so far on coffee by the discounted cost of one
     * coffee. All coffees have the same base price of $2.75. However, a different
     * discount is applied to the price of a coffee every time one is purchased.
     * 
     * The amount of the discount is always rounded down to the nearest cent before
     * it is subtracted from the base price.
     * 
     * @param discountPercent percent of discount
     */
    public void buyCoffee(int discountPercent) {
        amtSpent += 275 + (275 *  (100 - discountPercent) / 100);
    }
}