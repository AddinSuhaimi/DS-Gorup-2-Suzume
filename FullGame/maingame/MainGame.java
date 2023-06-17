package maingame;

import java.util.List;
import javax.swing.JFrame;
import pixelmap.CompleteMap;
import pixelmap.PixelMap;
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
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.startGameThread();
        
        
    }
}

