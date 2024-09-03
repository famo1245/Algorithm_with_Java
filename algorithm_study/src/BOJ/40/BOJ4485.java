import java.util.*;
import java.io.*;

public class BOJ4485 {

    static final int[][] OFFSET = {{-1, 0},{1, 0},{0, -1},{0, 1}};

    static int[][] cave;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int problem = 1;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            sb.append("Problem ").append(problem++).append(": ");
            cave = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = dijkstra();
            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    private static int dijkstra() {
        int startRow = 0;
        int startCol = 0;
        int endRow = N - 1;
        int endCol = N - 1;
        int[][] distance = new int[N][N];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        distance[startRow][startCol] = cave[startRow][startCol];
        pq.add(new int[] {distance[startRow][startCol], startRow, startCol});
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int row = now[1];
            int col = now[2];
            int weight = now[0];

            if (distance[row][col] < weight) {
                continue;
            }

            for(int i = 0; i < OFFSET.length; i++) {
                int nextRow = row + OFFSET[i][0];
                int nextCol = col + OFFSET[i][1];

                if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N
                        && distance[nextRow][nextCol] > weight + cave[nextRow][nextCol]) {
                    distance[nextRow][nextCol] = weight + cave[nextRow][nextCol];
                    pq.add(new int[] {distance[nextRow][nextCol], nextRow, nextCol});
                }
            }
        }

        return distance[N - 1][N - 1];
    }
}