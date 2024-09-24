import java.io.*;
import java.util.*;

public class BOJ6593 {

    static final char START = 'S';
    static final char END = 'E';
    static final char WALL = '#';
    static final int[][] OFFSET = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            char[][][] building = new char[L][R][C];
            int startZ = -1;
            int startRow = -1;
            int startCol = -1;
            int endZ = -1;
            int endRow = -1;
            int endCol = -1;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String input = br.readLine();
                    for (int k = 0; k < C; k++) {
                        building[i][j][k] = input.charAt(k);

                        if (input.charAt(k) == START) {
                            startZ = i;
                            startRow = j;
                            startCol = k;
                        } else if (input.charAt(k) == END) {
                            endZ = i;
                            endRow = j;
                            endCol = k;
                        }
                    }
                }
                // flush line
                br.readLine();
            }


            int minTime = Integer.MAX_VALUE;
            boolean[][][] visited = new boolean[L][R][C];
            Queue<int[]> que = new ArrayDeque<>();
            que.add(new int[]{startZ, startRow, startCol, 0});

            while(!que.isEmpty()) {
                int[] now = que.poll();
                int z = now[0];
                int x = now[1];
                int y = now[2];
                int time = now[3];

                if (z == endZ && x == endRow && y == endCol) {
                    minTime = Math.min(minTime, time);
                    continue;
                }

                if (visited[z][x][y]) {
                    continue;
                }

                visited[z][x][y] = true;
                for (int i = 0; i < OFFSET.length; i++) {
                    int nextZ = z + OFFSET[i][0];
                    int nextRow = x + OFFSET[i][1];
                    int nextCol = y + OFFSET[i][2];

                    if (0 <= nextZ && nextZ < L && 0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C
                            && !visited[nextZ][nextRow][nextCol]) {
                        if (building[nextZ][nextRow][nextCol] != WALL) {
                            que.add(new int[] {nextZ, nextRow, nextCol, time + 1});
                        }
                    }
                }
            }

            if (minTime == Integer.MAX_VALUE) {
                sb.append("Trapped!");
            } else {
                sb.append("Escaped in ").append(minTime).append(" minute(s).");
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }
}
