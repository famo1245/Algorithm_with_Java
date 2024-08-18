import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2001 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken()) - 1;
            int max = 0;
            int[][] table = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    if (i == 1) {
                        table[i][j] = table[i][j - 1] + Integer.parseInt(st.nextToken());
                    } else if (j == 1) {
                        table[i][j] = table[i - 1][j] + Integer.parseInt(st.nextToken());
                    } else {
                        table[i][j] = table[i][j - 1] + table[i - 1][j] - table[i - 1][j - 1] + Integer.parseInt(st.nextToken());
                    }
                }
            }

            if (N == M) {
                max = table[N][N];
            } else {
                for (int i = 1; i <= N - M; i++) {
                    for (int j = 1; j <= N - M; j++) {
                        int nextX = i + M;
                        int nextY = j + M;

                        int result = table[nextX][nextY] - table[i - 1][nextY] - table[nextX][j - 1] + table[i - 1][j - 1];
                        if (result > max) {
                            max = result;
                        }
                    }
                }
            }

            sb.append(max).append("\n");
        }

        System.out.println(sb);
    }
}
