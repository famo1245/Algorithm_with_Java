import java.io.*;
import java.util.*;

public class BOJ16933 {

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

        Queue<Route> q = new ArrayDeque<>();
        q.add(new Route(0, 0, 1, K, false, false));
        distance[0][0][K] = 1;
        while (!q.isEmpty()) {
            Route now = q.poll();

            if (now.row == N - 1 && now.col == M - 1) {
                System.out.println(now.hop);
                return;
            }

            if (!now.stayed && distance[now.row][now.col][now.breakCount] < now.hop) {
                continue;
            }

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = now.row + OFFSET[i][0];
                int nextCol = now.col + OFFSET[i][1];

                if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M) {
                    if (map[nextRow][nextCol] == WALL) {
                        if (now.breakCount != 0
                                && distance[nextRow][nextCol][now.breakCount - 1] > now.hop + 1) {
                            if (now.night) {
                                q.add(new Route(now.row, now.col, now.hop + 1,
                                        now.breakCount, false, true));
                            } else {
                                distance[nextRow][nextCol][now.breakCount - 1] = now.hop + 1;
                                q.add(new Route(nextRow, nextCol, now.hop + 1,
                                        now.breakCount - 1, true, false));
                            }
                        }
                    } else if (distance[nextRow][nextCol][now.breakCount] > now.hop + 1) {
                        distance[nextRow][nextCol][now.breakCount] = now.hop + 1;
                        q.add(new Route(nextRow, nextCol, now.hop + 1,
                                now.breakCount, !now.night, false));
                    }
                }
            }
        }

        System.out.println(-1);
    }

    static class Route {
        int row;
        int col;
        int hop;
        int breakCount;
        boolean night;
        boolean stayed;

        public Route(int row, int col, int hop, int breakCount, boolean night, boolean stayed) {
            this.row = row;
            this.col = col;
            this.hop = hop;
            this.breakCount = breakCount;
            this.night = night;
            this.stayed = stayed;
        }
    }
}
