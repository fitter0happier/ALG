package alg;

public class Railway {
    public Station root;
    public final int[] verticesPreorder;
    public int stationCount = 0;
    private int index = 0;

    public Railway(int[] verticesPreorder) {
        this.verticesPreorder = verticesPreorder;
        int verticeCount = verticesPreorder.length;
        root = createRailway(verticesPreorder, verticeCount);
        int[] rootInfo = calculatePath(root);
        stationCount = rootInfo[1];
    }

    public final Station createRailway(int[] preorder, int bound) {
        if (index == preorder.length || preorder[index] > bound) {
            return null;
        }

        Station root = new Station(preorder[index++]);
        root.setLeft(createRailway(preorder, root.getValue()));
        root.setRight(createRailway(preorder, bound));

        return root;
    }

    private int[] calculatePath(Station station) {
        if (station == null) {
            return new int[]{0, 0};
        }

        int[] leftInfo = calculatePath(station.getLeft());
        int[] rightInfo = calculatePath(station.getRight());

        int maxDeep = Math.max(leftInfo[0], rightInfo[0]) + 1;
        int fastTrack = Math.max(leftInfo[1] + rightInfo[0], leftInfo[0] + rightInfo[1]) + 1;

        return new int[]{maxDeep, fastTrack};
    }

    public final void printAnswer(int result) {
        System.out.println(result);
    }
}
