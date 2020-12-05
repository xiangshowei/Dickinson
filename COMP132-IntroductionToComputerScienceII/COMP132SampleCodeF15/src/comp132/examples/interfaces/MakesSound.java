package comp132.examples.interfaces;

/**
 * An interface implemented by objects that can make sounds.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 20, 2009
 */
public interface MakesSound {

    /**
     * Print out the sound that is made by the object.
     */
    void makeSound();

    /**
     * Get a number from 1 to 10 indicating how loud the sound is that is made
     * by the object.
     * 
     * @return the volume of this object's sound.
     */
    int howLoud();
}
