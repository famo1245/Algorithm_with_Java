import java.util.*;
import java.io.*;

public class BOJ1922 {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.add(new int[]{weight, a, b});
        }

        int answer = 0;
        int count = 0;
        while (!pq.isEmpty() && count < N - 1) {
            int[] now = pq.poll();
            int weight = now[0];
            int from = now[1];
            int to = now[2];

            if (find(from) != find(to)) {
                union(from, to);
                answer += weight;
                count++;
            }
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }

        int root = find(parents[x]);
        parents[x] = root;
        return root;
    }

    static void union(int node1, int node2) {
        if (node1 != parents[node1]) {
            node1 = find(node1);
        }

        if (node2 != parents[node2]) {
            node2 = find(node2);
        }

        if (node1 > node2) {
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }

        if (node1 != node2) {
            parents[node1] = node2;
        }
    }
}
