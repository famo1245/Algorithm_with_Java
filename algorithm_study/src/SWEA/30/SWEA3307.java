import java.util.*;
import java.io.*;

public class SWEA3307 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            int N = Integer.parseInt(br.readLine());
            int[] sequence = new int[N + 1];
            int[] D = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                sequence[i] = Integer.parseInt(st.nextToken());
                D[i] = 1;
            }

            int answer = 1;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j < i; j++) {
                    if (sequence[i] > sequence[j]) {
                        D[i] = Math.max(D[i], D[j] + 1);
                    }
                }

                answer = Math.max(answer, D[i]);
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
