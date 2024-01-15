package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task05 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
        br.close();
    }

    public static void solution (BufferedReader br) throws IOException {
        String[] params = br.readLine().split(" ");
        int maxLength = (int) (Math.pow(2, Integer.parseInt(params[0])) - 1);
        int opsCount = Integer.parseInt(params[1]);

        String[] commands = new String[opsCount];

        for (int i = 0; i < opsCount; ++i) {
            commands[i] = br.readLine();
        }

        Tree tree = new Tree(maxLength, opsCount, commands);
        tree.buildGraph();
        tree.printAnswer();
    }
}


