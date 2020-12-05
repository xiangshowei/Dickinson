package comp132.examples.gui.snow;

import java.awt.*;
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
public class SnowTrackerGUI extends JFrame implements Observer {

    private SnowTracker myModel;

    private SnowStormPanel snowPanel;
    private JLabel depthLabel;
    private JButton heatWaveButton;
    private JButton snowStormButton;

    /**
     * Construct a new SnowTrackerGUI that is a view and a controller
     * for a SnowTracker object.
     */
    public SnowTrackerGUI() {
        super("Snow Tracker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myModel = new SnowTracker();
        myModel.addObserver(this);

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
        
        snowPanel = new SnowStormPanel();
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

        depthLabel = new JLabel("Snow Depth: 0");
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
        
        heatWaveButton = new JButton("Heat Wave");
        buttonPanel.add(heatWaveButton);
        HeatButtonListener hbl = new HeatButtonListener();
        heatWaveButton.addActionListener(hbl);
        
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        
        snowStormButton = new JButton("Snow Storm");
        buttonPanel.add(snowStormButton);
        SnowButtonListener sbl = new SnowButtonListener();
        snowStormButton.addActionListener(sbl);

        buttonPanel.add(Box.createHorizontalGlue());
        
        return buttonPanel;
    }

    /**
     * The Model has notified the view of a change. This method updates the view
     * to match the current state of the model.
     * 
     * @param o the object that made the notification (i.e. the SnowTracker)
     * @param arg unused in this example. In general it could be a signal from
     *            the model indicating what has changed.
     */
    public void update(Observable o, Object arg) {
        int inches = myModel.getDepth();

        snowPanel.setSnowDepth(inches);

        depthLabel.setText("Snow Depth: " + inches);
    }

    /**
     * Main method for the SnowTracker application with a GUI.
     * 
     * @param args none.
     */
    public static void main(String[] args) {
        SnowTrackerGUI stGUI = new SnowTrackerGUI();
        stGUI.setVisible(true);
    }

    /*
     * This listener listens for clicks on the snow storm button
     * and makes the appropriate call to update the model.
     */
    private class SnowButtonListener implements ActionListener {
        
        private Random rnd;
        
        public SnowButtonListener() {
            rnd = new Random();
        }
        
        public void actionPerformed(ActionEvent e) {
        	int curDepth = myModel.getDepth();
        	int newDepth = curDepth + rnd.nextInt(12) + 1;
            myModel.setDepth(newDepth);
        }
    }
    
    /*
     * This listener listens for clicks on the heat wave button
     * and makes the appropriate call to update the model.
     */
    private class HeatButtonListener implements ActionListener {
      
        private Random rnd;
        
        public HeatButtonListener() {
            rnd = new Random();
        }
        
        public void actionPerformed(ActionEvent e) {
            myModel.setDepth(myModel.getDepth() - rnd.nextInt(12) - 1);
        }
    }
}
