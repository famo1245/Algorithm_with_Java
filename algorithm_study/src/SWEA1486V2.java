import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA1486V2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int[] H = new int[N];
            // 점원 키의 합의 최댓값
            int maxHeight = 10000 * N;
            boolean[] D = new boolean[maxHeight + 1];
            D[0] = true;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                int height = H[i];

                for (int j = maxHeight; j >= height; j--) {
                    if (D[j - height]) {
                        D[j] = true;
                    }
                }
            }

            int answer = 0;
            for (int i = B; i <= maxHeight; i++) {
                if (D[i]) {
                    answer = i - B;
                    break;
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}