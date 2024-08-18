import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215V2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[] tastes = new int[N + 1];
            int[] calories = new int[N + 1];
            int[][] D = new int[N + 1][L + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                tastes[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= L; j++) {
                    if (calories[i] <= j) {
                        D[i][j] = Math.max(D[i - 1][j], D[i - 1][j - calories[i]] + tastes[i]);
                    } else {
                        D[i][j] = D[i - 1][j];
                    }
                }
            }

            sb.append(D[N][L]).append("\n");
        }

        System.out.println(sb);
    }
}
