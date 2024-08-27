import java.io.*;
import java.util.*;

public class SWEA2105 {

    static final int[][] OFFSET = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    static int N, answer, startRow, startCol;
    static int[][] map;
    static boolean[] usedDessert;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;
            for (int i = 0; i < N - 2; i++) {
                for (int j = 1; j < N - 1; j++) {
                    startRow = i;
                    startCol = j;
                    usedDessert = new boolean[101];
                    usedDessert[map[i][j]] = true;

                    dfs(i, j, 1, 0);
                }
            }

            if (answer == 0) {
                answer = -1;
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int row, int col, int count, int offset) {  // count 이동 , offset OFFSET 배열 시작 index
        for (int i = offset; i < OFFSET.length; i++) {
            int nextRow = row + OFFSET[i][0];
            int nextCol = col + OFFSET[i][1];

            if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N) {
                if ((nextRow == startRow) && (nextCol == startCol) && count > 2) {
                    answer = Math.max(answer, count);
                    return;
                }

                if (!usedDessert[map[nextRow][nextCol]]) {
                    usedDessert[map[nextRow][nextCol]] = true;
                    dfs(nextRow, nextCol, count + 1, i);
                    usedDessert[map[nextRow][nextCol]] = false;
                }
            }
        }
    }
}
