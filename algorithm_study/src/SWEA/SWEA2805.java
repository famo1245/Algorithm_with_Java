import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            int N = Integer.parseInt(br.readLine());
            int[][] farm = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    farm[i][j] = Integer.parseInt(input[j]);
                }
            }

            int answer = 0;
            int middle = Math.floorDiv(N, 2);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int x = j - middle;
                    int y = middle - i;

                    if (y >= 0) {
                        if (x < 0) {
                            if (x + middle >= y) {
                                answer += farm[i][j];
                            }
                        } else {
                            if (-1 * x + middle >= y) {
                                answer += farm[i][j];
                            }
                        }
                    } else {
                        if (x < 0) {
                            if (-1 * x - middle <= y) {
                                answer += farm[i][j];
                            }
                        } else {
                            if (x - middle <= y) {
                                answer += farm[i][j];
                            }
                        }
                    }
                }
            }
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
