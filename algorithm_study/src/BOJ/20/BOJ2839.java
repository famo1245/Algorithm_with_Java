import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        while (N >= 0) {
            // 5kg으로 담을 수 있는 경우
            if (N % 5 == 0) {
                answer += N / 5;
                break;
            }

            // 아니면 3kg으로 담기
            N -= 3;
            answer++;
        }

        if (N < 0) {
            answer = -1;
        }

        System.out.println(answer);
    }
}
