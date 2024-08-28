import java.io.*;
import java.util.*;

public class SWEA1238 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int testCase = 1; testCase <= 1; testCase++) {
            sb.append('#').append(testCase).append(' ');
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            List<Integer>[] networkMap = new ArrayList[101];

            for (int i = 1; i <= 100; i++) {
                networkMap[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i += 2) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                networkMap[from].add(to);
            }

            Queue<int[]> que = new ArrayDeque<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            int nowDepth = 0;

            boolean[] called = new boolean[101];
            que.add(new int[] {start, 0});
            called[start] = true;

            while (!que.isEmpty()) {
                int[] now = que.poll();
                int node = now[0];
                int depth = now[1];

                if (depth != nowDepth) {
                    nowDepth = depth;
                    pq.clear();
                }
                pq.add(node);

                for (int next : networkMap[node]) {
                    if (!called[next]) {
                        que.add(new int[] {next, depth + 1});
                        called[next] = true;
                    }
                }
            }

            sb.append(pq.poll()).append('\n');
        }

        System.out.print(sb);
    }
}
