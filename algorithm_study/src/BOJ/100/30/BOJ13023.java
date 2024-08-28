import java.io.*;
import java.util.*;

public class BOJ13023 {

    static int N;
    static List<Integer>[] friends;
    static boolean[] visited;
    static boolean possible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        friends = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friends[a].add(b);
            friends[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            isConnectedAll(i, 1);

            if (possible) {
                break;
            }
        }

        if (possible) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static void isConnectedAll(int node, int depth) {
        if (depth == 5) {
            possible = true;
            return;
        }

        visited[node] = true;
        for (int next : friends[node]) {
            if (!visited[next]) {
                isConnectedAll(next, depth + 1);
            }
        }
        visited[node] = false;
    }
}