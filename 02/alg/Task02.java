package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Task02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
        br.close();
    }

    static void solution(BufferedReader br) throws IOException {
        String[] params = br.readLine().split(" ");
        String[] weights = br.readLine().split(" ");
        String[] edges = new String[3];

        String reader = new String();

        int verticeCount = Integer.parseInt(params[0]);
        int boxesCount = Integer.parseInt(params[1]);

        int[] boxes = new int[boxesCount];
        for (int i = 0; i < boxesCount; ++i) {
            boxes[i] = Integer.parseInt(weights[i]);
        }

        int[][] graph = new int[verticeCount][verticeCount];

        while (true) {
            reader = br.readLine();
            if (reader == null) {
                break;
            }

            edges = reader.split(" ");

            graph[Integer.parseInt(edges[0])][Integer.parseInt(edges[1])] = Integer.parseInt(edges[2]); 
        }

        Warehouse warehouse = new Warehouse(graph, boxes);
        warehouse.fillWarehouse(0, 0, warehouse.entrance);
        warehouse.printAnswer();
    }
}
