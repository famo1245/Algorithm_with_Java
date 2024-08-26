import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ31476 {

    static int D, U, T, height, twin, pony;
    static boolean[] tree;
    static boolean isEnd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        height = (int) Math.pow(2, D) - 1;
        tree = new boolean[height + 1];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            tree[Integer.parseInt(input[1])] = false;
        }

        getTwinTime();
        getPonyTime(1, 0);

        if (twin == pony) {
            System.out.println(":blob_twintail_thinking:");
        } else if (twin < pony) {
            System.out.println(":blob_twintail_aww:");
        } else {
            System.out.println(":blob_twintail_sad:");
        }
    }

    static int getTwinTime() {
        boolean[] visited = new boolean[height + 1];
        ArrayDeque<int[]> que = new ArrayDeque<>();

        que.add(new int[] { 1, 0 });
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int node = now[0];
            int depth = now[1];

            if (depth == D) {
                break;
            }

            if (tree[node * 2] && tree[node * 2 + 1]) {
                // T 추가
            } else if (tree[node * 2]) {

            } else if (tree[node * 2 + 1]) {

            }
        }

        return 1;
    }

    static void getPonyTime(int idx, int depth) {
        if (depth == D || idx == height + 1) {
            return;
        }

        if (tree[idx * 2]) {
            pony += U;
            getPonyTime(idx * 2, depth + 1);
        }

        if (tree[idx * 2 + 1]) {
            pony += U;
            getPonyTime(idx * 2 + 1, depth + 1);
        }
    }
}