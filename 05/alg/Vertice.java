package alg;

public class Vertice {

    private Vertice left = null;
    private Vertice right = null;

    private int value;

    public Vertice(int value) {
        this.value = value;
    }

    public Vertice getLeft() {
        return this.left;
    }

    public Vertice getRight() {
        return this.right;
    }

    public int getValue() {
        return this.value;
    }

    public void setLeft(Vertice left) {
        this.left = left;
    }

    public void setRight(Vertice right) {
        this.right = right;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
}
