import java.util.*;
import java.io.*;

public class BOJ7569 {

    static final int ROTTEN = 1;
    static final int TOMATO = 0;
    static final int[][] OFFSET = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};

    static int[][][] storage, time;
    static int[][] rottenCoord;
    static int N, M, H, tomatoCount;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        storage = new int[H][M][N];
        time = new int[H][M][N];
        rottenCoord = new int[M * N * H][3];

        tomatoCount = 0;    // 안 익은 도마도
        int rottenCount = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    int value = Integer.parseInt(st.nextToken());
                    storage[i][j][k] = value;
                    time[i][j][k] = Integer.MAX_VALUE;

                    if (value == TOMATO) {
                        tomatoCount++;
                    }

                    if (value == ROTTEN) {
                        rottenCoord[rottenCount][2] = i;
                        rottenCoord[rottenCount][0] = j;
                        rottenCoord[rottenCount++][1] = k;
                    }
                }
            }
        }

        if (tomatoCount == 0) {
            System.out.println(0);
            return;
        }

        Queue<int[]> que = new ArrayDeque<>();
        for (int i = 0; i < rottenCount; i++) {
            int x = rottenCoord[i][0];
            int y = rottenCoord[i][1];
            int z = rottenCoord[i][2];

            que.add(new int[]{x, y, z, 0});
            visited = new boolean[H][M][N];
            time[z][x][y] = 0;
            bfs(que);
        }

        int answer = Integer.MIN_VALUE;
        if (tomatoCount != 0) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < M; j++) {
                    for (int k = 0; k < N; k++) {
                        if (time[i][j][k] == Integer.MAX_VALUE) {
                            continue;
                        }

                        answer = Math.max(answer, time[i][j][k]);
                    }
                }
            }
            System.out.println(answer);
        }
    }

    private static void bfs(Queue<int[]> que) {
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int x = now[0];
            int y = now[1];
            int z = now[2];
            int date = now[3];

            if (visited[z][x][y]) {
                continue;
            }

            visited[z][x][y] = true;
            for (int i = 0; i < OFFSET.length; i++) {
                int nextX = x + OFFSET[i][0];
                int nextY = y + OFFSET[i][1];
                int nextZ = z + OFFSET[i][2];

                if (0 <= nextX && nextX < M && 0 <= nextY && nextY < N && 0 <= nextZ && nextZ < H && !visited[nextZ][nextX][nextY]) {
                    if (storage[nextZ][nextX][nextY] == ROTTEN) {
                        que.add(new int[]{nextX, nextY, nextZ, date});
                    } else if (storage[nextZ][nextX][nextY] == TOMATO && time[nextZ][nextX][nextY] > date + 1) {
                        if (time[nextZ][nextX][nextY] == Integer.MAX_VALUE) {
                        tomatoCount--;
                        }
                        time[nextZ][nextX][nextY] = date + 1;
                        que.add(new int[]{nextX, nextY, nextZ, date + 1});
                    }
                }
            }
        }
    }
}
