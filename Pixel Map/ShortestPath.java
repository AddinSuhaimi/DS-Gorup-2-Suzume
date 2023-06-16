package ds-group-2-suzume;

import java.util.*;
/**
 *
 * @author HP
 */
public class ShortestPath {
    private static final int[][] movement = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final String[] direction = {"Up", "Down", "Left", "Right"};

    //Use breadth-first-search algorithm to find the shortest path
    public static List<List<String>> FindShortestPaths(int[][] map) {
        
        //A list is created to store the resulting shortest paths that has been found
        //A queue is created to perform breadth-first search. It stores nodes that need to be explored.
        List<List<String>> shortestPaths = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        HashSet<String> FirstPosition = new HashSet<>();
        FirstPosition.add("0,0");
        queue.offer(new Node(0, 0, 0, FirstPosition, null));

        int ShortestDist = Integer.MAX_VALUE;

        //Implementation of BFS algorithm using queue
        //The BFS algorithm begins with a loop that continues until the queue becomes empty.
        //In each iteration, a node is dequeued from the queue.
        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            //If the current node's distance is greater than the shortest distance, 
            //The iteration continues with the next node in the queue.
            if (curr.dist > ShortestDist) {
                continue;
            }

            if (map[curr.row][curr.col] == 3) {
                //Once the destination has been reached, set the shortest distance as the current distance
                if (curr.dist < ShortestDist) {
                    shortestPaths.clear();
                    ShortestDist = curr.dist;
                }

                List<String> path = new ArrayList<>();
                Node node = curr;
                while (node.parent != null) {

                    int RowDirection = node.row - node.parent.row;
                    int ColDirection = node.col - node.parent.col;
                    for (int i = 0; i < movement.length; i++) {
                        if (movement[i][0] == RowDirection && movement[i][1] == ColDirection) {
                            path.add(direction[i]);
                            break;
                        }
                    }
                    node = node.parent;
                }
                Collections.reverse(path);
                shortestPaths.add(path);
                continue;
            }

            //Handling the exploration of neighboring positions from the current node during BFS
            for (int i = 0; i < movement.length; i++) {
                int newRow = curr.row + movement[i][0];
                int newCol = curr.col + movement[i][1];
                String key = newRow + "," + newCol;

                //Creates new nodes for valid and unvisited positions and enqueue to continue the breadth-first search.
                //The new node is then added to the queue to be explored later in the breadth-first search.
                if (isValid(map, newRow, newCol) && !curr.visited.contains(key)) {
                    HashSet<String> newVisited = new HashSet<>(curr.visited);
                    newVisited.add(key);
                    queue.offer(new Node(newRow, newCol, curr.dist + 1, newVisited, curr));
                }
            }
        }
        return shortestPaths;
    }

    private static boolean isValid(int[][] map, int height, int width) {
        if (height < 0 || height >= map.length || width < 0 || width >= map[0].length) {
            return false;
        }
        int value = map[height][width];
        return value != 1;
    }
}
