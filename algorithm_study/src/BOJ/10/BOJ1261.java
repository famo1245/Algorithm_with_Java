import java.util.*;
import java.io.*;


public class BOJ1261 {

    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int WALL = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] maze = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = input.charAt(j) - '0';
            }
        }

        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0, 0});
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int row = now[0];
            int col = now[1];
            int breakCount = now[2];

            if (row == N - 1 && col == M - 1) {
                System.out.println(breakCount);
                return;
            }

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = row + OFFSET[i][0];
                int nextCol = col + OFFSET[i][1];

                if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M
                        && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    if (maze[nextRow][nextCol] == WALL) {
                        q.addLast(new int[] {nextRow, nextCol, breakCount + 1});
                    } else {
                        q.addFirst(new int[] {nextRow, nextCol, breakCount});
                    }
                }
            }
        }

        System.out.println(-1);
    }
}