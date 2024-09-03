import java.util.*;
import java.io.*;

public class SWEA1953 {

    static final int PLUS = 1;
    static final int VERTICAL = 2;
    static final int HORIZONTAL = 3;
    static final int UPPER_RIGHT = 4;
    static final int LOWER_RIGHT = 5;
    static final int LOWER_LEFT = 6;
    static final int UPPER_LEFT = 7;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Queue<int[]> q = new ArrayDeque<>();
            int count = 1;
            q.add(new int[] {R, C, 1});
            while (!q.isEmpty()) {
                int[] now = q.poll();
                int row = now[0];
                int col = now[1];
                int time = now[2];

                if (time > L) {
                    continue;
                }

                
            }
        }
    }
}
