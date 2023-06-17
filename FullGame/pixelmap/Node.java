package pixelmap;

import java.util.HashSet;

public class Node {
    int row;
    int col;
    int dist;
    HashSet<String> visited;
    Node parent;

    Node(int row, int col, int dist, HashSet<String> visited, Node parent) {
        this.row = row;
        this.col = col;
        this.dist = dist;
        this.visited = visited;
        this.parent = parent;
    }
}
