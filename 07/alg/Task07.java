package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task07 {
    public static int commandCount = 0;
    public static String[] simCommands = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
        br.close();
    }

    public static void solution(BufferedReader br) throws IOException {
        String[] params = br.readLine().split(" ");

        commandCount = Integer.parseInt(params[0]);
        int simLength = Integer.parseInt(params[1]);
        int satLength = Integer.parseInt(params[2]);

        simCommands = new String[commandCount];

        String[] letters = {"A", "B", "C", "D"};

        for (int i = 0; i < commandCount; ++i) {
            String command = br.readLine();
            simCommands[i] = command;
        }

        long[][] totalCount = new long[satLength - simLength][commandCount];

        for (int i = 0; i < satLength - simLength; ++i) {
            for (int j = 0; j < commandCount; ++j) {
                for (int k = 0; k < letters.length; ++k) {
                    String commandToCheck = simCommands[j] + letters[k];
                    int index = checkString(commandToCheck.substring(1));
                    if (index != -1) {
                        if (i == 0) {
                            totalCount[i][index] += 1;
                        } else {
                            totalCount[i][index] += 1 * totalCount[i - 1][j];
                        }
                    }
                }
            }
        }

        long total = 0;

        for (int i = 0; i < commandCount; ++i) {
            total += totalCount[satLength - simLength - 1][i];
        }

        System.out.println(total);
    }

    public static int checkString(String str) {   
        for (int i = 0; i < commandCount; ++i) {
            if (simCommands[i].equals(str)) {
                return i;
            }
        }

        return -1;
    }

}
