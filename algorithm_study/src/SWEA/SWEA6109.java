import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA6109 {

    static int N;
    static int[][] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append("\n");
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            table = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (direction.equals("up")) {
                rotateLeft();
                rotateLeft();
            } else if (direction.equals("left")) {
                rotateLeft();
            } else if (direction.equals("right")) {
                rotateRight();
            }

            drop();

            if (direction.equals("up")) {
                rotateRight();
                rotateRight();
            } else if (direction.equals("left")) {
                rotateRight();
            } else if (direction.equals("right")) {
                rotateLeft();
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(table[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    static void rotateLeft() {
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[N - j - 1][i] = table[i][j];
            }
        }

        table = temp;
    }

    static void rotateRight() {
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[j][N - i - 1] = table[i][j];
            }
        }

        table = temp;
    }
    
    static void drop() {
        for (int col = 0; col < N; col++) {
            int row = N - 1;
            int idx = 0;
            int[] line = new int[N];

            // 아래로 내리기
            while (row >= 0) {
                if (table[row][col] != 0) {
                    line[idx++] = table[row][col];
                }

                row--;
            }

            idx = 0;
            row = N - 1;
            // 블록 합치기 & 테이블에 값 채우기
            while (idx < N && row >= 0) {
                if (line[idx] != 0) {
                    if (idx < N - 1 && line[idx] == line[idx + 1]) {
                        table[row--][col] = line[idx] * 2;
                        idx++;
                    } else {
                        table[row--][col] = line[idx];
                    }
                }

                idx++;
            }

            while (row >= 0) {
                table[row--][col] = 0;
            }
        }
    }
}
