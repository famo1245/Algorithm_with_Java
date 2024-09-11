import java.io.*;
import java.util.*;

public class BOJ1520 {

    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int[][] map, possibility;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        possibility = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                possibility[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0, map[0][0]));
    }

    static int dfs(int row, int col, int beforeHeight) {
        if (row == N - 1 && col == M - 1) {
            return 1;
        }

        if (possibility[row][col] != -1) {
            return possibility[row][col];
        }

        possibility[row][col] = 0;
        for (int i = 0; i < OFFSET.length; i++) {
            int nextRow = row + OFFSET[i][0];
            int nextCol = col + OFFSET[i][1];

            if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M) {
                if (map[nextRow][nextCol] < beforeHeight) {
                    possibility[row][col] += dfs(nextRow, nextCol, map[nextRow][nextCol]);
                }
            }
        }

        return possibility[row][col];
    }
}