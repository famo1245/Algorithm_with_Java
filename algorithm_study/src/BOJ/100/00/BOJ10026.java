import java.util.*;
import java.io.*;

public class BOJ10026 {

    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final char GREEN = 'G';
    static final char RED = 'R';

    static int N;
    static char[][] picture;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        picture = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                picture[i][j] = input.charAt(j);
            }
        }

        int noneBlind = 0;
        int colorBlind = 0;
        visited = new boolean[N][N];
        // 색약x
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    noneBlind++;
                }

                if (picture[i][j] == GREEN) {
                    picture[i][j] = RED;
                }
            }
        }

        visited = new boolean[N][N];
        // 색약
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    colorBlind++;
                }
            }
        }

        sb.append(noneBlind).append(' ').append(colorBlind);
        System.out.println(sb);
    }

    static void bfs(int row, int col) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {row, col});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];

            if (visited[r][c]) {
                continue;
            }

            visited[r][c] = true;
            char value = picture[r][c];

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = r + OFFSET[i][0];
                int nextCol = c + OFFSET[i][1];

                if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N
                        && !visited[nextRow][nextCol]) {
                    if (value == picture[nextRow][nextCol]) {
                        q.add(new int[] {nextRow, nextCol});
                    }
                }
            }
        }
    }
}