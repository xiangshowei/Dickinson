package comp132.examples.inheritance.abstractClass;

/**
 * Representation of an employee with a name and a pay rate. The meaning of the
 * Pay rate depends upon the type of employee (Salaried, Hourly etc...). All
 * Employee's must have a method for computing their weekly base pay but that
 * mechanism differs by the type of Employee. Thus, there is no meaningful
 * implementation of computeBasePay() that can appear in the Employee class.
 * Thus it is defined as an abstract method.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 14, 2009
 */
public abstract class Employee {
    private String name;
    protected double payRate;
    
    public Employee(String name, double payRate) {
        this.name = name;
        this.payRate = payRate;
    }

    public String getName() {
        return name;
    }
    
    public double getPayRate() {
        return payRate;
    }
    
    /**
     * Compute 1 week's base pay for this Employee.
     */
    abstract public double getBasePay();
}
