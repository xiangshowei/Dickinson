package comp132.examples.gui.snow;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;

import javax.imageio.*;
import javax.swing.*;

/**
 * Custom JPanel that displays a picture and covers a specified percentage of it
 * with white (very poorly) simulating the accumulation of snow.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jul 16, 2009
 */
public class SnowStormPanel extends JPanel {

    // Required by all swing components.
    private static final long serialVersionUID = 1L;

    private BufferedImage skiImg;
    private int snowDepth;
    
    /**
     * Construct a new SnowStormPanel using the ski.jpg image
     * included in this package.
     */
    public SnowStormPanel() {
        URL skiFile = SnowStormPanel.class.getResource("ski.jpg");
        skiImg = null;
        try {
            skiImg = ImageIO.read(skiFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        snowDepth = 0;
        
        this.setMinimumSize(new Dimension(skiImg.getWidth(), skiImg.getHeight()));
        this.setMaximumSize(new Dimension(skiImg.getWidth(), skiImg.getHeight()));
        this.setPreferredSize(new Dimension(skiImg.getWidth(), skiImg.getHeight()));
    }
    
    /**
     * Paint the image and the appropriate amount of snow on the
     * Graphics context associated with this SnowStormPanel.
     * 
     * @param g the Graphics context on which to paint.
     */
    public void paintComponent(Graphics g) {
        g.drawImage(skiImg, 0, 0, null);
        
        g.setColor(Color.white);
        int depthInPixels = (int)(skiImg.getHeight()*(snowDepth/100.0));
        g.fillRect(0, skiImg.getHeight() - depthInPixels, skiImg.getWidth(), skiImg.getHeight());
        
        g.setColor(Color.cyan);
        g.fillRoundRect(380, 40, 125, 50, 15, 15);
        g.fillPolygon(new int[] {310, 380, 380}, new int[] {75, 45, 55}, 3);
        
        g.setColor(Color.red);
        g.drawString(getSaying(), 390, 70);
    }
    
    private String getSaying() {
        if (snowDepth == 0) {
            return "Please snow!";
        }
        else if (snowDepth <= 20) {
            return "Keep it coming!";
        }
        else if (snowDepth <= 40) {
            return "Sweet turns!";
        }
        else if (snowDepth <= 60) {
            return "Steep 'n deep!";
        }
        else if (snowDepth <= 75) {
            return "Yikes! ";
        }
        else if (snowDepth <= 90) {
            return "Help!";
        }
        else {
            return "Mommy?!?!";
        }
    }
    
    /**
     * Set the depth of the snow in inches. The height of the image is assumed to be
     * six feet.
     * 
     * @param inches the number of inches of snow to display.
     */
    public void setSnowDepth(int inches) {
        
        int totalHeight = 5 * 12 + 9; // Prof. Braught is 5'9"
        int percent = 100 * inches / totalHeight;
        
        if (percent < 0) {
            snowDepth = 0;
        }
        else if (percent > 100) {
            snowDepth = 100;
        }
        else {
            snowDepth = percent;
        }
        
        repaint();
    }
    
    /**
     * Small test method that displays the SnowStormPanel and slowly
     * increases the depth of the snow.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        SnowStormPanel ssp = new SnowStormPanel();
        f.getContentPane().add(ssp);
        
        f.pack();
        f.setVisible(true);
        
        for (int i=0; i<=10; i++) {
            ssp.setSnowDepth(i*10);
            try {
                Thread.sleep(500);
            }
            catch(InterruptedException e) {
                // nothing to do here - just go about our business.
            }
        }
    }
}
