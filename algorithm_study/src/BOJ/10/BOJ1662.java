import java.util.*;
import java.io.*;

public class BOJ1662 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("");
        Deque<String[]> s = new ArrayDeque<>();
        int answer = 0;
        String temp = "";

        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("(")) {
                s.push(new String[] {temp, Integer.toString(answer - 1)});
                answer = 0;
            } else if (input[i].equals(")")) {
                String[] data = s.pop();
                answer = (Integer.parseInt(data[0]) * answer) + Integer.parseInt(data[1]);
            } else {
                answer++;
                temp = input[i];
            }
        }


        System.out.println(answer);
    }
}