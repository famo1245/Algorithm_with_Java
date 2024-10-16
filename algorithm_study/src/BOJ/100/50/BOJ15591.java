import java.io.*;
import java.util.*;

public class BOJ15591 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        List<int[]>[] network = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            network[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            network[p].add(new int[] {q, r});
            network[q].add(new int[] {p, r});
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(bfs(k, v, network, N)).append('\n');
        }

        System.out.print(sb);
    }

    private static int bfs(int k, int v, List<int[]>[] g, int N) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        q.add(new int[]{v, Integer.MAX_VALUE});
        int answer = -1;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int video = now[0];
            int recommend = now[1];

            if (visited[video]) {
                continue;
            }

            if (recommend >= k) {
                answer++;
            }

            visited[video] = true;
            for (int[] next : g[video]) {
                int nextVideo = next[0];
                int nextRecommend = next[1];

                if (!visited[nextVideo]) {
                    q.add(new int[]{nextVideo, Math.min(recommend, nextRecommend)});
                }
            }
        }

        return answer;
    }
}
