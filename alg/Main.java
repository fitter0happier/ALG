package alg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException{
        //this reads input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //this reads 1 line from your input and returns an array, 
        //useful for init parameters
        String[] params = br.readLine().split(" ");

        //parse strings like this
        int param_1 = Integer.parseInt(params[0]);

        //how to create class
        Class object = new Class(param_1);

        //how to call class methodss
        object.saySomething();
    }
}
