package comp132.examples.inheritance.abstractClass;

import java.util.*;

/**
 * A list of Employee objects. Because SalariedEmployee and HourlyEmployee are
 * both sub-classes of Employee, an Employee reference can refer to objects of
 * either type. Polymorphism ensures that when we invoke a method defined in the
 * Employee class (including abstract methods) the version that is executed is
 * determined by the object on which it is invoked.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 14, 2009
 */
public class EmployeeList {

    private ArrayList<Employee> empList;
    
    public EmployeeList() {
        empList = new ArrayList<Employee>();
    }
    
    public void addEmployee(Employee emp) {
        empList.add(emp);
    }
    
    public void displayBasePays() {
        for (Employee e : empList) {
            System.out.println(e.getName() + " $" + e.getBasePay());
        }
    }
      
    /**
     * Create a list of several employees and display their base pay.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        EmployeeList workers = new EmployeeList();
        workers.addEmployee(new SalariedEmployee("Joe", 57000.00));
        workers.addEmployee(new HourlyEmployee("Sam", 12.53));
        workers.addEmployee(new SalariedEmployee("Jane", 104376.00));
        workers.addEmployee(new HourlyEmployee("Kim", 10.98));
        
        workers.displayBasePays();
        
    }
}
