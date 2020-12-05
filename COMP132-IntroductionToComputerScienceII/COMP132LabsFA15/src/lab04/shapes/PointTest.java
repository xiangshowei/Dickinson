package lab04.shapes;


import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.*;

public class PointTest {

    private Point p1;
    
    @Before
    public void setUp() throws Exception {
        p1 = new Point(10,20,Color.red);
    }

    @Test
    public void testConstructor() {
        assertEquals("wrong x", 10, p1.getX());
        assertEquals("wrong y", 20, p1.getY());
        assertEquals("wrong color", Color.red, p1.getColor());
        assertTrue("should be visible", p1.isVisible());
    }
    
    @Test
    public void testSetColor() {
        p1.setColor(Color.blue);
        assertEquals("wrong color", Color.blue, p1.getColor());     
    }
    
    @Test
    public void testSetVisible() {
        p1.setVisible(false);
        assertFalse("should be invisible", p1.isVisible());
        
        p1.setVisible(true);
        assertTrue("should be visible", p1.isVisible());
    }
    
    @Test
    public void testMove() {
        p1.move(20,30);
        assertEquals("wrong x", 20, p1.getX());
        assertEquals("wrong y", 30, p1.getY());
    }
    
    @Test
    public void testTranslate() {
        p1.translate(5,10);
        assertEquals("wrong x", 15, p1.getX());
        assertEquals("wrong y", 30, p1.getY());
    }
}
