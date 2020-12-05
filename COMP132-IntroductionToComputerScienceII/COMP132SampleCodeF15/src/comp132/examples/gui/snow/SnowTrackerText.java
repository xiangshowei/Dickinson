package comp132.examples.gui.snow;

import java.util.*;

/**
 * A Text based interface to the SnowTracker model.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 28, 2009
 */
public class SnowTrackerText implements Observer {

    private SnowTracker myModel;
    
    /**
     * Construct a new SnowTrackerText object as the interface to a SnowTracker
     * object.
     */
    public SnowTrackerText() {
        myModel = new SnowTracker();
        myModel.addObserver(this);
    }
    
    /**
     * Control the SnowTracker via text input from the user.  When the user 
     * enters S or H the appropriate method in the SnowTracker is invoked
     * to increase or decrease the amount of snow.
     */
    public void control() {
        Random rnd = new Random();
        Scanner scr = new Scanner(System.in);
        
        System.out.println("Make it (S)now, have a (H)eatwave or (Q)uit.");
        
        String input = "";
        while(!input.equals("Q")) {
            input = scr.next().toUpperCase();
            if (input.equals("S")) {
                myModel.setDepth(myModel.getDepth() + rnd.nextInt(12) + 1);
            }
            else if (input.equals("H")) {
                myModel.setDepth(myModel.getDepth() - rnd.nextInt(12) - 1);
            }
        }
        
        System.out.println ("Bye bye.");
    }
    
    /**
     * This method is invoked when the SnowTracker that we are observing
     * notifies its observers.
     */
    public void update(Observable o, Object arg) {
        int inches = myModel.getDepth();
        System.out.println("Snow depth: " + inches + " inches.");
    }
    
    /**
     * Run the text based interface to the SnowTracker object.
     * 
     * @param args none.
     */
    public static void main(String[] args) {
        SnowTrackerText stt = new SnowTrackerText();
        stt.control();
    }
}
