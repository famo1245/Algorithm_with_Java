import java.util.*;
import java.io.*;

public class BOJ9205V2 {

    static final int MAX_DISTANCE = 1000;

    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] coord = new int[n + 2][2];
            group = new int[n + 2];

            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine());
                coord[i][0] = Integer.parseInt(st.nextToken());
                coord[i][1] = Integer.parseInt(st.nextToken());
                group[i] = i;
            }

            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    if (i == j) {
                        continue;
                    }

                    int distance = Math.abs(coord[i][0] - coord[j][0]) + Math.abs(coord[i][1] - coord[j][1]);
                    if (distance <= MAX_DISTANCE) {
                        union(i, j);
                    }
                }
            }

            if (find(0) == find(n + 1)) {
                sb.append("happy");
            } else {
                sb.append("sad");
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }

    static int find(int x) {
        if (x == group[x]) {
            return x;
        }

        return group[x] = find(group[x]);
    }

    static void union(int a, int b) {
        if (a != group[a]) {
            a = find(a);
        }

        if (b != group[b]) {
            b = find(b);
        }

        if (a != b) {
            group[b] = a;
        }
    }
}