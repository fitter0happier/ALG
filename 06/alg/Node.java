package alg;

public class Node {

    public Node left = null;
    public Node right = null;

    public boolean deleted = false;
    public int markedNum = 0;

    public int value;
    public int height = 1; 

    public Node(int value) {
        this.value = value;
    }
}
