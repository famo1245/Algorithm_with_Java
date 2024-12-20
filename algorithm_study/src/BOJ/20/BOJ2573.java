import java.io.*;
import java.util.*;

public class BOJ2573 {

    private static final int SEA = 0;
    private static final int[][] OFFSET = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static int[][] MAP;
    private static int N, M, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAP = new int[N][M];
        count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                int block = Integer.parseInt(st.nextToken());
                MAP[i][j] = block;

                if (block != SEA) {
                    count++;
                }
            }
        }

        int year = 0;
        while (count > 0) {
            melt();
            year++;

            if (isDivided()) {
                System.out.println(year);
                return;
            }
        }

        System.out.println(0);
    }

    private static void melt() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            temp[i] = MAP[i].clone();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] != SEA) {
                    int seaCount = 0;

                    for (int k = 0; k < OFFSET.length; k++) {
                        int nr = i + OFFSET[k][0];
                        int nc = j + OFFSET[k][1];

                        if (nr >= 0 && nr < N && nc >= 0 && nc < M && temp[nr][nc] == SEA) {
                            seaCount++;
                        }
                    }

                    MAP[i][j] = Math.max(0, temp[i][j] - seaCount);
                    if (temp[i][j] > 0 && MAP[i][j] == 0) {
                        count--;
                    }
                }
            }
        }
    }

    private static boolean isDivided() {
        boolean[][] visited = new boolean[N][M];
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (MAP[i][j] != SEA && !visited[i][j]) {
                    count++;

                    if (count > 1) {
                        return true;
                    }

                    bfs(i, j, visited);
                }
            }
        }

        return false;
    }

    private static void bfs(int r, int c, boolean[][] visited) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int row = now[0];
            int col = now[1];

            if (visited[row][col]) {
                continue;
            }

            visited[row][col] = true;

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = row + OFFSET[i][0];
                int nextCol = col + OFFSET[i][1];

                if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M && !visited[nextRow][nextCol]) {
                    if (MAP[nextRow][nextCol] != SEA) {
                        q.add(new int[]{nextRow, nextCol});
                    }
                }
            }
        }
    }
}