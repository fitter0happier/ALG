package alg;

public class Room {
    private Room left = null;
    private Room right = null;
    private Room previous = null;

    final private int price;

    private int beenHere = 0;
    private int boxesTotal = 0;

    public Room(int price) {
        this.price = price;
    }
    
    public void setLeft(Room left) {
        this.left = left;
    }

    public void setRight(Room right) {
        this.right = right;
    }

    public void setPrevious(Room previous) {
        this.previous = previous;
    }

    public Room getLeft() {
        return this.left;
    }

    public Room getRight() {
        return this.right;
    }

    public int getPrice() {
        return this.price;
    }

    public void addBox(int weight) {
        this.boxesTotal += weight;
        this.beenHere += 1;
    }

    public int getTotalWeight() {
        return this.boxesTotal;
    }

    public int getTotalPrice() {
        return this.price * this.beenHere;
    }

    public boolean isAvailable() {
        if ((this.left == null || this.left.boxesTotal != 0) && 
            (this.right == null || this.right.boxesTotal != 0) &&
            (this.previous == null || this.previous.getTotalWeight() == 0)) {
            return true;
        }

        return false;
    }

    public void removeBox(int weight) {
        this.boxesTotal -= weight;
        this.beenHere -= 1;
    }
}
