import java.io.*;
import java.util.*;

public class BOJ2636 {

    static final int CHEESE = 1;
    static final int BLANK = 0;
    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int R, C, time, cheeseCount;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        time = 0;
        while(melt());
        sb.append(time).append('\n').append(cheeseCount).append('\n');
        System.out.print(sb);
    }

    static boolean melt() {
        if (!hasRemainCheese()) {
            return false;
        }

        int row = 0;
        int col = 0;
        cheeseCount = 0;
        Deque<int[]> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        que.add(new int[]{row, col});
        visited[row][col] = true;

        while (!que.isEmpty()) {
            int[] now = que.poll();
            int nowRow = now[0];
            int nowCol = now[1];

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = nowRow + OFFSET[i][0];
                int nextCol = nowCol + OFFSET[i][1];

                if (0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C && !visited[nextRow][nextCol]) {
                    if (board[nextRow][nextCol] == CHEESE) {
                        board[nextRow][nextCol] = BLANK;
                        cheeseCount++;
                    } else {
                        que.add(new int[]{nextRow, nextCol});
                    }

                    visited[nextRow][nextCol] = true;
                }
            }
        }

        time++;
        return true;
    }

    static boolean hasRemainCheese() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == CHEESE) {
                    return true;
                }
            }
        }

        return false;
    }
}