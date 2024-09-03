import java.util.*;
import java.io.*;

public class BOJ1600 {

    static final int INF = 500000;
    static final int PLAIN = 0;
    static final int[][] OFFSET = {{-2, -1}, {-1, -2}, {-1, 2}, {-2, 1}, {1, -2}, {1, 2}, {2, -1}, {2, 1},
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int W, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        int[][] board = new int[H][W];
        int[][][] distance = new int[H][W][K + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                for (int k = 0; k <= K; k++) {
                    distance[i][j][k] = INF;
                }
            }
        }

        int minHopCount = Integer.MAX_VALUE;
        ArrayDeque<int[]> que = new ArrayDeque<>();
        // row, col, remain jump count
        que.add(new int[]{0, 0, K});
        distance[0][0][K] = 0;
        while (!que.isEmpty()) {
            int[] data = que.poll();
            int row = data[0];
            int col = data[1];
            int jump = data[2];

            if (row == H - 1 && col == W - 1) {
                minHopCount = Math.min(minHopCount, distance[row][col][jump]);
                continue;
            }

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = row + OFFSET[i][0];
                int nextCol = col + OFFSET[i][1];

                if (0 <= nextRow && nextRow < H && 0 <= nextCol && nextCol < W && board[nextRow][nextCol] == PLAIN) {
                    if (i < 8) {
                        if (jump > 0 && distance[nextRow][nextCol][jump - 1] > distance[row][col][jump] + 1) {
                            distance[nextRow][nextCol][jump - 1] = distance[row][col][jump] + 1;
                            que.add(new int[]{nextRow, nextCol, jump - 1});
                        }
                    } else {
                        if (distance[nextRow][nextCol][jump] > distance[row][col][jump] + 1) {
                            distance[nextRow][nextCol][jump] = distance[row][col][jump] + 1;
                            que.add(new int[]{nextRow, nextCol, jump});
                        }
                    }
                }
            }
        }

        System.out.println(minHopCount == Integer.MAX_VALUE ? -1 : minHopCount);
    }
}
