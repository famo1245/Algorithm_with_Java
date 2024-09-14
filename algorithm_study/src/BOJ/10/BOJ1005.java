import java.util.*;
import java.io.*;

public class BOJ1005 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] times = new int[N + 1];
            int[] degree = new int[N + 1];
            int[] result = new int[N + 1];
            List<Integer>[] buildings = new ArrayList[N + 1];

            for (int j = 1; j <= N; j++) {
                buildings[j] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                times[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                buildings[from].add(to);
                degree[to]++;
            }

            int end = Integer.parseInt(br.readLine());
            Queue<Integer> q = new ArrayDeque<>();
            for (int j = 1; j <= N; j++) {
                if (degree[j] == 0) {
                    q.add(j);
                }
            }

            while (!q.isEmpty()) {
                int num = q.poll();
                result[num] += times[num];

                if (num == end) {
                    break;
                }

                for (int next : buildings[num]) {
                    result[next] = Math.max(result[next], result[num]);
                    degree[next]--;

                    if (degree[next] == 0) {
                        q.add(next);
                    }
                }
            }

            sb.append(result[end]).append('\n');
        }

        System.out.print(sb);
    }
}
