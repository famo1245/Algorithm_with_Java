import java.util.*;
import java.io.*;

public class BOJ2206 {

    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int[][][] distance = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
                distance[i][j][0] = Integer.MAX_VALUE;
                distance[i][j][1] = Integer.MAX_VALUE;
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        // row, col, hop, break
        q.add(new int[]{0, 0, 1, 1});
        while (!q.isEmpty()) {
            int[] info = q.poll();
            int row = info[0];
            int col = info[1];
            int hop = info[2];
            int breakCount = info[3];

            if (row == N - 1 && col == M - 1) {
                System.out.println(hop);
                return;
            }

            if (distance[row][col][breakCount] < hop) {
                continue;
            }

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = row + OFFSET[i][0];
                int nextCol = col + OFFSET[i][1];

                if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M) {
                    if (map[nextRow][nextCol] == 1) {
                        if (breakCount != 0 && distance[nextRow][nextCol][breakCount] > hop + 1) {
                            distance[nextRow][nextCol][breakCount] = hop + 1;
                            q.add(new int[]{nextRow, nextCol, hop + 1, breakCount - 1});
                        }
                    } else if (distance[nextRow][nextCol][breakCount] > hop + 1) {
                        distance[nextRow][nextCol][breakCount] = hop + 1;
                        q.add(new int[]{nextRow, nextCol, hop + 1, breakCount});
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
