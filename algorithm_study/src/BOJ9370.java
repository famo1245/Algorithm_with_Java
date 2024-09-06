import java.io.*;
import java.util.*;

public class BOJ9370 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            List<Road>[] G = new ArrayList[n + 1];
            int[] distance = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                G[i] = new ArrayList<>();
                distance[i] = Integer.MAX_VALUE;
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                G[a].add(new Road(b, d));
                G[b].add(new Road(a, d));
            }

            List<Integer> query = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int x = Integer.parseInt(br.readLine());
                query.add(x);
            }

            PriorityQueue<Road> pq = new PriorityQueue<>();
            pq.add(new Road(s, 0));
            distance[s] = 0;
            while (!pq.isEmpty()) {
                Road now = pq.poll();

                if (distance[now.to] < now.distance) {
                    continue;
                }

                for (Road next : G[now.to]) {
                    if (distance[next.to] > distance[now.to] + now.distance) {
                        distance[next.to] = distance[now.to] + now.distance;
                        pq.add(new Road(next.to, distance[next.to]));

                        if (query.contains(next.to) && query.contains(now.to)) {
                            query.remove((Integer) next.to);
                        }
                    }
                }
            }

            Collections.sort(query);
            for (int num : query) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static class Road implements Comparable<Road> {
        int to;
        int distance;

        public Road(int to, int distance) {
            super();
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Road o) {
            return this.distance - o.distance;
        }
    }
}