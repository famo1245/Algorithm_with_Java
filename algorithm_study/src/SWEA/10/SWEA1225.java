import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class SWEA1225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int testCase = 1; testCase <= 10; testCase++) {
            int cases = Integer.parseInt(br.readLine());
            sb.append("#").append(cases).append(" ");
            st = new StringTokenizer(br.readLine());
            ArrayDeque<Integer> q = new ArrayDeque<>();

            for (int i = 0; i < 8; i++) {
                q.addLast(Integer.parseInt(st.nextToken()));
            }

            int num = 1;
            int count = 1;
            while (num > 0) {
                num = q.pollFirst() - count++;

                if (num < 0) {
                    num = 0;
                }

                if (count > 5) {
                    count -= 5;
                }
                q.addLast(num);
            }
            for (Integer n : q) {
                sb.append(n).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
