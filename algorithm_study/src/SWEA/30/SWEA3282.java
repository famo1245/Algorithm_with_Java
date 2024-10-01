import java.util.*;
import java.io.*;

public class SWEA3282 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] values = new int[N + 1];
            int[] volumes = new int[N + 1];
            int[][] D = new int[N + 1][K + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                volumes[i] = Integer.parseInt(st.nextToken());
                values[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= K; j++) {
                    if (volumes[i] <= j) {
                        // 선택
                        D[i][j] = Math.max(D[i - 1][j], D[i - 1][j - volumes[i]] + values[i]);
                    } else {
                        // 비선택
                        D[i][j] = D[i - 1][j];
                    }
                }
            }

            sb.append(D[N][K]).append('\n');
        }

        System.out.print(sb);
    }
}