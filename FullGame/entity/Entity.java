package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author 123456
 */
public class Entity extends Rectangle{
    public int x, y;
    public int speed;
    public static String winner;
    public static boolean end = false;
    
    public BufferedImage img;
   
    public Rectangle solidArea;
    public boolean collisionOn = false;
    
}
