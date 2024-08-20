import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA1486 {

    static int N, B, answer;
    static int[] H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            H = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            answer = Integer.MAX_VALUE;
            getResult(0, 0);
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void getResult(int idx, int sum) {
        if (sum >= B) {
            answer = Math.min(answer, sum - B);
            return;
        }

        if (idx == N) {
            return;
        }

        // 현재 사람을 선택
        getResult(idx + 1, sum + H[idx]);
        // 선택 x
        getResult(idx + 1, sum);
    }
}
