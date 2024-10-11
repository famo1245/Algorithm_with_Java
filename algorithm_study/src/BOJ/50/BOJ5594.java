import java.io.*;
import java.util.*;

public class BOJ5594 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] degree = new int[n + 1];
        List<Integer>[] rank = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            rank[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int win = Integer.parseInt(st.nextToken());
            int lose = Integer.parseInt(st.nextToken());

            rank[win].add(lose);
            degree[lose]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean hasMore = false;

        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                q.add(i);
            }
        }

        if (q.size() > 1) {
            hasMore = true;
        }

        while (!q.isEmpty()) {
            int team = q.poll();
            sb.append(team).append('\n');

            int originSize = q.size();
            for (int next : rank[team]) {
                degree[next]--;
                if (degree[next] == 0) {
                    q.add(next);
                }
            }

            if (!hasMore && q.size() - originSize > 1) {
                hasMore = true;
            }
        }

        System.out.print(sb);
        System.out.println(hasMore ? 1 : 0);
    }
}