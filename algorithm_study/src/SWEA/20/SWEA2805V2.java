import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA2805V2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            int N = Integer.parseInt(br.readLine());
            int answer = 0;
            int middle = N / 2;

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                // 다이아몬드 안에 들어오는 범위 구하기
                int start = Math.abs(middle - i);
                int end = N - start;

                // 범위의 값을 더하기
                for (int j = start; j < end; j++) {
                    answer += input.charAt(j) - '0';
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}