/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ds.gorup.pkg2.suzume;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.imageio.ImageIO;
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
        int pathCount1 = pixelMap.countPaths(map1_pixelConverted);
        int pathCount2 = pixelMap.countPaths(map2_pixelConverted);
        int pathCount3 = pixelMap.countPaths(map3_pixelConverted);
        int pathCount4 = pixelMap.countPaths(map4_pixelConverted);

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

        
        //Search for number of possible path
        public int bfs(int[][] Map) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] startVisited = new boolean[Map.length][Map[0].length];
        startVisited[0][0] = true;
        queue.offer(new int[]{0, 0, 0, 0});

        List<boolean[][]> visitedList = new ArrayList<>();
        visitedList.add(startVisited);

        int pathCount = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currX = curr[0];
            int currY = curr[1];
            int visitedStations = curr[2];
            int visitedIndex = curr[3];
            boolean[][] currVisited = visitedList.get(visitedIndex);

            if (Map[currY][currX] == 3 && visitedStations == 3) {
                pathCount++;
                continue;
            }

            for (int[] direction : Directions) {
                int newX = currX + direction[0];
                int newY = currY + direction[1];

                if (newX < 0 || newX >= Map[0].length || newY < 0 || newY >= Map.length
                        || Map[newY][newX] == 1 || currVisited[newY][newX])
                    continue;

                int newVisitedStations = visitedStations;
                if (Map[newY][newX] == 2 && visitedStations < 3) {
                    newVisitedStations++;
                } else if (Map[newY][newX] == 2) {
                    continue; 
                }

                if (Map[newY][newX] == 3 && newVisitedStations < 3)
                    continue;

                boolean[][] newVisited = new boolean[Map.length][Map[0].length];
                for (int i = 0; i < Map.length; i++) {
                    System.arraycopy(currVisited[i], 0, newVisited[i], 0, Map[0].length);
                }
                newVisited[newY][newX] = true;

                visitedList.add(newVisited);
                int newVisitedIndex = visitedList.size() - 1;
                queue.offer(new int[]{newX, newY, newVisitedStations, newVisitedIndex});
            }
        }
        return pathCount;
    }
        
        public int dfs(int[][] MP, int currX, int currY, int visitedStations, boolean[][] visited) {
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
            pathCount += dfs(MP, newX, newY, visitedStations, visited);
        }

        visited[currY][currX] = false;

        if (MP[currY][currX] == 2)
            visitedStations--;
        return pathCount;
    }


    public int countPaths(int[][] Map) {
        boolean[][] visited = new boolean[Map.length][Map[0].length];
        return dfs(Map, 0, 0, 0, visited);
    }

    //Formation of complete map
    public int[][] FullMap() {
        int[][] TempMP1 = new int[20][10], TempMP2 = new int[20][10],
                TempMP3 = new int[20][10], TempMP4 = new int[20][10];

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 10 ; j++){
                TempMP1[i][j] = Map1[i][j];
            }
        }

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 10 ; j++){
                TempMP2[i][j] = Map2[i][j];
            }
        }

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 10 ; j++){
                TempMP3[i][j] = Map3[i][j];
            }
        }

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 10 ; j++){
                TempMP4[i][j] = Map4[i][j];
            }
        }

        TempMP1[19][9] = 1;
        TempMP2[19][9] = 1;
        TempMP3[19][9] = 1;

        int[][] FullMap = new int[40][20];

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                FullMap[i][j] = TempMP1[i][j];
            }
        }

        for (int i = 0, y = 0; i < 20; i++, y++) {
            for (int j = 10, x = 0; j < 20; j++, x++) {
                FullMap[i][j] = TempMP2[y][x];
            }
        }

        for (int i = 20, y = 0; i < 40; i++, y++) {
            for (int j = 0, x = 0; j < 10; j++, x++) {
                FullMap[i][j] = TempMP3[y][x];
            }
        }

        for (int i = 20, y = 0; i < 40; i++, y++) {
            for (int j = 10, x = 0; j < 20; j++, x++) {
                FullMap[i][j] = TempMP4[y][x];
            }
        }
        return FullMap;
    }
}   
    
