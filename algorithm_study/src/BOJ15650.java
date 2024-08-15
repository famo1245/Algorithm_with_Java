import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15650 {

    static StringBuilder sb;
    static int[] nums;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[M];

        getNums(1, 0);
        System.out.print(sb);
    }

    private static void getNums(int start, int count) {
        if (count == M) {
            for (int i = 0; i < M; i++) {
                sb.append(nums[i]).append(" ");
            }

            sb.append("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            nums[count] = i;
            getNums(i + 1, count + 1);
        }
    }
}
