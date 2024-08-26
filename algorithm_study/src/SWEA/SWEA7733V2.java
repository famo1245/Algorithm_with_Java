import java.io.*;
import java.util.*;

public class SWEA7733V2 {

    static final int BLANK = -1;
    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int[][] cheese;
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');

            N = Integer.parseInt(br.readLine());
            cheese = new int[N][N];
            int maxTaste = 0;
            int minTaste = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int taste = Integer.parseInt(st.nextToken());
                    cheese[i][j] = taste;
                    maxTaste = Math.max(maxTaste, taste);
                    minTaste = Math.min(minTaste, taste);
                }
            }

            // 최초에는 한 덩어리
            answer = 1;
            for (int day = minTaste; day <= maxTaste; day++) {
                if (day == 100) {
                    break;
                }

                eatCheese(day);
                int chunk = countChunk();
                answer = Math.max(answer, chunk);
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void eatCheese(int day) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cheese[i][j] == day) {
                    cheese[i][j] = BLANK;
                }
            }
        }
    }

    static int countChunk() {
        int chunk = 0;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> que = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cheese[i][j] != BLANK && !visited[i][j]) {
                    que.add(new int[]{i, j});
                    visited[i][j] = true;

                    while (!que.isEmpty()) {
                        int[] now = que.poll();
                        int nowRow = now[0];
                        int nowCol = now[1];

                        for (int offset = 0; offset < OFFSET.length; offset++) {
                            int nextRow = nowRow + OFFSET[offset][0];
                            int nextCol = nowCol + OFFSET[offset][1];

                            if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N && !visited[nextRow][nextCol]
                                    && cheese[nextRow][nextCol] != BLANK) {
                                visited[nextRow][nextCol] = true;
                                que.add(new int[]{nextRow, nextCol});
                            }
                        }
                    }

                    chunk++;
                }
            }
        }

        return chunk;
    }
}