import java.io.*;
import java.util.*;

public class BOJ1655 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> bigger = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        int middle = Integer.parseInt(br.readLine());
        sb.append(middle).append('\n');

        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num < middle) {
                smaller.add(num);
            } else {
                bigger.add(num);
            }

            if (bigger.size() - smaller.size() > 1) {
                smaller.add(middle);
                middle = bigger.poll();
            }

            if (smaller.size() - bigger.size() >= 1) {
                bigger.add(middle);
                middle = smaller.poll();
            }

            sb.append(middle).append('\n');
        }

        System.out.print(sb);
    }
}
