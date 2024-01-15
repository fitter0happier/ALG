package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Task01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
        br.close();
    }

    static void solution(BufferedReader br) throws IOException {
        String[] reader = br.readLine().split(" ");

        int field = Integer.parseInt(reader[0]);
        int park = Integer.parseInt(reader[1]);
        int rocks = Integer.parseInt(reader[3]);

        int[][] buffer = new int[field][field];

        for (int i = 0; i < field; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < field; j++) {
                buffer[i][j] = Integer.parseInt(row[j]);
            }
        }

        int[][] forestsSum = new int[field + 1][field + 1];
        int[][] rocksSum = new int[field + 1][field + 1];

        int forestMax = 0;

        for (int i = 1; i <= field; i++) {
            for (int j = 1; j <= field; j++) {
                forestsSum[i][j] = forestsSum[i - 1][j] + forestsSum[i][j - 1] - forestsSum[i - 1][j - 1];
                rocksSum[i][j] = rocksSum[i - 1][j] + rocksSum[i][j - 1] - rocksSum[i - 1][j - 1];

                if (buffer[i - 1][j - 1] == 1) {
                    forestsSum[i][j]++;
                }
                if (buffer[i - 1][j - 1] == 2) {
                    rocksSum[i][j]++;
                }
            }
        }

        for (int i = 0; i <= field - park; i++) {
            for (int j = 0; j <= field - park; j++) {
                int totalForest = forestsSum[i + park][j + park] - forestsSum[i][j + park] -
                        forestsSum[i + park][j] + forestsSum[i][j];
                int totalRocks = rocksSum[i + park][j + park] - rocksSum[i][j + park] -
                        rocksSum[i + park][j] + rocksSum[i][j];

                if (totalRocks >= rocks) {
                    forestMax = Math.max(forestMax, totalForest);
                }
            }
        }

        System.out.println(forestMax);
    }
}
