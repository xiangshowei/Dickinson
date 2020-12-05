package comp132.examples.interfaces;

/**
 * The Duck class implements both the MakesSound and Swims
 * interfaces.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 20, 2009
 */
public class Duck implements MakesSound, Swims {

    private String species;
    
    public Duck(String species) {
        this.species = species;
    }
    
    public String getSpecies() {
        return species;
    }
    
    // === Implementation of the MakesSound interface ===
    
    public void makeSound() {
        System.out.println("Quack, Quack");
    }
    
    public int howLoud() {
        return 4;
    }

    // === Implementation of the Swims interface ===
    
    public void swim() {
        System.out.println("Paddling my feet, Paddling my feet");
    }
}
