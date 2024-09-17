import java.io.*;
import java.util.*;

public class BOJ32090 {

    static final String INSERT = "INSERT";
    static final String LEFT = "LEFT";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        while (N != 0) {
            Deque<String> left = new ArrayDeque<>();
            Deque<String> right = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                String ch = st.nextToken();

                if (command.equals(INSERT)) {
                    left.addLast(ch);
                } else if (command.equals(LEFT)) {
                    if (!left.isEmpty()) {
                        right.addFirst(left.pollLast());
                    }
                } else {
                    if (!right.isEmpty()) {
                        left.addLast(right.pollFirst());
                    }
                }
            }

            while (!left.isEmpty()) {
                sb.append(left.pollFirst());
            }

            while (!right.isEmpty()) {
                sb.append(right.pollFirst());
            }

            sb.append('\n');
            N = Integer.parseInt(br.readLine());
        }

        System.out.print(sb);
    }
}
