package postalRateCalculator;

/**
 * This class defines an object that is useful for calculating the postal
 * rate for sending a particular package.  Each package has a weight and is to 
 * be shipped via a specified postage class.  The cost of shipping a package
 * depends on both the weight of the package and the postage class used for shipping.  
 * 
 * @author Tim Wahls
 * @author Xiang Wei
 * @version 10/02/2013
 */
public class RateCalculator {
    private int packageWeight; // measured in ounces
    private int rateClass; // either one or two
    
    /**
     * Set the postal rate class and the package weight
     * 
     * @param initWeight the weight of the package in ounces
     * @param initRateClass either first or second class (1 or 2)
     */
    public RateCalculator(int initWeight, int initRateClass) {
        packageWeight = initWeight;
        rateClass = initRateClass;
    }

    /**
     * Returns the weight of the package
     * 
     * @return the weight of the package
     */
    public int getWeight() {
        return packageWeight;
    }
        
    /**
     * Returns the shipping class of the package
     * 
     * @return the shipping class of the package
     */
    public int getRateClass() {
        return rateClass;
    }
        
    /**
     * Returns whether the package is being sent via first class
     * 
     * @return whether or not the package is to be sent first class
     */
    public boolean isFirstClass() {
        return rateClass == 1;
    }
    
    /**
     * Increase the weight of the package
     * 
     * @param weightToIncreaseBy the number of ounces to increase the weight by
     */
    public void increaseWeight(int weightToIncreaseBy) {
        packageWeight += weightToIncreaseBy;
    }
        
    /**
     * For packages under 16 ounces, the full
     * weight of the package is charged at the highest rate.  For packages
     * 16 ounces or heavier, only 16 ounces are charged at the highest rate.
     * 
     * @return the amount of the package's weight that will be charged at the
     * highest rate (for postage).
     */
    public int getWeightChargedAtHighestRate() {
        if (packageWeight < 16) {
            return packageWeight;
        }
        
        else {
            return 16;
        }
    }
   
    /** 
     * For first class packages, the rate is 34 cents per ounce for the first
     * 16 ounces, plus 15 cents per ounce for each ounce over 16 ounces.
     * 
     * For second class packages, the rate is 20 cents per ounce for the first
     * 16 ounces, plus 15 cents per ounce for each ounce over 16 ounces.
     * 
     * The total cost is always just in dollars - any fractions of a dollar
     * resulting from the above calculation are discarded.
     * 
     * @return the total cost of sending the package (in dollars).
     */
    public int getPostageBill() {
        int standardWeight = 16;
        double firstClassBaseRate = 0.34;
        double secondClassClassBaseRate = 0.2;
        double overWeightRate = 0.15;
        int cost = 0;


        if (rateClass == 1) {
            if (packageWeight <= standardWeight) {
                cost = firstClassBaseRate * packageWeight;
            }
        
            else {
                cost = (firstClassBaseRate * standardWeight) + ((packageWeight - standardWeight) * overWeightRate);
            } 
        }
        else { // second class
            if (packageWeight <= standardWeight) {
                cost = secondClassClassBaseRate * packageWeight;
            }
        
            else {  
                cost = (secondClassClassBaseRate * standardWeight) + ((packageWeight - standardWeight) * overWeightRate);
            }
        }
        
        return cost;
    }
}


