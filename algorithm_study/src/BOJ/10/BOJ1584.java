import java.util.*;
import java.io.*;

public class BOJ1584 {

    static final int MAX = 501;
    static final int DANGER = 1;
    static final int DEATH = -1;
    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[MAX][MAX];

        int N = Integer.parseInt(br.readLine());
        inputZone(br, map, N, DANGER);

        int M = Integer.parseInt(br.readLine());
        inputZone(br, map, M, DEATH);

        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[MAX][MAX];
        q.add(new int[]{0, 0, 0});
        while (!q.isEmpty()) {
            int[] now = q.pollFirst();
            int row = now[0];
            int col = now[1];
            int life = now[2];

            if (visited[row][col]) {
                continue;
            }

            visited[row][col] = true;
            for (int i = 0; i < OFFSET.length; i++) {
                int nr = row + OFFSET[i][0];
                int nc = col + OFFSET[i][1];

                if (0 <= nr && nr < MAX && 0 <= nc && nc < MAX && !visited[nr][nc] && map[nr][nc] != DEATH) {
                    if (nr == MAX - 1 && nc == MAX - 1) {
                        if (map[nr][nc] == DANGER) {
                            System.out.println(life + 1);
                        } else {
                            System.out.println(life);
                        }

                        return;
                    }

                    if (map[nr][nc] == DANGER) {
                        q.addLast(new int[]{nr, nc, life + 1});
                    } else {
                        q.addFirst(new int[]{nr, nc, life});
                    }
                }
            }
        }

        System.out.println(DEATH);
    }

    private static void inputZone(BufferedReader br, int[][] map, int size, int value) throws IOException {
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int x = Math.max(x1, x2);
            int y = Math.max(y1, y2);

            for (int j = Math.min(x1, x2); j <= x; j++) {
                for (int k = Math.min(y1, y2); k <= y; k++) {
                    map[j][k] = value;
                }
            }
        }
    }
}
