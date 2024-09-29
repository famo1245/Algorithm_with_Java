import java.io.*;
import java.util.*;

public class BOJ1654 {

    static long[] lines;
    static int K, N;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lines = new long[K];

        long sum = 0;
        for (int i = 0; i < K; i++) {
            int value = Integer.parseInt(br.readLine());
            sum += value;
            lines[i] = value;
        }

        long maxLength = sum / K;
        answer = Long.MIN_VALUE;
        calcLength(1L, maxLength);
        System.out.println(answer);
    }

    private static boolean fn(long lineLength) {
        long count = 0;
        for (int i = 0; i < K; i++) {
            count += lines[i] / lineLength;
        }

        return count >= N;
    }

    private static void calcLength(long min, long max) {
        if (min > max) {
            return;
        }

        long mid = (max + min) / 2;

        if (fn(mid)) {
            answer = Math.max(answer, mid);
            calcLength(mid + 1, max);
        } else {
            calcLength(min, mid - 1);
        }
    }
}
