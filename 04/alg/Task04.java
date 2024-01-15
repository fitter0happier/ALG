package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task04 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
        br.close();
    }

    public static void solution(BufferedReader br) throws IOException {
        String[] params = br.readLine().split(" ");
        int rows = Integer.parseInt(params[0]);
        int cols = Integer.parseInt(params[1]);
        int colorCount = Integer.parseInt(params[2]);

        Cell[][] grid = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] gridRow = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(Integer.parseInt(gridRow[j]), colorCount);
            }
        }

        Field field = new Field(grid, rows, cols, colorCount);
        field.printAnswer();
    }
}
