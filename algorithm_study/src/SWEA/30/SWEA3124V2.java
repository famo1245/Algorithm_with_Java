import java.util.*;
import java.io.*;

public class SWEA3124V2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            List<int[]>[] G = new ArrayList[V + 1];
            boolean[] visited = new boolean[V + 1];
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

            for (int i = 1; i <= V; i++) {
                G[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                G[a].add(new int[]{b, weight});
                G[b].add(new int[]{a, weight});
            }

            long answer = 0;
            int count = 0;
            pq.add(new int[] {1, 0});
            while (!pq.isEmpty()) {
                if (count == V) {
                    break;
                }

                int[] now = pq.poll();
                int node = now[0];
                int weight = now[1];

                if (visited[node]) {
                    continue;
                }

                answer += weight;
                visited[node] = true;
                count++;

                for (int[] next : G[node]) {
                    if (!visited[next[0]]) {
                        pq.add(next);
                    }
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
