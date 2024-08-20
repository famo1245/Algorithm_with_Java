import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA2805 {

    static int[] DX = {0, 1, 0, -1};
    static int[] DY = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            int N = Integer.parseInt(br.readLine());
            int[][] farm = new int[N][N];
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    farm[i][j] = Integer.parseInt(input[j]);
                }
            }


            int answer = 0;
            int start = Math.floorDiv(N, 2);
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
