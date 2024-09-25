import java.util.*;
import java.io.*;

public class BOJ15573 {

    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Mineral> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][M];

        int[][] mountain = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int intensity = Integer.parseInt(st.nextToken());
                mountain[i][j] = intensity;

                if (i == 0 || j == 0 || j == M - 1) {
                    pq.add(new Mineral(i, j, intensity));
                    visited[i][j] = true;
                }
            }
        }

        int mined = 0;
        int minIntensity = Integer.MIN_VALUE;
        while (!pq.isEmpty()) {
            Mineral now = pq.poll();
            minIntensity = Math.max(minIntensity, now.intensity);
            mined++;

            if (mined == K) {
                break;
            }

            for (int i = 0; i < OFFSET.length; i++) {
                int nextRow = now.row + OFFSET[i][0];
                int nextCol = now.col + OFFSET[i][1];

                if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    pq.add(new Mineral(nextRow, nextCol, mountain[nextRow][nextCol]));
                }
            }
        }

        System.out.println(minIntensity);
    }

    static class Mineral implements Comparable<Mineral> {
        int row;
        int col;
        int intensity;

        public Mineral(int row, int col, int intensity) {
            this.row = row;
            this.col = col;
            this.intensity = intensity;
        }

        @Override
        public int compareTo(Mineral o) {
            return Integer.compare(this.intensity, o.intensity);
        }
    }
}
