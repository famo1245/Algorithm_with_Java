import java.util.*;
import java.io.*;

public class BOJ1504 {

    static int N;
    static List<int[]>[] G;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        G = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
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

        st = new StringTokenizer(br.readLine());
        int start = 1;
        int end = N;
        int via1 = Integer.parseInt(st.nextToken());
        int via2 = Integer.parseInt(st.nextToken());
        int answer;
        int sum = 0;

        sum += dijkstra(start, via1);;
        sum += dijkstra(via1, via2);
        sum += dijkstra(via2, end);
        answer = sum;
        sum = 0;

        sum += dijkstra(start, via2);
        sum += dijkstra(via2, via1);
        sum += dijkstra(via1, end);
        answer = Math.min(answer, sum);

        if (answer < 0) {
            answer = -1;
        }

        System.out.println(answer);
    }

    static int dijkstra(int start, int end) {
        if (start == end) {
            return 0;
        }

        int[] distance = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        distance[start] = 0;
        pq.add(new int[] {start, distance[start]});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int node = now[0];
            int weight = now[1];

            if (weight > distance[node]) {
                continue;
            }

            for (int[] next : G[node]) {
                int nextNode = next[0];
                int nextWeight = next[1];

                if (distance[nextNode] > weight + nextWeight) {
                    distance[nextNode] = weight + nextWeight;
                    pq.add(new int[] {nextNode, weight + nextWeight});
                }
            }
        }

        return distance[end] == Integer.MAX_VALUE ? Integer.MIN_VALUE : distance[end];
    }
}
