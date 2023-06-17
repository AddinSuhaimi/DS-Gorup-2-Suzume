package entity;

/**
 *
 * @author 123456
 */
import maingame.GamePanel;
import maingame.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import pixelmap.CompleteMap;
import tictactoe.*;


public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;
    Stack<Integer[]> visitedStations;
    int[][] map;
    int[] prevPixel = new int[2];
  
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        CompleteMap fullMap = new CompleteMap();
        map = fullMap.getFullMap();
        visitedStations = new Stack<>();
        this.gp=gp;
        this.keyH=keyH;
        
        //solidArea = new Rectangle(0,0,16,16);
          
        setDefaultValues();
        getPlayerImage();
        
        
    }
    public void setDefaultValues(){
        
        x = 0;
        y = 0;
        speed = 16;
       
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
                
        if(keyH.upPressed==true && (y/16)!=0){
            
            int[] nextPixel = {x,y-16};
            if((map[(y-16)/16][x/16]==0 || map[(y-16)/16][x/16]==2) && !(Arrays.equals(prevPixel, nextPixel))) {
                prevPixel[0] = x;
                prevPixel[1] = y;
                y -= speed;
                
                if(map[y/16][x/16]==2) {
                     gp.playSE(1);
                    initiateRandomTTT();
                    keyH.upPressed=false;
                    Integer[] coordinates = {x,y};
                    visitedStations.push(coordinates);
                }

            }
        }
        else if(keyH.downPressed == true && (y/16)!=39){
            
            int[] nextPixel = {x,y+16};
            if((map[(y+16)/16][x/16]==0 || map[(y+16)/16][x/16]==2) && !(Arrays.equals(prevPixel, nextPixel))) {
                prevPixel[0] = x;
                prevPixel[1] = y;
                y += speed;

                if(map[y/16][x/16]==2) {
                     gp.playSE(1);
                    initiateRandomTTT();
                    keyH.downPressed=false;
                    Integer[] coordinates = {x,y};
                    visitedStations.push(coordinates);
                }

            }
        }
        else if(keyH.leftPressed == true && (x/16)!=0){
            
            int[] nextPixel = {x-16,y};
            if((map[y/16][(x-16)/16]==0 || map[y/16][(x-16)/16]==2) && !(Arrays.equals(prevPixel, nextPixel))) {
                prevPixel[0] = x;
                prevPixel[1] = y;
                x -= speed;

                if(map[y/16][x/16]==2) {
                     gp.playSE(1);
                    initiateRandomTTT();
                    keyH.leftPressed=false;
                    Integer[] coordinates = {x,y};
                    visitedStations.push(coordinates);
                }
            }
        }
        else if(keyH.rightPressed == true && (x/16)!=19){
            
            int[] nextPixel = {x+16,y};
            if((map[y/16][(x+16)/16]==0 || map[y/16][(x+16)/16]==2 || map[y/16][(x+16)/16]==3) && !(Arrays.equals(prevPixel, nextPixel))) {
                prevPixel[0] = x;
                prevPixel[1] = y;
                x += speed;

                if(map[y/16][x/16]==2) {
                    gp.playSE(1);
                    initiateRandomTTT();
                    keyH.rightPressed=false;
                    Integer[] coordinates = {x,y};
                    visitedStations.push(coordinates);
                }

            }
        }

        if(winner!=null) {
            if(winner.equals("AI") && visitedStations.size()==1) {
                gp.playSE(3);
                System.out.println("You lost at the first station! Your journey ends");
                System.exit(0);
            } else if(winner.equals("AI") && visitedStations.size()>1) {
                gp.playSE(3);
                System.out.println("You lost! Go back to the previous station!");
                winner = null;
                visitedStations.pop();
                x = visitedStations.peek()[0];
                y = visitedStations.peek()[1];
            } else if(winner.equals("Human")) {
                gp.playSE(2);
                System.out.println("You may continue!");
                winner = null;
            }
        }

        if(x==(19*16)&&y==(39*16)) {
            gp.playSE(2);
            System.out.println("Congratulations! You have won the game!");
            end = true;
        }
    }
    
    public void initiateRandomTTT() {
        Random r = new Random();
        int choice = r.nextInt(3);

        if(choice == 0) {
            SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run(){
                    new TTT5x5Suzume();
                }
            });
        }
        if(choice == 1) {
            SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run(){
                    new TTTReverseSuzume();
                }
            });
        }
        
        if(choice == 2) {
            SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run(){
                    new WildTTTSuzume();
                }
            });
        }

    }
         
         
    
    public void draw(Graphics2D g2){
      
        BufferedImage image =img;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
       
    }
    
}
