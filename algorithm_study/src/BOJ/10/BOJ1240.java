import java.io.*;
import java.util.*;

public class BOJ1240 {

    static List<Node>[] G;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        G = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            G[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            G[a].add(new Node(b, weight));
            G[b].add(new Node(a, weight));
        }

        Deque<Node> q = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            visited = new boolean[N + 1];

            answer = 0;
            visited[start] = true;
            dfs(start, end, 0);
            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs (int now, int end, int length) {
        for (Node node : G[now]) {
            if (node.num == end) {
                answer = length + node.weight;
                return;
            }

            if (!visited[node.num]) {
                visited[node.num] = true;
                dfs(node.num, end, length + node.weight);
                visited[node.num] = false;
            }
        }
    }

    static class Node {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }
}
