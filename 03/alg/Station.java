package alg;

public class Station {
    private final int value;
    private Station left;
    private Station right;
    private boolean visited = false;

    public Station(int value){
        this.value = value;
    }

    public void setLeft(Station left) {
        this.left = left;
    }

    public void setRight(Station right) {
        this.right = right;
    }

    public void setVisited() {
        this.visited = true;
    }

    public Station getLeft() {
        return this.left;
    }

    public Station getRight() {
        return this.right;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public int getValue() {
        return this.value;
    }
}
