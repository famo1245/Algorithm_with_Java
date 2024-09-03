import java.util.*;
import java.io.*;

public class SWEA1953 {

    static final int BLANK = 0;
    static final int PLUS = 1;
    static final int VERTICAL = 2;
    static final int HORIZONTAL = 3;
    static final int UPPER_RIGHT = 4;
    static final int LOWER_RIGHT = 5;
    static final int LOWER_LEFT = 6;
    static final int UPPER_LEFT = 7;
    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final String[] possible = {"", "0123", "02", "13", "03", "23", "12", "01"};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][M];
            boolean[][] visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Queue<int[]> q = new ArrayDeque<>();
            int count = 1;
            q.add(new int[]{R, C, 1});
            visited[R][C] = true;
            while (!q.isEmpty()) {
                int[] now = q.poll();
                int row = now[0];
                int col = now[1];
                int time = now[2];

                if (time == L) {
                    break;
                }

                int currentPipe = map[row][col];
                for (int i = 0; i < OFFSET.length; i++) {
                    int nextRow = row + OFFSET[i][0];
                    int nextCol = col + OFFSET[i][1];

                    if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M && !visited[nextRow][nextCol]
                            && map[nextRow][nextCol] != BLANK) {
                        int nextPipe = map[nextRow][nextCol];
                        if (!possible[nextPipe].contains(Integer.toString(i))) {
                            continue;
                        }
                        if (i == 0) {
                            if (currentPipe == PLUS || currentPipe == VERTICAL || currentPipe == UPPER_RIGHT
                                    || currentPipe == UPPER_LEFT) {
                                if (nextPipe == PLUS || nextPipe == VERTICAL || nextPipe == LOWER_RIGHT
                                        || nextPipe == LOWER_LEFT) {
                                    q.add(new int[]{nextRow, nextCol, time + 1});
                                    visited[nextRow][nextCol] = true;
                                    count++;
                                }
                            }
                        } else if (i == 1) {
                            if (currentPipe == PLUS || currentPipe == VERTICAL || currentPipe == LOWER_RIGHT
                                    || currentPipe == LOWER_LEFT) {
                                if (nextPipe == PLUS || nextPipe == VERTICAL || nextPipe == UPPER_RIGHT
                                        || nextPipe == UPPER_LEFT) {
                                    q.add(new int[]{nextRow, nextCol, time + 1});
                                    visited[nextRow][nextCol] = true;
                                    count++;
                                }
                            }
                        } else if (i == 2) {
                            if (currentPipe == PLUS || currentPipe == HORIZONTAL || currentPipe == UPPER_LEFT
                                    || currentPipe == LOWER_LEFT) {
                                if (nextPipe == PLUS || nextPipe == HORIZONTAL || nextPipe == UPPER_RIGHT
                                        || nextPipe == LOWER_RIGHT) {
                                    q.add(new int[]{nextRow, nextCol, time + 1});
                                    visited[nextRow][nextCol] = true;
                                    count++;
                                }
                            }
                        } else {
                            if (currentPipe == PLUS || currentPipe == HORIZONTAL || currentPipe == UPPER_RIGHT
                                    || currentPipe == LOWER_RIGHT) {
                                if (nextPipe == PLUS || nextPipe == VERTICAL || nextPipe == UPPER_LEFT
                                        || nextPipe == LOWER_LEFT) {
                                    q.add(new int[]{nextRow, nextCol, time + 1});
                                    visited[nextRow][nextCol] = true;
                                    count++;
                                }
                            }
                        }
                    }
                }
            }

            sb.append(count).append('\n');
        }
        System.out.print(sb);
    }
}
