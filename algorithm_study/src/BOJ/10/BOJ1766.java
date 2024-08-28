import java.io.*;
import java.util.*;

public class BOJ1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] degree = new int[N + 1];
        List<Integer>[] problems = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            problems[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            problems[from].add(to);
            degree[to]++;
        }

        Queue<Integer> que = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                que.add(i);
            }
        }

        while (!que.isEmpty()) {
            int now = que.poll();
            sb.append(now).append(' ');

            for (int next : problems[now]) {
                degree[next]--;

                if (degree[next] == 0) {
                    que.add(next);
                }
            }
        }

        System.out.println(sb);
    }
}
