package comp132.examples.inheritance.abstractClass;

/**
 * An hourly employee is paid their payRate for each hour worked.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 14, 2009
 */
public class HourlyEmployee extends Employee {

    public static final int BASE_HOURS_PER_WEEK = 40;

    public HourlyEmployee(String name, double payRate) {
        super(name, payRate);
    }

    public double getBasePay() {
        return payRate * BASE_HOURS_PER_WEEK;
    }

    public void addOvertime(double hours) {
        /*
         * Can have other hourly employee specific methods here too.
         */
    }
}
