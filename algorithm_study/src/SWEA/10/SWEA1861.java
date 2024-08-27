import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1861 {

    // offset
    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};

    static int N, maxVisits, roomNumber;
    static int[][] rooms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");

            N = Integer.parseInt(br.readLine());
            rooms = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    rooms[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxVisits = -1;
            roomNumber = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int result = dfs(i, j, 1);
                    if (result >= maxVisits) {
                        if (result == maxVisits) {
                            roomNumber = Math.min(roomNumber, rooms[i][j]);
                        } else {
                            roomNumber = rooms[i][j];
                        }
                        maxVisits = result;
                    }
                }
            }

            sb.append(roomNumber).append(" ").append(maxVisits).append("\n");
        }

        System.out.print(sb);
    }

    static int dfs(int row, int col, int depth) {
        int maxDepth = 0;

        for (int i = 0; i < DX.length; i++) {
            int nextRow = row + DX[i];
            int nextCol = col + DY[i];

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) {
                continue;
            }

            if (rooms[row][col] + 1 == rooms[nextRow][nextCol]) {
                maxDepth = Math.max(maxDepth, dfs(nextRow, nextCol, depth + 1));
            }
        }

        return Math.max(depth, maxDepth);
    }
}
