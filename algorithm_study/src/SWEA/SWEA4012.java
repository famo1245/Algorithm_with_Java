import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4012 {

    static int N, answer;
    static int[][] S;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            N = Integer.parseInt(br.readLine());
            S = new int[N][N];
            selected = new boolean[N];


            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = Integer.MAX_VALUE;
            select(0, 0);
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void select(int count, int index) {
        // 재료를 반반 나누어 골랐을 때
        if (count == N / 2) {
            calcSynergy();
        }

        for (int i = index; i < N; i++) {
            selected[i] = true;
            select(count + 1, i + 1);
            selected[i] = false;
        }
    }

    static void calcSynergy() {
        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // selected true => A가 선택, false => B가 선택
                if (selected[i] && selected[j]) {
                    sumA += S[i][j];
                } else if (!selected[i] && !selected[j]) {
                    sumB += S[i][j];
                }
            }
        }

        int diff = Math.abs(sumA - sumB);
        answer = Math.min(answer, diff);
    }
}
