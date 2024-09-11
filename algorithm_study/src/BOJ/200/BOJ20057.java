import java.util.*;
import java.io.*;

public class BOJ20057 {

    // 토네이도 이동 좌, 하, 우, 상
    static final int[][] MOVE = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static final int BLANK = 0;
    static final int TURN_MAX = 2;
    // dr, dc, rate idx
    static final int[][][] BLOW_NEXT = {{{-1, 0, 4}, {1, 0, 4}}, {{0, -1, 4}, {0, 1, 4}}};
    static final int[][][] BLOW_NOW = {{{-1, 0, 3}, {1, 0, 3}, {-2, 0, 1}, {2, 0, 1}},
            {{0, -1, 3}, {0, 1, 3}, {0, -2, 1}, {0, 2, 1}}};
    static final int[][][] BLOW_BACK = {{{1, 0}, {-1, 0}}, {{0, -1}, {0, 1}}};
    static final double[] BLOW_RATE = {0.01, 0.02, 0.05, 0.07, 0.1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int row = N / 2;
        int col = N / 2;
        int moveCount = 0;
        int countMax = 1;
        int turnCount = 0;
        int offsetIndex = 0;
        int answer = 0;

        while (true) {
            if (row == 0 && col == 0) {
                break;
            }

            //move
            row += MOVE[offsetIndex][0];
            col += MOVE[offsetIndex][1];

            if (map[row][col] != BLANK) {
                int nextRow = row + MOVE[offsetIndex][0];
                int nextCol = col + MOVE[offsetIndex][1];
                // blow
                int blowRow;
                int blowCol;
                int sand;
                int blewSand = 0;
                // next
                blowRow = nextRow + MOVE[offsetIndex][0];
                blowCol = nextCol + MOVE[offsetIndex][1];
                sand = (int) Math.floor(map[row][col] * BLOW_RATE[2]);
                blewSand += sand;
                if (isValid(blowRow, blowCol, N)) {
                    map[blowRow][blowCol] += sand;
                } else {
                    answer += sand;
                }

                for (int i = 0; i < BLOW_NEXT[offsetIndex % 2].length; i++) {
                    blowRow = nextRow + BLOW_NEXT[offsetIndex % 2][i][0];
                    blowCol = nextCol + BLOW_NEXT[offsetIndex % 2][i][1];
                    sand = (int) Math.floor(map[row][col] * BLOW_RATE[BLOW_NEXT[offsetIndex % 2][i][2]]);
                    blewSand += sand;
                    if (isValid(blowRow, blowCol, N)) {
                        map[blowRow][blowCol] += sand;
                    } else {
                        answer += sand;
                    }
                }
                // now
                for (int i = 0; i < BLOW_NOW[offsetIndex % 2].length; i++) {
                    blowRow = row + BLOW_NOW[offsetIndex % 2][i][0];
                    blowCol = col + BLOW_NOW[offsetIndex % 2][i][1];
                    sand = (int) Math.floor(map[row][col] * BLOW_RATE[BLOW_NOW[offsetIndex % 2][i][2]]);
                    blewSand += sand;
                    if (isValid(blowRow, blowCol, N)) {
                        map[blowRow][blowCol] += sand;
                    } else {
                        answer += sand;
                    }
                }
                // back
                for (int i = 0; i < BLOW_BACK[offsetIndex % 2].length; i++) {
                    blowRow = row + BLOW_BACK[offsetIndex % 2][i][0] - MOVE[offsetIndex][0];
                    blowCol = col + BLOW_BACK[offsetIndex % 2][i][1] - MOVE[offsetIndex][1];
                    sand = (int) Math.floor(map[row][col] * BLOW_RATE[0]);
                    blewSand += sand;
                    if (isValid(blowRow, blowCol, N)) {
                        map[blowRow][blowCol] += sand;
                    } else {
                        answer += sand;
                    }
                }

                // last
                blowRow = nextRow;
                blowCol = nextCol;
                sand = map[row][col] - blewSand;
                if (isValid(blowRow, blowCol, N)) {
                    map[blowRow][blowCol] += sand;
                } else {
                    answer += sand;
                }

                map[row][col] = 0;

            }

            moveCount++;
            if (moveCount == countMax) {
                turnCount++;
                offsetIndex = (offsetIndex + 1) % MOVE.length;
                moveCount = 0;
            }

            if (turnCount == TURN_MAX) {
                countMax++;
                turnCount = 0;
            }
        }

        System.out.println(answer);
    }

    static boolean isValid(int r, int c, int N) {
        boolean validRow = 0 <= r && r < N;
        boolean validCol = 0 <= c && c < N;
        return validRow && validCol;
    }
}
