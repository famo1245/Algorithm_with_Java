import java.util.*;
import java.io.*;

public class BOJ6497 {

    static int[] parent;
    static int m, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            parent = new int[m];

            if (m == 0 && n == 0) {
                break;
            }

            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }

            PriorityQueue<Line> pq = new PriorityQueue<>();
            int sumCosts = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                sumCosts += value;
                pq.add(new Line(from, to, value));
            }

            int count = 0;
            int cost = 0;
            while (!pq.isEmpty() && count < m) {
                Line now = pq.poll();
                if (find(now.from) != find(now.to)) {
                    union(now.from, now.to);
                    count++;
                    cost += now.value;
                }
            }

            sb.append(sumCosts - cost).append('\n');
        }

        System.out.print(sb);
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        if (a != parent[a]) {
            a = find(parent[a]);
        }

        if (b != parent[b]) {
            b = find(parent[b]);
        }

        if (a != b) {
            parent[a] = b;
        }
    }

    static class Line implements Comparable<Line> {
        int from;
        int to;
        int value;

        public Line(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.value, o.value);
        }
    }
}
