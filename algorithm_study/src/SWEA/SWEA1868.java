import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class SWEA1868 {

    static final int MINE = '*';
    static final int LAND = '.';
    // 상, 하, 좌, 우, 우상, 좌상, 우하, 좌하
    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {-1, -1},
            {1, 1}, {1, -1}};

    static int[][] map;
    static int N, answer;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = input.charAt(j);

                    if (input.charAt(j) == MINE) {
                        visited[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == LAND) {
                        markMap(i, j);
                    }
                }
            }

            answer = 0;
            // 0을 선택하는 경우 처리
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0 && !visited[i][j]) {
                        travelMap(i, j);
                    }
                }
            }

            // 나머지 처리
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        answer++;
                    }
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void markMap(int row, int col) {
        int mineCount = 0;
        for (int i = 0; i < OFFSET.length; i++) {
            int nextRow = row + OFFSET[i][0];
            int nextCol = col + OFFSET[i][1];

            if (nextRow < N && nextRow >= 0 && nextCol < N && nextCol >= 0) {
                if (map[nextRow][nextCol] == MINE) {
                    mineCount++;
                }
            }
        }

        map[row][col] = mineCount;
    }

    static void travelMap(int row, int col) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{row, col});
        visited[row][col] = true;

        while (!que.isEmpty()) {
            int[] now = que.poll();
            int nowRow = now[0];
            int nowCol = now[1];

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = nowRow + OFFSET[i][0];
                int nextCol = nowCol + OFFSET[i][1];

                if (nextRow < N && nextRow >= 0 && nextCol < N && nextCol >= 0) {
                    if (map[nextRow][nextCol] != MINE) {
                        if (map[nextRow][nextCol] == 0 && !visited[nextRow][nextCol]) {
                            que.add(new int[]{nextRow, nextCol});
                        }

                        visited[nextRow][nextCol] = true;
                    }
                }

            }
        }

        answer++;
    }
}
