/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 123456
 */
import Main.GamePanel;
import Main.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
  
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        this.gp=gp;
        this.keyH=keyH;
        
        solidArea = new Rectangle(0,0,16,16);
          
        setDefaultValues();
        getPlayerImage();
        
        
    }
    public void setDefaultValues(){
        
        x = 0;
        y=0;
        speed = 2;
       
    }
    public void getPlayerImage(){
        try{
           img = ImageIO.read(new File("Suzume.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public void update(){   
        
      
        
         if(keyH.upPressed==true){
            y -= speed;
        }
        else if(keyH.downPressed == true){
            y += speed;
        }
        else if(keyH.leftPressed == true){
            x -= speed;
        }
        else if(keyH.rightPressed == true){
            x += speed;
        }
       
        
    }  
         
         
    
    public void draw(Graphics2D g2){
      
        BufferedImage image =img;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
       
    }
    
    
}
