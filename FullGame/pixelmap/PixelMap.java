package pixelmap;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import static pixelmap.AnswerDecryption.decrypt;

public class PixelMap {    
    private final int width = 10, height = 20;
    private int[][] Map1;
    private int[][] Map2;
    private int[][] Map3;
    private int[][] Map4;

    private final int[][] Directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // Reading the image from file
        BufferedImage map1_img = null;
        BufferedImage map2_img = null;
        BufferedImage map3_img = null;
        BufferedImage map4_img = null;

        try {
            map1_img = ImageIO.read(new File("maps/image 1.png"));
            map2_img = ImageIO.read(new File("maps/image 2.png"));
            map3_img = ImageIO.read(new File("maps/image 3.png"));
            map4_img = ImageIO.read(new File("maps/image 4.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Map Files not Found!");
            return;
        }
        

        // Image dimensions
        int width = map1_img.getWidth();
        int height = map1_img.getHeight();

        // Get the pixel values array of the image
        int[][] map1_pixelValues = new int[height][width];
        int[][] map2_pixelValues = new int[height][width];
        int[][] map3_pixelValues = new int[height][width];
        int[][] map4_pixelValues = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
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

        // Converting pixel values to the range of 0-3
        int[][] map1_pixelConverted = new int[height][width];
        int[][] map2_pixelConverted = new int[height][width];
        int[][] map3_pixelConverted = new int[height][width];
        int[][] map4_pixelConverted = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map1_pixelConverted[y][x] = (int) Math.round(map1_pixelValues[y][x] / 64);
                map2_pixelConverted[y][x] = (int) Math.round(map2_pixelValues[y][x] / 64);
                map3_pixelConverted[y][x] = (int) Math.round(map3_pixelValues[y][x] / 64);
                map4_pixelConverted[y][x] = (int) Math.round(map4_pixelValues[y][x] / 64);
            }
        }
        

        // Printing the converted pixel values
        System.out.println("Map 1:");
        printMap(map1_pixelConverted);

        System.out.println("\nMap 2:");
        printMap(map2_pixelConverted);

        System.out.println("\nMap 3:");
        printMap(map3_pixelConverted);

        System.out.println("\nMap 4:");
        printMap(map4_pixelConverted);
        System.out.println();
        
        // Calculate and print the path count for each map
        PixelMap pixelMap = new PixelMap();
        int pathCount1 = pixelMap.countPaths3Stations(map1_pixelConverted);
        int pathCount2 = pixelMap.countPaths3Stations(map2_pixelConverted);
        int pathCount3 = pixelMap.countPaths3Stations(map3_pixelConverted);
        int pathCount4 = pixelMap.countPaths3Stations(map4_pixelConverted);

        System.out.println("These are the number of possible paths for each map piece that passes through exactly 3 stations");
        System.out.println("Path count for Map 1: " + pathCount1);
        System.out.println("Path count for Map 2: " + pathCount2);
        System.out.println("Path count for Map 3: " + pathCount3);
        System.out.println("Path count for Map 4: " + pathCount4);
        System.out.println();
        
        // Calculate shortest paths for each map
        List<List<String>> shortestPathsMap1 = ShortestPath.FindShortestPaths(map1_pixelConverted);
        List<List<String>> shortestPathsMap2 = ShortestPath.FindShortestPaths(map2_pixelConverted);
        List<List<String>> shortestPathsMap3 = ShortestPath.FindShortestPaths(map3_pixelConverted);
        List<List<String>> shortestPathsMap4 = ShortestPath.FindShortestPaths(map4_pixelConverted);

        // Print shortest paths for Map 1
        System.out.println("Shortest paths for Map 1:");
        for (List<String> path : shortestPathsMap1) {
         System.out.println(path);
        }

        // Print shortest paths for Map 2
        System.out.println("\nShortest paths for Map 2:");
        for (List<String> path : shortestPathsMap2) {
            System.out.println(path);
        }

        // Print shortest paths for Map 3
        System.out.println("\nShortest paths for Map 3:");
        for (List<String> path : shortestPathsMap3) {
            System.out.println(path);
        }

        // Print shortest paths for Map 4
        System.out.println("\nShortest paths for Map 4:");
        for (List<String> path : shortestPathsMap4) {
            System.out.println(path);
        }
        
        //Answer Decryption
        System.out.println();
        System.out.println("Decryption for this weird number 17355: ");
        System.out.println(decrypt(17355, 7));
        System.out.println("This should be the number of paths for the full map passing through exactly 4 stations");
        
        //Form a full map out of the 4 pieces
        CompleteMap fullMap = new CompleteMap();
        System.out.println("\nFull map:");
        printMap(fullMap.getFullMap());
        
        int[][] mapFull_pixelConverted = fullMap.getFullMap();
        int pathCountFull = pixelMap.countPaths4Stations(mapFull_pixelConverted);
        System.out.println("Path count for full map that passes through exactly 4 stations: " + pathCountFull);
        
        //Find shortest paths for Full Map
        List<List<String>> shortestPathsMapFull = ShortestPath.FindShortestPaths(mapFull_pixelConverted);
        
        //Print shortest paths for Full Map
        System.out.println("\nShortest paths for Full Map that passes through exactly 4 stations:");
        for (List<String> path : shortestPathsMapFull) {
            System.out.println(path);
        }

        //Create a png file of the full pixel map
        int[][] mapFull_pixelValues = new int[40][20];
        for (int y = 0; y < 40; y++) {
            for (int x = 0; x < 20; x++) {
                mapFull_pixelValues[y][x] = (int) Math.round(mapFull_pixelConverted[y][x] * 64);
            }
        }

        BufferedImage fullPixelMap = new BufferedImage(20, 40, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < 40; y++) {
            for (int x = 0; x < 20; x++) {
                int pixelValue = mapFull_pixelValues[y][x];
                int rgbValue = (pixelValue << 16) | (pixelValue << 8) | pixelValue; // Assuming grayscale values
                System.out.println(rgbValue);
                fullPixelMap.setRGB(x, y, rgbValue);
            }
        }
        
        File outputFile = new File("pixel_map.png");
        try {
            ImageIO.write(fullPixelMap, "png", outputFile);
            System.out.println("Pixel map saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save pixel map: " + e.getMessage());
        }
        
        //Display the full pixel map using JFrame
        try {
            File imageFile = new File("pixel_map.png");
            BufferedImage pixelMapImage = ImageIO.read(imageFile);
            
            //Enlarge the display
            DisplayPixelMap panel = new DisplayPixelMap(enlargePixelMap(pixelMapImage,16));
        
            int enlargedW = enlargePixelMap(pixelMapImage,17).getWidth();
            int enlargedH = enlargePixelMap(pixelMapImage,17).getHeight();

            //Display the map
            JFrame frame = new JFrame("Pixel Map Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel);
            frame.setSize(enlargedW, enlargedH);
            frame.setVisible(true);
            
        } catch (IOException e) {
            System.out.println("Failed to convert image to BufferedImage: " + e.getMessage());
        }
        
    }
    
    public static void printMap(int[][] map) {
        int height = map.length;
        int width = map[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

        
    //Search for number of possible path using depth-first-search, passing through exactly 3 stations    
    public int dfs3(int[][] MP, int currX, int currY, int visitedStations, boolean[][] visited) {
        if (currX < 0 || currX >= MP[0].length || currY < 0 || currY >= MP.length
            || MP[currY][currX] == 1 || visitedStations > 3)
            return 0;

        if (visited[currY][currX])
            return 0;

        if (MP[currY][currX] == 2)
            visitedStations++;

        if (visitedStations >= 3 && MP[currY][currX] == 3)
            return 1;

        visited[currY][currX] = true;
        int pathCount = 0;

        for (int[] direction : Directions) {
            int newX = currX + direction[0];
            int newY = currY + direction[1];
            pathCount += dfs3(MP, newX, newY, visitedStations, visited);
        }

        visited[currY][currX] = false;

        if (MP[currY][currX] == 2)
            visitedStations--;
        return pathCount;
    }

    //Search for number of possible path using depth-first-search, passing through exactly 4 stations    
    public int dfs4(int[][] MP, int currX, int currY, int visitedStations, boolean[][] visited) {
        if (currX < 0 || currX >= MP[0].length || currY < 0 || currY >= MP.length
            || MP[currY][currX] == 1 || visitedStations > 4)
            return 0;

        if (visited[currY][currX])
            return 0;

        if (MP[currY][currX] == 2)
            visitedStations++;

        if (visitedStations >= 4 && MP[currY][currX] == 3)
            return 1;

        visited[currY][currX] = true;
        int pathCount = 0;

        for (int[] direction : Directions) {
            int newX = currX + direction[0];
            int newY = currY + direction[1];
            pathCount += dfs4(MP, newX, newY, visitedStations, visited);
        }

        visited[currY][currX] = false;

        if (MP[currY][currX] == 2)
            visitedStations--;
        return pathCount;
    }

    public int countPaths3Stations(int[][] Map) {
        boolean[][] visited = new boolean[Map.length][Map[0].length];
        return dfs3(Map, 0, 0, 0, visited);
    }
    
    public int countPaths4Stations(int[][] Map) {
        boolean[][] visited = new boolean[Map.length][Map[0].length];
        return dfs4(Map, 0, 0, 0, visited);
    }
    
    //Enlarge image method
    public static BufferedImage enlargePixelMap(BufferedImage originalImage, int scale) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        int newWidth = originalWidth * scale;
        int newHeight = originalHeight * scale;

        BufferedImage enlargedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g2d = enlargedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return enlargedImage;
    }
    
    public static void shortestPathValuesConvert(int[][] map) {
        
        //Selected Shortest Path Route: Number 1
        
        //Down 20
        for(int i=1; i<=20; i++) 
            map[i][0] = 4;
        //Right 5
        for(int j=1; j<=5; j++) 
            map[20][j] = 4;
        //Down 3
        for(int i=21; i<=23; i++) 
            map[i][5] = 4;
        //Right 3
        for(int j=6; j<=8; j++) 
            map[23][j] = 4;
        //Down 5
        for(int i=24; i<=28; i++) 
            map[i][8] = 4;
        //Left 1
        map[28][7] = 4;
        //Down 2
        map[29][7] = 4;
        map[30][7] = 4;
        //Right 1
        map[30][8] = 4;
        //Down 2
        map[31][8] = 4;
        map[32][8] = 4;
        //Right 1
        map[32][9] = 4;
        //Down 2
        map[33][9] = 4;
        map[34][9] = 4;
        //Right 1
        map[34][10] = 4; 
        //Down 4
        map[35][10] = 4;
        map[36][10] = 4;
        map[37][10] = 4;
        map[38][10] = 4;
        //Right 3
        map[38][11] = 4;
        map[38][12] = 4;
        map[38][13] = 4;
        //Down 1
        map[39][13] = 4;
        //Right 2
        map[39][14] = 4;
        map[39][15] = 4;
        //Up 1
        map[38][15] = 4;
        //Right 3
        map[38][16] = 4;
        map[38][17] = 4;
        map[38][18] = 4;
        //Down 1
        map[39][18] = 4;
        //Right 1 is destination
        
    }
}
