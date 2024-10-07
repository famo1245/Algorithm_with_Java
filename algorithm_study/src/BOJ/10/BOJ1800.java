import java.util.*;
import java.io.*;

public class BOJ1800 {

    static List<Cable>[] graph;
    static int K, N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        int maxCost = -1;

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Cable(to, cost));
            graph[to].add(new Cable(from, cost));
            maxCost = Math.max(maxCost, cost);
        }

        answer = Integer.MAX_VALUE;
        calcMax(0, maxCost);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static boolean fn(int value) {
        boolean[][] visited = new boolean[N + 1][K + 1];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1, K});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int node = now[0];
            int remainFree = now[1];

            if (node == N) {
                return true;
            }

            if (visited[node][remainFree]) {
                continue;
            }

            visited[node][remainFree] = true;

            for (Cable next : graph[node]) {
                if (next.cost <= value) {
                    if (!visited[next.to][remainFree]) {
                        q.add(new int[]{next.to, remainFree});
                    }
                } else if (remainFree > 0) {
                    if (!visited[next.to][remainFree - 1]) {
                        q.add(new int[]{next.to, remainFree - 1});
                    }
                }
            }
        }

        return false;
    }

    static void calcMax(int start, int end) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        if (fn(mid)) {
            answer = mid;
            calcMax(start, mid - 1);
        } else {
            calcMax(mid + 1, end);
        }
    }

    static class Cable {
        int to;
        int cost;

        public Cable(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
