import java.util.*;
import java.io.*;

public class SWEA1949 {

    static final int[][] OFFSET = {{-1, 0},{1, 0},{0, -1},{0, 1}};

    static int[][] mountains, peakMountains;
    static boolean[][] visited;
    static int N, K, peekCount, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            mountains = new int[N][N];
            peakMountains = new int[5][2];
            visited = new boolean[N][N];

            int maxHeight = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int height = Integer.parseInt(st.nextToken());
                    mountains[i][j] = height;

                    if (maxHeight < height) {
                        maxHeight = height;
                    }
                }
            }

            peekCount = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mountains[i][j] == maxHeight) {
                        peakMountains[peekCount][0] = i;
                        peakMountains[peekCount++][1] = j;
                    }
                }
            }

            answer = 0;
            for (int i = 0; i < peekCount; i++) {
                int row = peakMountains[i][0];
                int col = peakMountains[i][1];
                visited[row][col] = true;
                dfs(row, col, 1, K, maxHeight);
                visited[row][col] = false;
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int row, int col, int depth, int cut, int beforeHeight) {
        for (int i = 0; i < OFFSET.length; i++) {
            int nextRow = row + OFFSET[i][0];
            int nextCol = col + OFFSET[i][1];

            if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N && !visited[nextRow][nextCol]) {
                int height = mountains[nextRow][nextCol];
                int heightDiff = height - beforeHeight + 1;
                if (beforeHeight <= height && K == cut && heightDiff <= cut) {
                    visited[nextRow][nextCol] = true;
                    dfs(nextRow, nextCol, depth + 1, cut - (height - beforeHeight + 1), beforeHeight - 1);
                    visited[nextRow][nextCol] = false;
                } else if (beforeHeight > height) {
                    visited[nextRow][nextCol] = true;
                    dfs(nextRow, nextCol, depth + 1, cut, height);
                    visited[nextRow][nextCol] = false;
                }
            }
        }

        if (answer < depth) {
            answer = depth;
        }
    }
}
