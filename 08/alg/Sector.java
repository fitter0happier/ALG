package alg;

public class Sector {
    public int dist = 0;
    public int value = 0;

    public boolean checked = false;
    public boolean canWalk = true;

    public Sector(int dist, int value, boolean canWalk) {
        this.dist = dist;
        this.value = value;
        this.canWalk = canWalk;
    }

    public int value() {
        if (!this.canWalk) {
            return 0;
        }

        return this.value;
    }

    public int dist() {
        if (!this.canWalk) {
            return Integer.MAX_VALUE;
        }

        return this.dist;
    }
}
