package comp132.examples.interfaces;

/**
 * The Car class implements the MakesSound interface but not the Swims
 * interface.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 20, 2009
 */
public class Car implements MakesSound {

    private String make;
    private int volume;
    
    public Car(String make, int volume) {
        this.make = make;
        this.volume = volume;
    }

    public String getMake() {
        return make;
    }

    // === Implementation of the MakesSound interface ===

    public void makeSound() {
        System.out.println("Vroom, Vroom");
    }

    public int howLoud() {
        return volume;
    }
}
