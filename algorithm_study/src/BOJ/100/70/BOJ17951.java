import java.util.*;
import java.io.*;

public class BOJ17951 {

    static int[] papers;
    static int answer, K, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        papers = new int[N];

        st = new StringTokenizer(br.readLine());
        int maxScore = 0;
        int minScore = 20 * N;
        for (int i = 0; i < N; i++) {
            int score = Integer.parseInt(st.nextToken());
            papers[i] = score;
            maxScore += score;
            minScore = Math.min(minScore, score);
        }

        if (K == 1) {
            answer = maxScore;
        } else {
            answer = 0;
            calcMax(minScore, maxScore);
        }

        System.out.print(answer);
    }

    static boolean fn(int value) {
        int group = 0;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            sum += papers[i];
            if (sum >= value) {
                group++;
                sum = 0;
            }
        }

        return K <= group;
    }

    static void calcMax(int start, int end) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        if (fn(mid)) {
            answer = Math.max(answer, mid);
            calcMax(mid + 1, end);
        } else {
            calcMax(start, mid - 1);
        }
    }
}