import java.util.*;
import java.io.*;

public class BOJ14950 {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.add(new Edge(a, b, weight));
        }

        int answer = (N - 1) * ((N - 2) * t) / 2;
        int count = 0;

        while (!pq.isEmpty() && count < N - 1) {
            Edge temp = pq.poll();
            if (find(temp.a) != find(temp.b)) {
                union(temp.a, temp.b);
                answer += temp.weight;
                count++;
            }
        }

        System.out.println(answer);
    }

    static class Edge implements Comparable<Edge> {
        int a;
        int b;
        int weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }

        int root = find(parents[x]);
        parents[x] = root;
        return root;
    }

    static void union(int a, int b) {
        if (a != parents[a]) {
            a = find(parents[a]);
        }

        if (b != parents[b]) {
            b = find(parents[b]);
        }

        if (a != b) {
            parents[b] = a;
        }
    }
}
