import java.util.*;
import java.io.*;

public class BOJ14891 {

    static final int NUM_OF_GEARS = 4;
    static final int NUM_OF_TOOTH = 8;
    static final boolean[][] NEAR = {{false, true, false, false}, {true, false, true, false}, {false, true, false, true},
            {false, false, true, false}};

    static int[][] gears;
    static int[] scoreIndex;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        gears = new int[NUM_OF_GEARS][NUM_OF_TOOTH];
        scoreIndex = new int[NUM_OF_GEARS];

        for (int i = 0; i < NUM_OF_GEARS; i++) {
            String input = br.readLine();
            for (int j = 0; j < NUM_OF_TOOTH; j++) {
                gears[i][j] = input.charAt(j) - '0';
            }

            scoreIndex[i] = 0;
        }

        int K = Integer.parseInt(br.readLine());
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

        System.out.println(answer);
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
