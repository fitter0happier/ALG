package alg;

import java.util.LinkedList;
import java.util.Queue;

public class Field {
    public Cell start = null;
    public Cell finish = null;

    public int rows;
    public int cols;
    public int colorCount;

    public int totalPathLen;

    public Cell[][] grid;

    public Field (Cell[][] grid, int rows, int cols, int colorCount) {
        this.grid = grid;
        this.rows = rows;
        this.cols = cols;
        this.colorCount = colorCount;

        start = grid[rows - 1][0];
        finish = grid[0][cols - 1];
        connectGraph();

        this.totalPathLen = calcPath(start, 0, 0);
    }

    private void connectGraph() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j != 0) {
                    grid[i][j].appendNeighbour(0, grid[i][j - 1]);
                }
                if (i != 0) {
                    grid[i][j].appendNeighbour(1, grid[i - 1][j]);
                }
                if (j != cols - 1) {
                    grid[i][j].appendNeighbour(2, grid[i][j + 1]);
                }
                if (i != rows - 1) {
                    grid[i][j].appendNeighbour(3, grid[i + 1][j]);
                }
            }
        }
    }

    private int calcPath(Cell Cell, int len, int colorCount) {
        Queue<Cell> queue = new LinkedList<Cell>(); 
        Queue<Integer> states = new LinkedList<>();

        Cell.visited[colorCount] = true;
        queue.add(Cell);
        states.add(colorCount);

        int state = 0;

        while(!queue.isEmpty()) {
            Cell = queue.remove();
            state = states.remove();
            colorCount = state;

            if (Cell == finish) {
                return Cell.getPathLength();
            }

            if (Cell.value < 0) {
                colorCount = -1 * Cell.getValue();
                state = colorCount;
            }

            for (int i = 0; i < 4; ++i) {
                if (Cell.neighbors[i] != null 
                && (Cell.neighbors[i].value == state || Cell.neighbors[i].value <= 0)) {
                    if (Cell.neighbors[i].visited[colorCount] == false) {
                        Cell.neighbors[i].visited[colorCount] = true;
                        queue.add(Cell.neighbors[i]);
                        states.add(state);
                        Cell.neighbors[i].pathLen = Cell.pathLen + 1;
                    }
                }
            }
        }

        return -1;
    }

    public void printAnswer() {
        System.out.println(totalPathLen);
    }
}
