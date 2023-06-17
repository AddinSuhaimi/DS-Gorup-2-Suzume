package maingame;

import maingame.KeyHandler;
import pixelmap.CompleteMap;
import entity.Player;
import entity.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import static entity.Entity.end;


/**
 *
 * @author 123456
 */
public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS 
    final int originalTileSize=16; //16x16 tile
    final int scale = 1;
    
    public final int tileSize = originalTileSize * scale; 
    public final int maxScreenCol = 20;  //Horizontal
    public final int maxScreenRow = 40;  //vertical
    public final int screenWidth = tileSize * maxScreenCol;   
    public final int screenHeight = tileSize * maxScreenRow;   
    
    
    
    //FPS
    int FPS = 8;
    
   public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);
    
    
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }
    
    public void startGameThread(){
         gameThread = new Thread(this);
         gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;   
     
        while(gameThread != null){
            
            //Update
            update();
            
            //Draw
            repaint();
            
            try {
            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime/1000000;
            
            if(remainingTime <0){
                remainingTime=0;
            }
            
            Thread.sleep((long) remainingTime);
            
            nextDrawTime +=drawInterval;
            
            } catch (InterruptedException ex) {
              ex.printStackTrace();
            }
            
            if(end){
                gameThread=null;
            }
        }
        
    }
    public void update(){
       player.update();
        
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
    
   
}
