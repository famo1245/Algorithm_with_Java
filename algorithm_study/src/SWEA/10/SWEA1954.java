import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA1954 {

    public static void main(String[] args) throws IOException {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase < T + 1; testCase++) {
            sb.append("#").append(testCase).append("\n");
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            int x = 0, y = 0;
            int direction = 0;

            for (int i = 1; i <= N * N; i++) {
                arr[x][y] = i;

                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || arr[nx][ny] != 0) {
                    direction = (direction + 1) % 4;
                    nx = x + dx[direction];
                    ny = y + dy[direction];
                }

                x = nx;
                y = ny;
            }

            for (int[] line : arr) {
                for (int e : line) {
                    sb.append(e).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
