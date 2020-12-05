package comp132.examples.inheritance.abstractClass;

/**
 * Salaried employees receive 1/52 of their pay each week.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 14, 2009
 */
public class SalariedEmployee extends Employee {

    public SalariedEmployee(String name, double payRate) {
        super(name, payRate);
    }

    public double getBasePay() {
        return payRate / 52.0;
    }

    public void addBonus(double bonus) {
        /*
         * Can have other salaried employee specific methods here too.
         */
    }
}
