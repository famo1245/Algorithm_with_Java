import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class SWEA1210 {

    static final int SIZE = 100;
    static final int UP = 0;
    static final int LEFT = -1;
    static final int RIGHT = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int testCase = 0; testCase < 10; testCase++) {
            int T = Integer.parseInt(br.readLine());
            int[][] G = new int[SIZE][SIZE];

            int startRow = 0;
            int startCol = 0;
            for (int i = 0; i < SIZE; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < SIZE; j++) {
                    G[i][j] = Integer.parseInt(st.nextToken());
                    if (G[i][j] == 2) {
                        startRow = i;
                        startCol = j;
                    }
                }
            }

            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.add(new int[]{startRow, startCol, UP});
            int row = 0;
            int col = 0;
            while (!q.isEmpty()) {
                int[] temp = q.pollFirst();
                row = temp[0];
                col = temp[1];
                int direction = temp[2];

                if (row != 0 && row != SIZE - 1) {
                    if (direction == 0 || direction == LEFT) {
                        if (col - 1 >= 0 && G[row][col - 1] == 1) {
                            q.add(new int[]{row, col - 1, LEFT});
                            continue;
                        }
                    }

                    if (direction == 0 || direction == RIGHT) {
                        if (col + 1 < SIZE && G[row][col + 1] == 1) {
                            q.add(new int[]{row, col + 1, RIGHT});
                            continue;
                        }
                    }
                }

                if (row - 1 >= 0) {
                    if (G[row - 1][col] == 1) {
                        q.add(new int[]{row - 1, col, UP});
                    }
                }
            }

            sb.append("#").append(T).append(" ").append(col).append("\n");
        }
        System.out.println(sb);
    }
}
