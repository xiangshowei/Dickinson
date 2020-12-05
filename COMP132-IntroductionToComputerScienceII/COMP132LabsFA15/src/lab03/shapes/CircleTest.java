package lab03.shapes;

import static org.junit.Assert.*;
import org.junit.*; 

import java.awt.*;

public class CircleTest {

    private Circle c1;
    
    @Before
    public void setUp() throws Exception {
        c1 = new Circle(10,20,5,Color.red);
    }

    @Test
    public void testConstructor() {
        assertEquals("wrong x", 10, c1.getX());
        assertEquals("wrong y", 20, c1.getY());
        assertEquals("wrong radius", 5, c1.getRadius());
        assertEquals("wrong color", Color.red, c1.getColor());
        assertTrue("should be visible", c1.isVisible());
    }
    
    @Test
    public void testSetColor() {
        c1.setColor(Color.blue);
        assertEquals("wrong color", Color.blue, c1.getColor());     
    }
    
    @Test
    public void testSetVisible() {
        c1.setVisible(false);
        assertFalse("should be invisible", c1.isVisible());
        
        c1.setVisible(true);
        assertTrue("should be visible", c1.isVisible());
    }
    
    @Test 
    public void testSetRadius() {
        c1.setRadius(10);
        assertEquals("wrong radius", 10, c1.getRadius());
        
        c1.setRadius(-20);
        assertEquals("wrong radius", 20, c1.getRadius());
    }
    
    @Test
    public void testMove() {
        c1.move(20,30);
        assertEquals("wrong x", 20, c1.getX());
        assertEquals("wrong y", 30, c1.getY());
    }
    
    @Test
    public void testTranslate() {
        c1.translate(5,10);
        assertEquals("wrong x", 15, c1.getX());
        assertEquals("wrong y", 30, c1.getY());
    }
    
    @Test
    public void testScale() {
        c1.scale(2.0);
        assertEquals("wrong radius", 10, c1.getRadius());
        
        c1.scale(0.5);
        assertEquals("wrong radius", 5, c1.getRadius());
        
        c1.scale(-2.0);
        assertEquals("wrong radius", 5, c1.getRadius());
    }
}
