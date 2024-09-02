import java.util.*;
import java.io.*;

public class BOJ2056 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] degree = new int[N + 1];
        int[] times = new int[N + 1];
        int[] result = new int[N + 1];
        List<Integer>[] tasks = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            tasks[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            times[i] = time;

            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                int num = Integer.parseInt(st.nextToken());
                tasks[num].add(i);
                degree[i]++;
            }
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                que.add(i);
            }
        }

        int maxTime = -1;
        while (!que.isEmpty()) {
            int now = que.poll();
            result[now] += times[now];
            maxTime = Math.max(maxTime, result[now]);

            for (int next : tasks[now]) {
                result[next] = Math.max(result[now], result[next]);
                degree[next]--;
                if (degree[next] == 0) {
                    que.add(next);
                }
            }
        }

        System.out.println(maxTime);
    }
}
