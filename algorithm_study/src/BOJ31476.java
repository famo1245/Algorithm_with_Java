import java.util.*;
import java.io.*;

public class BOJ31476 {

    static int D, U, T, height, pony, twin, nodeCount;
    static boolean[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        height = (int) Math.pow(2, D);
        tree = new boolean[height];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            int to = Integer.parseInt(st.nextToken());

            tree[to] = true;
        }

        twin = 0;
        pony = 0;
        getTwinTime();
        getPonyTime(1, 1);

        if (twin == pony) {
            System.out.print(":blob_twintail_thinking:");
        } else if (twin < pony) {
            System.out.print(":blob_twintail_aww:");
        } else {
            System.out.print(":blob_twintail_sad:");
        }
    }

    static void getTwinTime() {
        Queue<int[]> q = new ArrayDeque<>();
        // node, depth, separate count, time
        q.add(new int[] {1, 1, 0, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int node = now[0];
            int depth = now[1];
            int separate = now[2];
            int time = now[3];

            twin = Math.max(twin, time);
            nodeCount++;
            if (depth == D) {
                continue;
            }

            if (!tree[node * 2] && !tree[node * 2 + 1]) {
                q.add(new int[] {node * 2, depth + 1, separate + 1, time + U + (T * (separate + 1))});
                q.add(new int[] {node * 2 + 1, depth + 1, separate + 1, time + U + (T * (separate + 1))});
            } else if (!tree[node * 2] && tree[node * 2 + 1]) {
                q.add(new int[] {node * 2, depth + 1, separate, time + U + (T * separate)});
            } else if (tree[node * 2] && !tree[node * 2 + 1]) {
                q.add(new int[] {node * 2 + 1, depth + 1, separate, time + U + (T * separate)});
            }
        }
    }

    static void getPonyTime(int node, int depth) {
        nodeCount--;
        if (depth == D) {
            return;
        }

        if (!tree[node * 2]) {
            pony += U;
            getPonyTime(node * 2, depth + 1);
            if (nodeCount > 0) {
                pony += U;
            }
        }

        if (!tree[node * 2 + 1]) {
            pony += U;
            getPonyTime(node * 2 + 1, depth + 1);
            if (nodeCount > 0) {
                pony += U;
            }
        }
    }
}
