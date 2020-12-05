package comp132.examples.interfaces;

import java.awt.event.*;
import javax.swing.*;

/**
 * Demonstration of the Timer class that is able to repeatedly invoke the
 * actionPerformed method on any object that implements the ActionListener
 * interface.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Aug 20, 2009
 */
public class MyTimerEvent implements ActionListener {

    /*
     * The actionPerformed method is invoked each time the Timer goes off.
     */
    public void actionPerformed(ActionEvent e) {
        /*
         *  This just prints a message, but in practice you could do anything 
         *  that you want here.
         */
        System.out.println("Here we go again...");
    }

    /**
     * Use a Timer to print out a message once every second.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        // Create and start the timer.
        Timer t = new Timer(1000, new MyTimerEvent());
        t.start();

        // This is just a way to wait until the user has seen enough.
        JOptionPane.showMessageDialog(null, "Click me to quit");

        // Stop the timer.
        t.stop();
    }
}
