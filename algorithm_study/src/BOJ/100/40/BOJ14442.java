import java.util.*;
import java.io.*;

public class BOJ14442 {

    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int INF = 987654321;
    static final int WALL = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][][] distance = new int[N][M][K + 1];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
                Arrays.fill(distance[i][j], INF);
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 1, K});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int row = now[0];
            int col = now[1];
            int hop = now[2];
            int breakCount = now[3];

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
                    if (map[nextRow][nextCol] == WALL) {
                        if (breakCount != 0 && distance[nextRow][nextCol][breakCount - 1] > hop + 1) {
                            distance[nextRow][nextCol][breakCount - 1] = hop + 1;
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
