import java.util.*;
import java.io.*;

public class BOJ17069 {

    static final int VERTICAL = 0;
    static final int HORIZONTAL = 1;
    static final int DIAGONAL = 2;
    static final int BLANK = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        long[][][] D = new long[N][N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        D[0][1][VERTICAL] = 1;
        for (int i = 2; i < N; i++) {
            if (map[0][i] == BLANK) {
                D[0][i][VERTICAL] = D[0][i - 1][VERTICAL];
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 2; j < N; j++) {
                // diagonal
                if (map[i][j] == BLANK && map[i][j - 1] == BLANK && map[i - 1][j] == BLANK) {
                    D[i][j][DIAGONAL] = D[i - 1][j - 1][VERTICAL] + D[i - 1][j - 1][HORIZONTAL] + D[i - 1][j - 1][DIAGONAL];
                }

                // vertical, horizontal
                if (map[i][j] == BLANK) {
                    D[i][j][VERTICAL] = D[i][j - 1][VERTICAL] + D[i][j - 1][DIAGONAL];
                    D[i][j][HORIZONTAL] = D[i - 1][j][HORIZONTAL] + D[i - 1][j][DIAGONAL];
                }
            }
        }

        long result = D[N - 1][N - 1][VERTICAL] + D[N - 1][N - 1][HORIZONTAL] + D[N - 1][N - 1][DIAGONAL];
        System.out.println(result);
    }
}
