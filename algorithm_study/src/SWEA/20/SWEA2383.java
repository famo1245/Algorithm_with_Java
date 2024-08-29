import java.util.*;
import java.io.*;

public class SWEA2383 {

    static final int HUMAN = 1;
    static final int MAX_HUMAN = 10;
    static final int STAIR_COUNT = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');

            int N = Integer.parseInt(br.readLine());
            Coordinate[] human = new Coordinate[MAX_HUMAN];
            Coordinate[] stairs = new Coordinate[STAIR_COUNT];

            int humanSize = 0;
            int stairIndex = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int element = Integer.parseInt(st.nextToken());

                    if (element == HUMAN) {
                        human[humanSize++] = new Coordinate(i, j, HUMAN);
                    } else if (element > HUMAN) {
                        stairs[stairIndex++] = new Coordinate(i, j, element);
                    }
                }
            }

            int maxPossible = 1 << humanSize;
            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < maxPossible; i++) {
                int[][] stairDistance = new int[STAIR_COUNT][MAX_HUMAN];
                int[] humanCount = new int[STAIR_COUNT];

                for (int j = 0; j < humanSize; j++) {
                    int stairNum = (i & 1 << j) > 0 ? 1 : 0;
                    stairDistance[stairNum][humanCount[stairNum]++] = Math.abs(human[j].row - stairs[stairNum].row)
                            + Math.abs(human[j].col - stairs[stairNum].col);
                }

                Arrays.sort(stairDistance[0], 0, humanCount[0]);
                Arrays.sort(stairDistance[1], 0, humanCount[1]);

                for (int j = 0; j < STAIR_COUNT; j++) {
                    for (int k = 0; k < Math.min(3, humanCount[j]); k++) {
                        stairDistance[j][k] += stairs[j].value + 1;
                    }

                    for (int k = 3; k < humanCount[j]; k++) {
                        stairDistance[j][k] = stairDistance[j][k - 3] > stairDistance[j][k] ?
                                stairDistance[j][k - 3] + stairs[j].value :
                                stairDistance[j][k] + stairs[j].value + 1;
                    }
                }

                if (humanCount[0] > 0 && humanCount[1] > 0) {
                    answer = Math.min(answer,
                            Math.max(stairDistance[0][humanCount[0] - 1], stairDistance[1][humanCount[1] - 1]));
                } else if (humanCount[0] > 0) {
                    answer = Math.min(answer, stairDistance[0][humanCount[0] - 1]);
                } else if (humanCount[1] > 0) {
                    answer = Math.min(answer, stairDistance[1][humanCount[1] - 1]);
                }
            }

            sb.append(answer).append('\n');
        }
        System.out.print(sb);
    }

    static class Coordinate {
        int row;
        int col;
        int value;

        public Coordinate(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
}
