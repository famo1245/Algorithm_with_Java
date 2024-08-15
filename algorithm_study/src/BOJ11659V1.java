import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11659V1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] sum = new int[N + 1];
        sum[0] = 0;

        // 누적합을 구하기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (i == 0) {
                sum[i + 1] = num;
            } else {
                sum[i + 1] = num + sum[i];
            }

        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 시작과 끝 인덱스
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(sum[end] - sum[start - 1]).append("\n");

        }

        System.out.println(sb);
    }
}
