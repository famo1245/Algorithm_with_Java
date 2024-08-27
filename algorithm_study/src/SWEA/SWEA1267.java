import java.io.*;
import java.util.*;

public class SWEA1267 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int testCase = 1; testCase <= 10; testCase++) {
            sb.append('#').append(testCase).append(' ');

            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            List<Integer>[] G = new ArrayList[V + 1];
            int[] degree = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                G[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                G[a].add(b);
                degree[b]++;
            }

            Queue<Integer> que = new ArrayDeque<>();
            for (int i = 1; i <= V; i++) {
                if (degree[i] == 0) {
                    que.add(i);
                }
            }

            while (!que.isEmpty()) {
                int now = que.poll();
                sb.append(now).append(' ');

                for (int next : G[now]) {
                    if (--degree[next] == 0) {
                        que.add(next);
                    }
                }
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }
}
