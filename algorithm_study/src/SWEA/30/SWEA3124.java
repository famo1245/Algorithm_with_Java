import java.util.*;
import java.io.*;

public class SWEA3124 {

    static int[] parents;

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
            parents = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                parents[i] = i;
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                pq.add(new int[]{weight, a, b});
            }

            long answer = 0;
            int count = 0;
            while (!pq.isEmpty() && count < V - 1) {
                int[] now = pq.poll();
                int weight = now[0];
                int a = now[1];
                int b = now[2];

                if (find(a) != find(b)) {
                    union(a, b);
                    answer += weight;
                    count++;
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
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
            a = find(a);
        }

        if (b != parents[b]) {
            b = find(b);
        }

        if (a != b) {
            parents[b] = a;
        }
    }
}
