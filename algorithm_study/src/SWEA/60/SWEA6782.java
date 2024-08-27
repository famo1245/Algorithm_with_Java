import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA6782 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            long N = Long.parseLong(br.readLine());
            long count = 0;

            while (N != 2) {
                double root = Math.sqrt(N);
                // 제곱근이 정수인지 확인
                if ((long) root == root) {
                    count++;
                    N = (long) root;
                } else {    // 제곱근이 정수가 아니라면, 가까운 정수를 구함
                    long add = (long) (Math.sqrt(N) + 1);
                    long square = add * add;
                    count += square - N;
                    N = square;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}
