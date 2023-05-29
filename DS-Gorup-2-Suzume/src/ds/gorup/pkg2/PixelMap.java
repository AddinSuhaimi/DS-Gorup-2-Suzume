/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ds.gorup.pkg2.suzume;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author user1
 */
public class DSGorup2Suzume 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // Reading the image from file
        BufferedImage map1_img = null;
        BufferedImage map2_img = null;
        BufferedImage map3_img = null;
        BufferedImage map4_img = null;
        
        try 
        {
            map1_img = ImageIO.read(new File("maps/image 1.png"));
            map2_img = ImageIO.read(new File("maps/image 2.png"));
            map3_img = ImageIO.read(new File("maps/image 3.png"));
            map4_img = ImageIO.read(new File("maps/image 4.png"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("Map Files not Found!");
            return;
        }
        
        // Image dimensions
        int width = 10;
        int height = 20;
        
        // Get the pixel values array of the image
        int[][] map1_pixelValues = new int[height][width];
        int[][] map2_pixelValues = new int[height][width];
        int[][] map3_pixelValues = new int[height][width];
        int[][] map4_pixelValues = new int[height][width];
        
        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < width; x++) 
            {
                int rgb = map1_img.getRGB(x, y);
                int gray = (rgb >> 16) & 0xFF; // Extract the red channel value
                map1_pixelValues[y][x] = gray;
                
                rgb = map2_img.getRGB(x, y);
                gray = (rgb >> 16) & 0xFF; // Extract the red channel value
                map2_pixelValues[y][x] = gray;
                
                rgb = map3_img.getRGB(x, y);
                gray = (rgb >> 16) & 0xFF; // Extract the red channel value
                map3_pixelValues[y][x] = gray;
                
                rgb = map4_img.getRGB(x, y);
                gray = (rgb >> 16) & 0xFF; // Extract the red channel value
                map4_pixelValues[y][x] = gray;
            }
        }
//        
//        for (int y = 0; y < height; y++) 
//        {
//            for (int x = 0; x < width; x++) 
//            {
//                System.out.print(map1_pixelValues[y][x] + " ");
//            }
//            System.out.println();
//        }
        
        // Converting pixel values to the range of 0-3
        int[][] map1_pixelConverted = new int[height][width];
        int[][] map2_pixelConverted = new int[height][width];
        int[][] map3_pixelConverted = new int[height][width];
        int[][] map4_pixelConverted = new int[height][width];
        
        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < width; x++) 
            {
                map1_pixelConverted[y][x] = (int) Math.round(map1_pixelValues[y][x] / 64);
                map2_pixelConverted[y][x] = (int) Math.round(map2_pixelValues[y][x] / 64);
                map3_pixelConverted[y][x] = (int) Math.round(map3_pixelValues[y][x] / 64);
                map4_pixelConverted[y][x] = (int) Math.round(map4_pixelValues[y][x] / 64);
            }
        }
        
        // Printing the converted pixel values
        System.out.print("Map 1:\n");
        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < width; x++) 
            {
                System.out.print(map1_pixelConverted[y][x] + " ");
            }
            System.out.println();
        }
        
        System.out.print("\nMap 2:\n");
        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < width; x++) 
            {
                System.out.print(map2_pixelConverted[y][x] + " ");
            }
            System.out.println();
            
        }
        
        System.out.print("\nMap 3:\n");
        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < width; x++) 
            {
                System.out.print(map3_pixelConverted[y][x] + " ");
            }
            System.out.println();
        }
        
        System.out.print("\nMap 4:\n");
        for (int y = 0; y < height; y++) 
        {
            for (int x = 0; x < width; x++) 
            {
                System.out.print(map4_pixelConverted[y][x] + " ");
            }
            System.out.println();
        }
    }
    
}
