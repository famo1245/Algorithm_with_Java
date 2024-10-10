import java.util.*;
import java.io.*;

public class BOJ3055 {

    private static final String CANT_REACH = "KAKTUS";
    private static final char BEAVER_CAVE = 'D';
    private static final char HEDGEHOG = 'S';
    private static final char EMPTY = '.';
    private static final char WATER = '*';
    private static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        Queue<int[]> hedgehog = new ArrayDeque<>();
        Queue<int[]> water = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char current = line.charAt(j);
                map[i][j] = current;

                if (current == HEDGEHOG) {
                    hedgehog.add(new int[]{i, j, 0});
                } else if (current == WATER) {
                    water.add(new int[]{i, j});
                }
            }
        }

        while (!hedgehog.isEmpty()) {
            int size = water.size();
            for (int cnt = 0; cnt < size; cnt++) {
                int[] now = water.poll();
                int row = now[0];
                int col = now[1];

                for (int i = 0; i < OFFSET.length; i++) {
                    int nr = row + OFFSET[i][0];
                    int nc = col + OFFSET[i][1];

                    if (0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] == EMPTY) {
                        map[nr][nc] = WATER;
                        water.add(new int[]{nr, nc});
                    }
                }
            }

            size = hedgehog.size();
            for (int cnt = 0; cnt < size; cnt++) {
                int[] now = hedgehog.poll();
                int row = now[0];
                int col = now[1];
                int currentTime = now[2];

                for (int i = 0; i < OFFSET.length; i++) {
                    int nr = row + OFFSET[i][0];
                    int nc = col + OFFSET[i][1];

                    if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                        if (map[nr][nc] == BEAVER_CAVE) {
                            System.out.println(currentTime + 1);
                            return;
                        } else if (map[nr][nc] == EMPTY) {
                            map[nr][nc] = HEDGEHOG;
                            hedgehog.add(new int[]{nr, nc, currentTime + 1});
                        }
                    }
                }
            }
        }

        System.out.println(CANT_REACH);
    }
}
