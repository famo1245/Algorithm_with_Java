import java.io.*;
import java.util.*;

public class SWEA1767 {

    static final int CORE = 1;
    static final int MAX_SIZE = 12;
    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int[][] pcb, cores;
    static int N, coresSize, minLength, maxCores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            N = Integer.parseInt(br.readLine());

            pcb = new int[N][N];
            cores = new int[MAX_SIZE][];
            coresSize = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int element = Integer.parseInt(st.nextToken());
                    pcb[i][j] = element;

                    if (element == CORE && i != 0 && j != 0 && i != N - 1 && j != N - 1) {
                        cores[coresSize++] = new int[]{i, j};
                    }
                }
            }

            minLength = Integer.MAX_VALUE;
            maxCores = Integer.MIN_VALUE;
            dfs(0, 0, 0);

            sb.append(minLength).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int index, int coreCount, int wireLength) {
        if (index == coresSize) {
            if (maxCores < coreCount) {
                maxCores = coreCount;
                minLength = wireLength;
            } else if (maxCores == coreCount) {
                minLength = Math.min(minLength, wireLength);
            }
            return;
        }

        int row = cores[index][0];
        int col = cores[index][1];

        for (int i = 0; i < OFFSET.length; i++) {
            int length = 0;
            int nextRow = row;
            int nextCol = col;

            while (true) {
                nextRow += OFFSET[i][0];
                nextCol += OFFSET[i][1];

                if (N <= nextRow || nextRow < 0 || N <= nextCol || nextCol < 0) {
                    break;
                }

                if (pcb[nextRow][nextCol] == CORE) {
                    length = 0;
                    break;
                }

                length++;
            }


            if (length == 0) {
                dfs(index + 1, coreCount, wireLength);
            } else {
                fill(row, col, i, length, CORE);
                dfs(index + 1, coreCount + 1, wireLength + length);
                fill(row, col, i, length, 0);
            }
        }
    }

    static void fill(int row, int col, int direction, int max, int value) {
        for (int i = 0; i < max; i++) {
            row += OFFSET[direction][0];
            col += OFFSET[direction][1];
            pcb[row][col] = value;
        }
    }
}
