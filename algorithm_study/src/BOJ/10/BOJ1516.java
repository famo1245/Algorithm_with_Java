import java.util.*;
import java.io.*;

public class BOJ1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] degree = new int[N + 1];
        int[] times = new int[N + 1];
        int[] result = new int[N + 1];
        List<Integer>[] buildings = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            buildings[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int max = st.countTokens();
            for (int j = 0; j < max; j++) {
                if (j == 0) {
                    times[i] = Integer.parseInt(st.nextToken());
                    continue;
                }

                int value = Integer.parseInt(st.nextToken());
                if (value == -1) {
                    break;
                }

                buildings[value].add(i);
                degree[i]++;
            }
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                que.add(i);
            }
        }

        while (!que.isEmpty()) {
            int num = que.poll();
            result[num] += times[num];

            for (int next : buildings[num]) {
                result[next] = Math.max(result[next], result[num]);
                degree[next]--;

                if (degree[next] == 0) {
                    que.add(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append('\n');
        }

        System.out.print(sb);
    }
}
