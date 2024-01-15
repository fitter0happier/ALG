package alg;

public class Cell {
    public Cell[] neighbors;
    public int value;

    public boolean visited[];
    public int pathLen;

    public Cell(int value, int colorCount) {
        this.value = value;
        this.visited = new boolean[colorCount + 1];
        this.pathLen = 0;
        this.neighbors = new Cell[] {null, null, null, null};
    }

    public void appendNeighbour(int index, Cell cell) {
        this.neighbors[index] = cell;
    }

    public int getValue() {
        return this.value;
    }

    public int getPathLength() {
        return this.pathLen;
    }
}

