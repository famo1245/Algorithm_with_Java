import java.util.*;
import java.io.*;

public class BOJ16985 {

    static final int BLANK = 1;
    static final int WALL = 0;
    static final int N = 5;
    static final int[][] OFFSET = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, 1}, {0, 0, -1}};

    static int[][][] maze, copiedMaze, distance;
    static boolean[][][] visited;
    static boolean[] usedLayer;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        maze = new int[N][N][N];
        copiedMaze = new int[N][N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    maze[i][j][k] = st.nextToken().charAt(0) - '0';
                }
            }
        }

        answer = Integer.MAX_VALUE;
        usedLayer = new boolean[N];
        setRotate(0);

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    static void initBFS() {
        distance = new int[N][N][N];
        visited = new boolean[N][N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }
        visited[0][0][0] = true;
        distance[0][0][0] = 0;
    }

    static void bfs() {
        if (copiedMaze[0][0][0] == WALL || copiedMaze[N - 1][N - 1][N - 1] == WALL) {
            return;
        }

        initBFS();
        Queue<Coordinate> q = new ArrayDeque<>();
        q.add(new Coordinate(0, 0, 0, 0));

        while(!q.isEmpty()) {
            Coordinate now = q.poll();
            if (now.x == N - 1 && now.y == N - 1 && now.z == N - 1) {
                answer = Math.min(answer, now.hop);
                continue;
            }

            if (distance[now.z][now.x][now.y] < now.hop) {
                continue;
            }

            for (int i = 0; i < OFFSET.length; i++) {
                int nextX = now.x + OFFSET[i][0];
                int nextY = now.y + OFFSET[i][1];
                int nextZ = now.z + OFFSET[i][2];

                if (isValid(nextX, nextY, nextZ)) {
                    if (!visited[nextZ][nextX][nextY] && copiedMaze[nextZ][nextX][nextY] == BLANK
                            && distance[nextZ][nextX][nextY] > now.hop + 1) {
                        visited[nextZ][nextX][nextY] = true;
                        distance[nextZ][nextX][nextY] = now.hop + 1;
                        q.add(new Coordinate(nextX, nextY, nextZ, now.hop + 1));
                    }
                }
            }
        }
    }

    static void setRotate(int layer) {
        if (layer == N) {
            setLayer(0);
            return;
        }

        for (int i = 0; i < 4; i++) {   // 3번 돌리면 제자리
            rotateRight(layer);
            setRotate(layer + 1);
        }
    }

    static void setLayer(int layer) {
        if (layer == N) {
            bfs();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (usedLayer[i]) {
                continue;
            }

            usedLayer[i] = true;
            for (int j = 0; j < N; j++) {
                copiedMaze[layer][j] = maze[i][j].clone();
            }
            setLayer(layer + 1);
            usedLayer[i] = false;
        }
    }

    static void rotateRight(int layer) { // z index
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[j][N - i - 1] = maze[layer][i][j];
            }
        }

        maze[layer] = temp;
    }

    static boolean isValid(int x, int y, int z) {
        boolean validX = 0 <= x && x < N;
        boolean validY = 0 <= y && y < N;
        boolean validZ = 0 <= z && z < N;
        return validX && validY && validZ;
    }

    static class Coordinate {
        int x;
        int y;
        int z;
        int hop;

        public Coordinate(int x, int y, int z, int hop) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.hop = hop;
        }
    }
}
