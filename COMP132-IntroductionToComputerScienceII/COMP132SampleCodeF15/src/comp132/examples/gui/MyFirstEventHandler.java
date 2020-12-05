package comp132.examples.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Demonstration of event handling in Java. A JButton is created and has an
 * ActionListener added to it.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 24, 2009
 */
public class MyFirstEventHandler extends JFrame {

    public MyFirstEventHandler() {
        super("My First Event Handler");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel);

        /*
         * Create the JButton and add it to the panel.
         */
        JButton b1 = new JButton("Click Me!");
        mainPanel.add(b1);

        /*
         * Register a new instance of MyButtonListener as an ActionListener on
         * the button. When the button is clicked the actionPerformed method of
         * the MyButtonListener object will be invoked.
         */
        MyButtonListener mbl = new MyButtonListener();
        b1.addActionListener(mbl);

        this.pack();
    }

    /**
     * Create an instance of MyFirstEventHandler and display it on the screen.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        MyFirstEventHandler mfal = new MyFirstEventHandler();
        mfal.setVisible(true);
    }

    /*
     * This inner class implements the ActionListener interface. When the
     * component generates an event the actionPerformed method of each of its
     * registered ActionListeners is invoked. The ActionEvent parameter contains
     * information about the event.
     */
    private class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            /*
             * Whatever code appears in the actionPerformed method is executed
             * each time an event is generated.
             */
            System.out.println("Hey, quit doing that!");
        }
    }
}
