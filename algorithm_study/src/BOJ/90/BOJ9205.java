import java.util.*;
import java.io.*;

public class BOJ9205 {

    static final int MAX_DISTANCE = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] coord = new int[n + 2][2];
            List<Integer>[] map = new ArrayList[n + 2];

            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine());
                coord[i][0] = Integer.parseInt(st.nextToken());
                coord[i][1] = Integer.parseInt(st.nextToken());
                map[i] = new ArrayList<>();
            }

            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    if (i == j) {
                        continue;
                    }

                    int distance = Math.abs(coord[i][0] - coord[j][0]) + Math.abs(coord[i][1] - coord[j][1]);
                    if (distance <= MAX_DISTANCE) {
                        map[i].add(j);
                    }
                }
            }

            Queue<Integer> que = new ArrayDeque<>();
            boolean[] visited = new boolean[n + 2];
            boolean possible = false;
            que.add(0);
            visited[0] = true;

            while (!que.isEmpty()) {
                int now = que.poll();

                if (now == n + 1) {
                    possible = true;
                    break;
                }

                for (int to : map[now]) {
                    if (!visited[to]) {
                        visited[to] = true;
                        que.add(to);
                    }
                }
            }

            if (possible) {
                sb.append("happy");
            } else {
                sb.append("sad");
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }
}