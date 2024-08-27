import java.io.*;
import java.util.*;

public class BOJ2252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] degree = new int[N + 1];
        List<Integer>[] students = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++) {
            students[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            students[a].add(b);
            degree[b]++;
        }

        Queue<Integer> que = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                que.add(i);
            }
        }

        while (!que.isEmpty()) {
            int now = que.poll();
            sb.append(now).append(' ');

            for (int next : students[now]) {
                degree[next]--;

                if (degree[next] == 0) {
                    que.add(next);
                }
            }
        }

        System.out.println(sb);
    }
}
