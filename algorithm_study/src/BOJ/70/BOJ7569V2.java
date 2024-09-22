import java.util.*;
import java.io.*;

public class BOJ7569V2 {

    static final int ROTTEN = 1;
    static final int TOMATO = 0;
    static final int[][] OFFSET = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][][] storage = new int[H][M][N];
        boolean[][][] visited = new boolean[H][M][N];
        Queue<int[]> que = new ArrayDeque<>();
        int tomatoCount = 0;    // 안 익은 도마도

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    int value = Integer.parseInt(st.nextToken());
                    storage[i][j][k] = value;

                    if (value == TOMATO) {
                        tomatoCount++;
                    }

                    if (value == ROTTEN) {
                        visited[i][j][k] = true;
                        que.add(new int[]{j, k, i, 0});
                    }
                }
            }
        }

        if (tomatoCount == 0) {
            System.out.println(0);
            return;
        }

        int answer = Integer.MIN_VALUE;
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int x = now[0];
            int y = now[1];
            int z = now[2];
            int date = now[3];
            answer = date;

            for (int i = 0; i < OFFSET.length; i++) {
                int nextX = x + OFFSET[i][0];
                int nextY = y + OFFSET[i][1];
                int nextZ = z + OFFSET[i][2];

                if (0 <= nextX && nextX < M && 0 <= nextY && nextY < N && 0 <= nextZ && nextZ < H
                        && !visited[nextZ][nextX][nextY]) {
                    if (storage[nextZ][nextX][nextY] == TOMATO) {
                        tomatoCount--;
                        visited[nextZ][nextX][nextY] = true;
                        que.add(new int[]{nextX, nextY, nextZ, date + 1});
                    }
                }
            }
        }

        if (tomatoCount != 0) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
