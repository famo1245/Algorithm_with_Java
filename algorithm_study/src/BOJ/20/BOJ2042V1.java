import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2042V1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 입력의 범위가 -2^63~ 2^63-1
        long[] nums = new long[N];
        long[] sum = new long[N + 1];
        sum[0] = 0;

        // 누적합을 구하기
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
            if (i == 0) {
                sum[i + 1] = nums[i];
            } else {
                sum[i + 1] = nums[i] + sum[i];
            }

        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                int index = Integer.parseInt(st.nextToken()) - 1;
                long changedNum = Long.parseLong(st.nextToken());
                long diff = changedNum - nums[index];
                nums[index] = changedNum;

                // 누적합 변경
                for (int j = index + 1; j < N + 1; j++) {
                    sum[j] += diff;
                }
            } else {    // 2
                // 시작과 끝 인덱스
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                sb.append(sum[end] - sum[start - 1]).append("\n");
            }
        }

        System.out.println(sb);
    }
}
