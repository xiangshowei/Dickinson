package comp132.examples.gui;

import java.awt.*;
import javax.swing.*;

/**
 * Demonstration of the use of the BoxLayout layout manager.  The GUI created
 * demonstrates the nesting of boxes and the use of struts and glue.
 * 
 * The structure of the code also highlights a recommended technique for building
 * GUI's using the BoxLayout.  The constructor creates the main box for the GUI, 
 * either horizontal or vertical.  Then each nested box is created and returned by
 * its own private method.
 *
 * @author Grant Braught
 * @author Dickinson College
 * @version Sep 24, 2009
 */
public class BoxLayoutDemo extends JFrame {

    /**
     * Construct a new BoxLayoutDemo.
     */
    public BoxLayoutDemo() {
        super("BoxLayout Demonstration");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.X_AXIS));
        this.add(mainPanel);  
        
        mainPanel.add(getVerticalBox());
        mainPanel.add(getHorizontalBox());

        this.pack();
    }
    
    /**
     * Create and return the vertical box that appears in the left portion
     * of the window.
     * 
     * @return the vertical box.
     */
    private JPanel getVerticalBox() {
        /*
         * Create a JPanel and set its layout manager to be a vertical box.
         * Also color the panel blue so that we can better see it in the demo.
         */
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(Color.blue);

        /*
         * Add the components, struts and glue as desired.
         */
        JButton up = new JButton("... up");
        panel.add(up);

        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        JButton hole = new JButton("... hole");
        panel.add(hole);
        
        panel.add(Box.createVerticalGlue());
        
        JButton easy = new JButton("Easy ...");
        panel.add(easy);
        
        return panel;
    }
    
    /**
     * Create and return the horizontal box that appears in the right portion
     * of the window.
     */
    private JPanel getHorizontalBox() {
        /*
         * Create a JPanel and set its layout manager to be a vertical box.
         * Also color the panel red so that we can better see it in the demo.
         */
        JPanel panel = new JPanel();
        panel.setBackground(Color.red);
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));

        /*
         * Add the components, struts and glue as desired.
         */
        panel.add(Box.createHorizontalGlue());
        
        JButton cute = new JButton("Cute as a ...");
        panel.add(cute); 
        JButton shirt = new JButton("... down shirt");
        panel.add(shirt);
        
        panel.add(Box.createRigidArea(new Dimension(50, 0)));
        
        JButton right = new JButton("Right on the ...");
        panel.add(right);
        
        panel.add(Box.createHorizontalGlue());
        
        return panel;
    }
    
    /**
     * Create a new BoxLayoutDemo and display it on the screen.
     * 
     * @param args none.
     */
    public static void main(String[] args) {
        BoxLayoutDemo bld = new BoxLayoutDemo();
        bld.setVisible(true);
    }
}
