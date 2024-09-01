import java.io.*;
import java.util.*;

public class BOJ2178 {

    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] maze = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = input.charAt(j) - '0';
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = now[0] + OFFSET[i][0];
                int nextCol = now[1] + OFFSET[i][1];

                if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M && !visited[nextRow][nextCol]
                        && maze[nextRow][nextCol] != 0) {
                    visited[nextRow][nextCol] = true;
                    maze[nextRow][nextCol] = maze[now[0]][now[1]] + 1;
                    q.add(new int[]{nextRow, nextCol});
                }
            }
        }

        System.out.println(maze[N - 1][M - 1]);
    }
}
