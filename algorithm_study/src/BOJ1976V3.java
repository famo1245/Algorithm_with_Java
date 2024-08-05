import java.io.*;
import java.util.*;

// 메모이제이션으로 시간 236ms => 176ms 단축
public class BOJ1976V3 {

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 도시의 수
        int N = Integer.parseInt(br.readLine());
        // 여행 계획에 있는 도시의 수
        int M = Integer.parseInt(br.readLine());
        map = new int[N][];
        int[] routes = new int[M];

        // 도시의 연결 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] temp = new int[N];

            for (int j = 0; j < N; j++) {
                temp[j] = Integer.parseInt(st.nextToken());
            }

            map[i] = temp;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            routes[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        // 연결 되어 있다는 점에 대한 flag
        boolean isPossible = true;

        for (int i = 0; i < M - 1; i++) {
            if (routes[i] != routes[i + 1]) {
                isPossible = isConnected(routes[i], routes[i + 1]);
            }

            if (!isPossible) {
                break;
            }
        }

        if (isPossible) {
            System.out.println("YES");
            return;
        }

        System.out.println("NO");
    }

    private static boolean isConnected(int start, int end) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int size = map.length;
        q.add(start);
        boolean[] visited = new boolean[size];
        visited[start] = true;

        // BFS 수행
        while (!q.isEmpty()) {
            int node = q.pollFirst();

            // 두 도시가 바로 연결되어 있는 경우
            if (map[node][end] == 1) {
                return true;
            }

            for (int i = 0; i < size; i++) {
                if (!visited[i] && map[node][i] == 1) {
                    // 메모이제이션 수행
                    map[start][end] = 1;
                    visited[i] = true;
                    q.add(i);
                }
            }
        }

        return false;
    }
}
