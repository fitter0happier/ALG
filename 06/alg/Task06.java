package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task06 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
        br.close();
    }

    public static void solution (BufferedReader br) throws IOException {
        int opsCount = Integer.parseInt(br.readLine());
        String[] commands = new String[opsCount];

        for (int i = 0; i < opsCount; ++i) {
            commands[i] = br.readLine();
        }

        Graph graph = new Graph(opsCount, commands);
        graph.buildGraph();
        graph.printAnswer();
    }
}
