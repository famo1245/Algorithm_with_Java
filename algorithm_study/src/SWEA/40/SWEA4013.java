import java.util.*;
import java.io.*;

public class SWEA4013 {

    static final int NUM_OF_GEARS = 4;
    static final int NUM_OF_TOOTH = 8;
    static final boolean[][] NEAR = {{false, true, false, false},{true, false, true, false},{false, true, false, true},
            {false, false, true, false}};

    static int[][] gears;
    static int[] scoreIndex;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            int K = Integer.parseInt(br.readLine());
            gears = new int[NUM_OF_GEARS][NUM_OF_TOOTH];
            scoreIndex = new int[NUM_OF_GEARS];

            for (int i = 0; i < NUM_OF_GEARS; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < NUM_OF_TOOTH; j++) {
                    gears[i][j] = Integer.parseInt(st.nextToken());
                }
                scoreIndex[i] = 0;
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken()) - 1;
                int direction = Integer.parseInt(st.nextToken());
                checked = new boolean[NUM_OF_GEARS];
                checked[num] = true;
                rotate(num, direction);
            }

            int answer = 0;
            int score = 1;
            for (int i = 0; i < NUM_OF_GEARS; i++) {
                answer += gears[i][scoreIndex[i]] * score;
                score *= 2;
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void rotate(int num, int direction) {
        for (int i = 0; i < NUM_OF_GEARS; i++) {
            if (!NEAR[num][i] || checked[i]) {
                continue;
            }

            checked[i] = true;
            if (num < i) {
                if (gears[num][(scoreIndex[num] + 2) % 8] != gears[i][(scoreIndex[i] + 6) % 8]) {
                    rotate(i, -direction);
                }
            } else {
                if (gears[num][(scoreIndex[num] + 6) % 8] != gears[i][(scoreIndex[i] + 2) % 8]) {
                    rotate(i, -direction);
                }
            }
        }

        scoreIndex[num] = (scoreIndex[num] - direction + 8) % 8;
    }
}
