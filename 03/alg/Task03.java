package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task03 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
        br.close();
    }

    public static void solution(BufferedReader br) throws IOException {
        int verticeCount = Integer.parseInt(br.readLine());
        String[] verticesStr = br.readLine().split(" ");
        int[] verticesPreorder = new int[verticeCount];

        for (int i = 0; i < verticeCount; ++i) {
            verticesPreorder[i] = Integer.parseInt(verticesStr[i]);
        }
        
        Railway railway = new Railway(verticesPreorder);
        railway.printAnswer(railway.stationCount);
    }
}
