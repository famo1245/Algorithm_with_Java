import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1873 {

    static final char PLAIN = '.';
    static final char BRICK = '*';
    static final char STEEL = '#';
    static final char UP = '^';
    static final char DOWN = 'v';
    static final char LEFT = '<';
    static final char RIGHT = '>';

    static char[][] map;
    static int H, W, tankRow, tankCol;
    static char tankDirection;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(' ');

            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];

            String input;
            char current;
            for (int i = 0; i < H; i++) {
                input = br.readLine();
                for (int j = 0; j < W; j++) {
                    current = input.charAt(j);
                    map[i][j] = current;

                    if (current == UP || current == DOWN || current == RIGHT || current == LEFT) {
                        tankRow = i;
                        tankCol = j;
                        tankDirection = current;
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String command = br.readLine();

            for (int i = 0; i < N; i++) {
                current = command.charAt(i);
                if (current == 'S') {
                    shoot();
                } else {
                    move(current);
                }
            }

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }

    static void shoot() {
        int offset;
        if (tankDirection == UP || tankDirection == DOWN) {
            if (tankDirection == UP) {
                offset = -1;
            } else {
                offset = 1;
            }

            for (int i = 1; tankRow + offset * i < H && tankRow + offset * i >= 0; i++) {
                int nextRow = tankRow + offset * i;
                if (map[nextRow][tankCol] == BRICK || map[nextRow][tankCol] == STEEL) {
                    if (map[nextRow][tankCol] == BRICK) {
                        map[nextRow][tankCol] = PLAIN;
                    }
                    break;
                }
            }
        } else {
            if (tankDirection == LEFT) {
                offset = -1;
            } else {
                offset = 1;
            }

            for (int i = 1; tankCol + offset * i < W && tankCol + offset * i >= 0; i++) {
                int nextCol = tankCol + offset * i;
                if (map[tankRow][nextCol] == BRICK || map[tankRow][nextCol] == STEEL) {
                    if (map[tankRow][nextCol] == BRICK) {
                        map[tankRow][nextCol] = PLAIN;
                    }
                    break;
                }
            }
        }
    }

    static void move(char direction) {
        if (direction == 'U') {
            tankDirection = UP;

            if (tankRow - 1 >= 0 && map[tankRow - 1][tankCol] == PLAIN) {
                map[tankRow--][tankCol] = PLAIN;
            }
        } else if (direction == 'D') {
            tankDirection = DOWN;

            if (tankRow + 1 < H && map[tankRow + 1][tankCol] == PLAIN) {
                map[tankRow++][tankCol] = PLAIN;
            }
        } else if (direction == 'R') {
            tankDirection = RIGHT;

            if (tankCol + 1 < W && map[tankRow][tankCol + 1] == PLAIN) {
                map[tankRow][tankCol++] = PLAIN;
            }
        } else if (direction == 'L') {
            tankDirection = LEFT;

            if (tankCol - 1 >= 0 && map[tankRow][tankCol - 1] == PLAIN) {
                map[tankRow][tankCol--] = PLAIN;
            }
        }

        map[tankRow][tankCol] = tankDirection;
    }
}
