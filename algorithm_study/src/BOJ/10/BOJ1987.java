import java.io.*;
import java.util.*;

public class BOJ1987 {

    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int R, C, answer;
    static char[][] board;
    static boolean[] passed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = input.charAt(j);
                board[i][j] = c;
            }
        }

        passed = new boolean['Z' - 'A' + 1];
        answer = 0;
        passed[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(answer);
    }

    static void dfs(int row, int col, int hop) {
        for (int i = 0; i < OFFSET.length; i++) {
            int nextRow = row + OFFSET[i][0];
            int nextCol = col + OFFSET[i][1];

            if (0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C) {
                char next = board[nextRow][nextCol];
                if (!passed[next - 'A']) {
                    passed[next - 'A'] = true;
                    dfs(nextRow, nextCol, hop + 1);
                    passed[next - 'A'] = false;
                }
            }
        }
        answer = Math.max(answer, hop);
    }
}
