import java.util.*;
import java.io.*;

public class SWEA5643 {

    static boolean[][] students, reverseStudents;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');

            N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            students = new boolean[N + 1][N + 1];
            reverseStudents = new boolean[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                students[from][to] = true;
                reverseStudents[to][from] = true;
            }

            int answer = 0;
            for (int i = 1; i <= N; i++) {
                visited = new boolean[N + 1];
                int forward = dfs(i, students) - 1;
                visited = new boolean[N + 1];
                int backward = dfs(i, reverseStudents) - 1;

                if (forward + backward == N - 1) {
                    answer++;
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static int dfs(int index, boolean[][] G) {
        visited[index] = true;
        int result = 1;

        for (int next = 1; next <= N; next++) {
            if (!visited[next] && G[index][next]) {
                result += dfs(next, G);
            }
        }

        return result;
    }
}
