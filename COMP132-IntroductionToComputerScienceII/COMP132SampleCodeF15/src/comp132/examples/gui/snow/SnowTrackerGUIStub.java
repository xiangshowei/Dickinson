package comp132.examples.gui.snow;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * A View and Controller for illustrating the Model-View-Controller pattern.
 * This GUI is a view and controller for the SnowTracker model.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 16, 2009
 */
public class SnowTrackerGUIStub extends JFrame {

    /**
     * Construct a new SnowTrackerGUI that is the view and controller for the
     * specified model.
     * 
     * @param myModel the SnowTracker to be viewed and controlled by this GUI.
     */
    public SnowTrackerGUIStub() {
        super("Snow Tracker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        this.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        mainPanel.add(getPhoto());
        mainPanel.add(getControls());

        this.pack();
    }

    /*
     * Build a JPanel holding the SnowStormPanel component.  Vertical glue
     * is used to ensure that the SnowStormPanel remains centered vertically.
     */
    private JPanel getPhoto() {
        JPanel photoPanel = new JPanel();
        photoPanel.setLayout(new BoxLayout(photoPanel, BoxLayout.Y_AXIS));
        
        photoPanel.add(Box.createVerticalGlue());
        
        SnowStormPanel snowPanel = new SnowStormPanel();
        photoPanel.add(snowPanel);

        photoPanel.add(Box.createVerticalGlue());
        
        return photoPanel;
    }
    
    /*
     * Build a JPanel holding all of the controls.  Vertical glue is used to ensure
     * that the controls remain centered vertically.
     */
    private JPanel getControls() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        controlPanel.add(Box.createVerticalGlue());

        JLabel depthLabel = new JLabel("Snow Depth: 0");
        controlPanel.add(depthLabel);

        controlPanel.add(Box.createRigidArea(new Dimension(0,20)));

        /*
         * Nest the JPanel holding the buttons here.
         */
        controlPanel.add(getButtons());

        controlPanel.add(Box.createVerticalGlue());

        return controlPanel;
    }
    
    /*
     * Create a JPanel holding the Heat Wave and Snow Storm buttons.  Horizontal
     * glue is used to keep the buttons separated.
     */
    private JPanel getButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        buttonPanel.add(Box.createHorizontalGlue());

        JButton heatWaveButton = new JButton("Heat Wave");
        buttonPanel.add(heatWaveButton);
        
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        
        JButton snowStormButton = new JButton("Snow Storm");
        buttonPanel.add(snowStormButton);
        
        buttonPanel.add(Box.createHorizontalGlue());
        
        return buttonPanel;
    }

    /**
     * Main method for the SnowTracker application with a GUI.
     * 
     * @param args none.
     */
    public static void main(String[] args) {
        SnowTrackerGUIStub stGUI = new SnowTrackerGUIStub();
        stGUI.setVisible(true);
    }
}
