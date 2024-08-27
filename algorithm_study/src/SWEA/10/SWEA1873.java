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
        int offsetRow = 0;
        int offsetCol = 0;
        if (tankDirection == UP || tankDirection == DOWN) {
            if (tankDirection == UP) {
                offsetRow = -1;
            } else {
                offsetRow = 1;
            }
        } else {
            if (tankDirection == LEFT) {
                offsetCol = -1;
            } else {
                offsetCol = 1;
            }
        }

        int i = 1;
        while (true) {
            if (tankCol + offsetCol * i >= W || tankCol + offsetCol * i < 0 ||
                    tankRow + offsetRow * i >= H || tankRow + offsetRow * i < 0) {
                break;
            }

            int nextRow = tankRow + offsetRow * i;
            int nextCol = tankCol + offsetCol * i;
            if (map[nextRow][nextCol] == BRICK || map[nextRow][nextCol] == STEEL) {
                if (map[nextRow][nextCol] == BRICK) {
                    map[nextRow][nextCol] = PLAIN;
                }
                break;
            }

            i++;
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
