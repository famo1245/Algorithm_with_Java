import java.util.*;
import java.io.*;

public class BOJ1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] colors = new int[N][3];
        int[][] D = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                colors[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        D[0] = colors[0];

        for (int i = 1; i < N; i++) {
            D[i][0] = colors[i][0] + Math.min(D[i - 1][1], D[i - 1][2]);
            D[i][1] = colors[i][1] + Math.min(D[i - 1][0], D[i - 1][2]);
            D[i][2] = colors[i][2] + Math.min(D[i - 1][1], D[i - 1][0]);
        }

        System.out.println(Math.min(D[N - 1][0], Math.min(D[N - 1][1], D[N - 1][2])));
    }
}