import java.util.*;
import java.io.*;

public class BOJ14502 {

    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static final int MAX_WALL_COUNT = 3;
    static final int[][] OFFSET = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static int N, M, answer;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MIN_VALUE;
        constructWalls(0);
        System.out.println(answer);
    }

    static void constructWalls(int count) {
        if (count == MAX_WALL_COUNT) {
            calcArea();
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == BLANK) {
                    map[i][j] = WALL;
                    constructWalls(count + 1);
                    map[i][j] = BLANK;
                }
            }
        }
    }

    static void calcArea() {
        int[][] copiedMap = copyMap();
        Queue<int[]> que = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copiedMap[i][j] == VIRUS) {
                    que.add(new int[] {i, j});
                }
            }
        }

        while (!que.isEmpty()) {
            int[] now = que.poll();
            int row = now[0];
            int col = now[1];

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = row + OFFSET[i][0];
                int nextCol = col + OFFSET[i][1];

                if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M
                        && copiedMap[nextRow][nextCol] == BLANK) {
                    copiedMap[nextRow][nextCol] = VIRUS;
                    que.add(new int[] {nextRow, nextCol});
                }
            }
        }

        int area = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copiedMap[i][j] == BLANK) {
                    area++;
                }
            }
        }

        answer = Math.max(answer, area);
    }

    static int[][] copyMap() {
        int[][] copiedMap = new int [N][M];
        for (int i = 0; i < N; i++) {
            copiedMap[i] = map[i].clone();
        }

        return copiedMap;
    }
}