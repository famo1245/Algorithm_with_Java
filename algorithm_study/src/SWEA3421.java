import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA3421 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] impossible = new int[N + 1];
            int[] bits = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                bits[i] = 1 << (i - 1);
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                impossible[a] |= bits[b];
            }

            int answer = 0;
            int ingredients = 1 << N;
            for (int select = 0; select < ingredients; select++) {
                boolean isPossible = true;
                for (int i = 1; i <= N; i++) {
                    if (impossible[i] != 0) {
                        if ((select & bits[i]) != 0 && (select & impossible[i]) != 0) {
                            isPossible = false;
                            break;
                        }
                    }
                }

                if (isPossible) {
                    answer++;
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
