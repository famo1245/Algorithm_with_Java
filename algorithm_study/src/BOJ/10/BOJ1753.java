import java.util.*;
import java.io.*;

public class BOJ1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        List<int[]>[] G = new ArrayList[V + 1];
        int[] distance = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 1; i <= V; i++) {
            G[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            G[a].add(new int[] { b, weight });
        }

        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        que.add(new int[] { 0, K });
        distance[K] = 0;

        while (!que.isEmpty()) {
            int node = que.poll()[1];

            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            for (int[] next : G[node]) {
                int nextNode = next[0];
                int weight = next[1];
                if (distance[nextNode] > distance[node] + weight) {
                    distance[nextNode] = distance[node] + weight;
                    que.add(new int[] { distance[nextNode], nextNode });
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                sb.append("INF").append('\n');
                continue;
            }

            sb.append(distance[i]).append('\n');
        }

        System.out.print(sb);
    }
}