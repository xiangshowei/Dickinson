package comp132.examples.gui.snow;

import java.util.*;

/**
 * A very simple model for illustrating the Model-View-Controller pattern. This
 * model has only one value, the depth of the snow in inches.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 16, 2009
 */
public class SnowTracker extends Observable {
    private int snowDepth;

    /**
     * Construct a new SnowTracker with an initial depth of 0".
     */
    public SnowTracker() {
        snowDepth = 0;
    }

    /**
     * Get the depth of the snow in inches.
     * 
     * @return the snow depth.
     */
    public int getDepth() {
        return snowDepth;
    }
    
    /**
     * Set the depth of the snow in inches. If the new depth is less than 0 the
     * depth is set to 0.
     * 
     * @param newDepth the snow depth.
     */
    public void setDepth(int newDepth) {
        if (newDepth >= 0) {
            snowDepth = newDepth;
        }
        else {
            snowDepth = 0;
        }

        /*
         * Indicate that the model has changed and notify all
         * of the observers that a change has occurred.
         */
        setChanged();
        notifyObservers();
    }
}
