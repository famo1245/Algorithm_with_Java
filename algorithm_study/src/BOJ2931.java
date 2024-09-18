import java.util.*;
import java.io.*;

public class BOJ2931 {

    static final int START = 'M';
    static final int END = 'Z';
    static final int BLANK = '.';

    static int[][] map;
    static int R, C, delRow, delCol, startRow, startCol, endRow, endCol;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);

                if (input.charAt(j) == START) {
                    startRow = i;
                    startCol = j;
                } else if (input.charAt(j) == END) {
                    endRow = i;
                    endCol = j;
                }
            }
        }

        findDeletedPoint(startRow, startCol);
    }

    static void findDeletedPoint(int row, int col) {

    }
}
