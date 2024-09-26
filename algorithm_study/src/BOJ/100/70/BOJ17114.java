import java.util.*;
import java.io.*;

public class BOJ17114 {

    static final int ROTTEN = 1;
    static final int TOMATO = 0;
    static final int BLANK = -1;
    static final int[][] OFFSET = {{-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0},{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int o = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[][][][][][][][][][][] storage = new int[w][v][u][t][s][r][q][p][o][n][m];
        boolean[][][][][][][][][][][] visited = new boolean[w][v][u][t][s][r][q][p][o][n][m];
        Queue<int[]> que = new ArrayDeque<>();
        int tomatoCount = 0;

        for (int a = 0; a < w; a++) {
            for (int b = 0; b < v; b++) {
                for (int c = 0; c < u; c++) {
                    for (int d = 0; d < t; d++) {
                        for (int e = 0; e < s; e++) {
                            for (int f = 0; f < r; f++) {
                                for (int g = 0; g < q; g++) {
                                    for (int h = 0; h < p; h++) {
                                        for (int i = 0; i < o; i++) {
                                            for (int j = 0; j < n; j++) {
                                                st = new StringTokenizer(br.readLine());
                                                for (int k = 0; k < m; k++) {
                                                    int value = Integer.parseInt(st.nextToken());
                                                    storage[a][b][c][d][e][f][g][h][i][j][k] = value;

                                                    if (value == ROTTEN) {
                                                        visited[a][b][c][d][e][f][g][h][i][j][k] = true;
                                                        que.add(new int[] {a, b, c, d, e, f, g, h, i, j, k, 0});
                                                    } else if (value == TOMATO) {
                                                        tomatoCount++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        int maxDate = -1;
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int date = now[11];
            maxDate = date;

            for (int offset = 0; offset < OFFSET.length; offset++) {
                int a = now[0] + OFFSET[offset][0];
                int b = now[1] + OFFSET[offset][1];
                int c = now[2] + OFFSET[offset][2];
                int d = now[3] + OFFSET[offset][3];
                int e = now[4] + OFFSET[offset][4];
                int f = now[5] + OFFSET[offset][5];
                int g = now[6] + OFFSET[offset][6];
                int h = now[7] + OFFSET[offset][7];
                int i = now[8] + OFFSET[offset][8];
                int j = now[9] + OFFSET[offset][9];
                int k = now[10] + OFFSET[offset][10];

                if (0 <= a && a < w && 0 <= b && b < v && 0 <= c && c < u && 0 <= d && d < t
                        && 0 <= e && e < s && 0 <= f && f < r && 0 <= g && g < q
                        && 0 <= h && h < p && 0 <= i && i < o && 0 <= j && j < n
                        && 0 <= k && k < m && !visited[a][b][c][d][e][f][g][h][i][j][k]
                        && storage[a][b][c][d][e][f][g][h][i][j][k] == TOMATO) {
                    visited[a][b][c][d][e][f][g][h][i][j][k] = true;
                    que.add(new int[] {a, b, c, d, e, f, g, h, i, j, k, date + 1});
                    tomatoCount--;
                }
            }
        }

        if (tomatoCount > 0) {
            System.out.println(-1);
        } else {
            System.out.println(maxDate);
        }
    }
}