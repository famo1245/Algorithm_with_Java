import java.io.*;
import java.util.*;

public class BOJ3078 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        K++;

        long answer = 0;
        Set<Integer> set;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            q.add(temp.length());

            if (q.size() == K) {
                set = new HashSet<>(q);
                answer += (K - set.size());
                q.pollFirst();
            }
        }

        if (!q.isEmpty()) {
            set = new HashSet<>(q);
            answer += (q.size() - set.size());
        }

        System.out.println(answer);
    }
}
