package postalRateCalculator;

/**
 * This class defines an object that is useful for calculating the postal
 * rate for sending a particular package.  Each package has a weight and is to 
 * be shipped via a specified postage class.  The cost of shipping a package
 * depends on both the weight of the package and the postage class used for
 * shipping.  The constructor and methods for this class are described in detail
 * below.
 * 
 * @author Tim Wahls
 * @author Xiang Wei
 * @version 10/02/2013
 */
public class RateCalculator
{
    private int weight; // measured in ounces
    private int rateClass; // either one or two
    
    /**
     * set the postal rate class and the package weight
     * @param initWeight the weight of the package in ounces
     * @param initRateClass either first or second class (1 or 2)
     */
    public RateCalculator(int initWeight, int initRateClass)
    {
        weight = initWeight;
        rateClass = initRateClass;
    }

    /**
     * @return the weight of the package
     */
    public int getWeight()
    {
        return weight;
    }
        
    /**
     * @return the rate class of the package
     */
    public int getRateClass()
    {
        return rateClass;
    }
        
    /**
     * @return whether or not the package is to be sent first class
     */
    public boolean isFirstClass() {
            return rateClass == 1;
    }
    
    /**
     * increase the weight of the package
     * @param increaseBy the number of ounces to increase the weight by
     */
    public void increaseWeight(int increaseBy) {
        weight = weight + increaseBy;
    }
        
    /**
     * @return the amount of the package's weight that will be charged at the
     * highest rate (for postage).  For packages under 16 ounces, the full
     * weight of the package is charged at the highest rate.  For packages
     * 16 ounces or heavier, only 16 ounces are charged at the highest rate.
     */
    public int getWeightChargedAtHighestRate() {
        if (weight < 16) {
            return weight;
        }
        
        else {
            return 16;
        }
      
    }
   
    /** 
     * @return the total cost of sending the package (in dollars).<br>
     * For first class packages, the rate is 34 cents per ounce for the first
     * 16 ounces, plus 15 cents per ounce for each ounce over 16 ounces.<br>
     * For second class packages, the rate is 20 cents per ounce for the first
     * 16 ounces, plus 15 cents per ounce for each ounce over 16 ounces.<br>
     * The total cost is always just in dollars - any fractions of a dollar
     * resulting from the above calculation are discarded.
     */
    public int getPostageBill() {
        int cost = 0;
        if (rateClass == 1) {
            if (weight <= 16) {
                
                cost = (34 * weight) / 100;
            }
        
            else {
                cost = ((34 * 16) + ((weight - 16) * 15)) / 100;
            } 
        }
        if (rateClass == 2) {
            if (weight <= 16) {
                cost = (20 * weight) / 100;
            }
        
            else {  
                cost = ((20 * 16) + ((weight - 16) * 15)) / 100;
            }
        }
        
        return cost;
    }
}


