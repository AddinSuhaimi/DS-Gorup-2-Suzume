/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import Main.GamePanel;
import PixelMap.CompleteMap;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import PixelMap.CompleteMap;
import java.awt.image.BufferedImage;
import java.util.Arrays;
/**
 *
 * @author 123456
 */
public class TileManager{
    GamePanel gp;
    Tile[] tile;
    int[][] map;
   public TileManager(GamePanel gp){
       CompleteMap fullMap = new CompleteMap();
       this.gp=gp;
       tile = new Tile[4];
        map = fullMap.getFullMap();
       getTileImage();
   }
   
   public void getTileImage(){
       
       try{
           tile[0]= new Tile();
           tile[0].tileimg = ImageIO.read(new File("grass.png"));
           
           tile[1]= new Tile();
           tile[1].tileimg = ImageIO.read(new File("tree.png"));
           tile[1].collision=true;
           
           tile[2]= new Tile();
           tile[2].tileimg = ImageIO.read(new File("hut.png"));
           
           tile[3]= new Tile();
           tile[3].tileimg = ImageIO.read(new File("stair.png"));
           
       }
       catch(IOException e){
           e.printStackTrace();
       }
   }
   
   
   public void draw(Graphics2D g2){
       
    int tileSize = gp.tileSize;

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int tileType = map[row][col];
               
                int x = col * tileSize;
                int y = row * tileSize;
                
                BufferedImage tileImg = tile[tileType].tileimg;
                g2.drawImage(tile[tileType].tileimg, x, y, tileSize, tileSize, null);
                
            
            }
        }
   }
}
