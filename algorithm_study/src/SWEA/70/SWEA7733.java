import java.io.*;
import java.util.*;

public class SWEA7733 {

    static final int[][] OFFSET = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int[] group;
    static int N, answer;
    static int[][] cheese;

    public static void main(String args[]) throws Exception {
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

            // 최초에 한 덩어리
            answer = 1;
            getResult(minTaste, maxTaste);
            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void getResult(int minTaste, int maxTaste) {
        for (int day = minTaste; day <= maxTaste; day++) {
            if (day == 100) {
                return;
            }

            group = new int[N * N];
            for (int i = 0; i < N * N; i++) {
                group[i] = i;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cheese[i][j] > day) {
                        for (int k = 0; k < OFFSET.length; k++) {
                            int nextRow = i + OFFSET[k][0];
                            int nextCol = j + OFFSET[k][1];

                            if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < N
                                    && cheese[nextRow][nextCol] > day) {
                                union(i * N + j, nextRow * N + nextCol);
                            }
                        }
                    }
                }
            }

            // 치즈 집합 수 세기
            Set<Integer> chunks = new HashSet<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cheese[i][j] > day) {
                        chunks.add(find(i * N + j));
                    }
                }
            }

            answer = Math.max(answer, chunks.size());
        }
    }

    static int find(int a) {
        if (group[a] == a) {
            return a;
        }

        int root = find(group[a]);
        group[a] = root;
        return root;
    }

    static void union(int a, int b) {
        if (a != group[a]) {
            a = find(a);
        }

        if (b != group[b]) {
            b = find(b);
        }

        if (a != b) {
            group[b] = a;
        }
    }
}
