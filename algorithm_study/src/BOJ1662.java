import java.util.*;
import java.io.*;

public class BOJ1662 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;
        int inBracket = 0;
        String s = br.readLine();
        ArrayDeque<String> q = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            String temp = Character.toString(s.charAt(i));
            // 더하기와 곱하기 구분하기
            if (temp.equals(")")) {
                int count = inBracket == 1 ? 0 : answer;
                System.out.println("count = " + count + "inBracket : " + inBracket);
                while (!q.pop().equals("(")) {
                    count++;
                }

                inBracket--;
                int k = Integer.parseInt(q.pop());
                count *= k;

                while (q.peek() != null && !(q.peek()).equals("(")) {
                    q.pop();
                    count++;
                }

                answer += count;
                System.out.println("count = " + count + "answer : " + answer);
                continue;
            }

            if (temp.equals("(")) {
                inBracket++;
            }
            q.push(temp);
        }

        // 남은거 처리
        if (!q.isEmpty()) {
            int temp = 0;
            while (!q.isEmpty()) {
                q.pop();
                temp++;
            }

            answer += temp;
        }

        System.out.println(answer);
    }
}