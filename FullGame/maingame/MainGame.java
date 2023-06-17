package maingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import pixelmap.CompleteMap;
import pixelmap.DisplayPixelMap;
import pixelmap.PixelMap;
import static pixelmap.PixelMap.enlargePixelMap;
import static pixelmap.PixelMap.shortestPathValuesConvert;
import pixelmap.ShortestPath;

/**
 *
 * @author 123456
 */
public class MainGame {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Journey-Simulation");
     
        //Display Rules
        System.out.println("MAP JOURNEY SIMULATION RULES:");
        System.out.println("1. You can only move adjacently (left, right, top, down), strictly not diagonally.\n" +
                           "2. You are not allowed to walk past the obstacles(trees)\n" +
                           "3. You are not allowed to walk past the visited ‘pixel’ again in a single journey.\n");
        //Get full map
        PixelMap pixelMap = new PixelMap();
        CompleteMap fullMap = new CompleteMap();
        
        int[][] mapFull_pixelConverted = fullMap.getFullMap();
        int pathCountFull = pixelMap.countPaths4Stations(mapFull_pixelConverted);
        System.out.println("Path count for full map that passes through exactly 4 stations: " + pathCountFull);
        
        //Find shortest paths for Full Map
        List<List<String>> shortestPathsMapFull = ShortestPath.FindShortestPaths(mapFull_pixelConverted);
        
        //Print steps for shortest paths for Full Map
        System.out.println("\nList of shortest paths steps for Full Map that passes through exactly 4 stations:");
        int i = 1;
        for (List<String> path : shortestPathsMapFull) {
            System.out.println(i + ". " + path);
            i++;
        }
        
        //Create a new map that features the shortest path
        int[][] mapShort_pixelConverted = fullMap.getFullMap();
        
        //Convert the values for the shortest paths pixels into 4
        shortestPathValuesConvert(mapShort_pixelConverted);

        BufferedImage shortestPixelMap = new BufferedImage(20, 40, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < 40; y++) {
            for (int x = 0; x < 20; x++) {
                int rgbValue;
                if(mapShort_pixelConverted[y][x]==0)
                    rgbValue = 0; // Assuming grayscale values
                else if(mapShort_pixelConverted[y][x]==1)
                    rgbValue = 4210752;//Decimal form for RGB(64,64,64)
                else if(mapShort_pixelConverted[y][x]==2)
                    rgbValue = 8421504;//Decimal form for RGB(128,128,128)
                else if(mapShort_pixelConverted[y][x]==3)
                    rgbValue = 12632256;//Decimal form for RGB(192,192,192)
                else
                    rgbValue = 51200;//Decimal form for RGB(0,200,0)
                shortestPixelMap.setRGB(x, y, rgbValue);
            }
        }
        
        File outputFileShortest = new File("pixel_map_shortest.png");
        try {
            ImageIO.write(shortestPixelMap, "png", outputFileShortest);
        } catch (IOException e) {
            System.out.println("Failed to save pixel map: " + e.getMessage());
        }
        
        //Display the shortest path pixel map using JFrame
        try {
            File imageFile = new File("pixel_map_shortest.png");
            BufferedImage pixelMapImage = ImageIO.read(imageFile);
            
            //Enlarge the display
            DisplayPixelMap panel = new DisplayPixelMap(enlargePixelMap(pixelMapImage,16));
        
            int enlargedW = enlargePixelMap(pixelMapImage,17).getWidth();
            int enlargedH = enlargePixelMap(pixelMapImage,17).getHeight();

            //Display the map
            JFrame frame = new JFrame("Pixel Map Shortest Path Guide");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel);
            frame.setSize(enlargedW, enlargedH);
            frame.setVisible(true);
            
        } catch (IOException e) {
            System.out.println("Failed to convert image to BufferedImage: " + e.getMessage());
        }
        
        System.out.println("Selected Shortest Path: 1");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.startGameThread();
        
        
    }
    
}

