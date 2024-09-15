import java.util.*;
import java.io.*;

public class BOJ17404 {

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] colors = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                colors[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int selected = 0; selected < 3; selected++) {
            int[][] D = new int[N][3];

            for (int i = 0; i < 3; i++) {
                if (i == selected) {
                    D[0][selected] = colors[0][selected];
                } else {
                    D[0][i] = INF;
                }
            }

            for (int i = 1; i < N; i++) {
                D[i][0] = colors[i][0] + Math.min(D[i - 1][1], D[i - 1][2]);
                D[i][1] = colors[i][1] + Math.min(D[i - 1][0], D[i - 1][2]);
                D[i][2] = colors[i][2] + Math.min(D[i - 1][1], D[i - 1][0]);
            }

            D[N - 1][selected] = INF;
            answer = Math.min(answer, Math.min(D[N - 1][0], Math.min(D[N - 1][1], D[N - 1][2])));
        }

        System.out.println(answer);
    }
}