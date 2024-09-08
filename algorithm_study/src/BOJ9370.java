import java.io.*;
import java.util.*;

public class BOJ9370 {

    static boolean[] visited;
    static List<Road>[] G;
    static int[] distance, distanceVia, query;
    static int n, via;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            G = new ArrayList[n + 1];
            distance = new int[n + 1];
            distanceVia = new int[n + 1];
            visited = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                G[i] = new ArrayList<>();
                distance[i] = Integer.MAX_VALUE;
                distanceVia[i] = Integer.MAX_VALUE;
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

            query = new int[t];
            for (int i = 0; i < t; i++) {
                query[i] = Integer.parseInt(br.readLine());
            }

            int result = dijkstra(s, g, h);

            Road next = null;
            for (int i = 0; i < G[result].size(); i++) {
                next = G[result].get(i);
                if (next.to == h || next.to == g) {
                    via = next.to;
                    break;
                }
            }


            sb.append('\n');
        }

        System.out.print(sb);
    }

    static int dijkstra(int start, int end1, int end2) {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        distance[start] = 0;
        int result = -1;
        pq.add(new Road(start, 0));

        while (!pq.isEmpty()) {
            Road now = pq.poll();

            if (result == -1 && now.to == end1 || now.to == end2) {
                result = now.to;
            }

            if (visited[start]) {
                continue;
            }

            visited[now.to] = true;

            for (Road next : G[now.to]) {
                if (!visited[next.to] && distance[next.to] > distance[now.to] + now.distance) {
                    distance[next.to] = distance[now.to] + now.distance;
                    pq.add(new Road(next.to, distance[next.to]));
                }
            }
        }

        return result;
    }

    static void findDestination(int start, int end) {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        distanceVia[start] = 0;
        pq.add(new Road(start, 0));

        while (!pq.isEmpty()) {
            Road now = pq.poll();
            if (now.to == end) {
                return;
            }

            if (visited[start]) {
                continue;
            }

            visited[now.to] = true;

            for (Road next : G[now.to]) {
                if (!visited[next.to] && distance[next.to] > distance[now.to] + now.distance) {
                    distance[next.to] = distance[now.to] + now.distance;
                    pq.add(new Road(next.to, distance[next.to]));
                }
            }
        }
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