package ds-group-2-suzume;

import java.util.List;

/**
 *
 * @author HP
 */
public class PixelMatrix {
    public static void convertToMatrix(List<String> path) {
        int[][] matrix = new int[40][20];

        int currentX = 0;
        int currentY = 0;

        matrix[currentX][currentY] = 0;

        for (String direction : path) {
            switch (direction) { 
                case "Up":
                    currentX--;
                    break;
                case "Down":
                    currentX++;
                    break;
                case "Left":
                    currentY--;
                    break;
                case "Right":
                    currentY++;
                    break;
            }

            if (currentX >= 0 && currentX < matrix.length && currentY >= 0 && currentY < matrix[currentX].length) {
                matrix[currentX][currentY] = 0;
            }
        }

        displayMatrix(matrix);
    }

    public static void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
    public static int[][] GenNewMatrix(List<String> path) {
        int[][] matrixNew = new int[40][20];

        int currentRow = 0;
        int currentCol = 0;

        for (String direction : path) {
            matrixNew[currentRow][currentCol] = 1;

            if (direction.equals("Right")) {
                currentCol++;
            } else if (direction.equals("Left")) {
                currentCol--;
            } else if (direction.equals("Up")) {
                currentRow--;
            } else if (direction.equals("Down")) {
                currentRow++;
            }
        }
        return matrixNew;
    }
}
