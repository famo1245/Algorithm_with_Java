import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ31476 {

    static int D, U, T, height, twin, pony, lastNode;
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
        Arrays.fill(tree, true);

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            tree[Integer.parseInt(input[1])] = false;
        }

        twin = 0;
        pony = 0;
        getTwinTime();
        getPonyTime(1, 1);
        System.out.println(twin + " " + pony);

        if (twin == pony) {
            System.out.print(":blob_twintail_thinking:");
        } else if (twin < pony) {
            System.out.print(":blob_twintail_aww:");
        } else {
            System.out.print(":blob_twintail_sad:");
        }
    }

    static void getTwinTime() {
        ArrayDeque<int[]> que = new ArrayDeque<>();

        que.add(new int[]{1, 1, U, 0});
        lastNode = 1;

        while (!que.isEmpty()) {
            int[] now = que.poll();
            int node = now[0];
            int depth = now[1];
            int time = now[2];
            int sum = now[3];

            lastNode = Math.max(lastNode, node);
            twin = Math.max(twin, sum);

            if (depth < D) {
                if (tree[node * 2] && tree[node * 2 + 1]) {
                    que.add(new int[]{node * 2, depth + 1, time + T, sum + time + T});
                    que.add(new int[]{node * 2 + 1, depth + 1, time +  T, sum + time + T});
                } else if (tree[node * 2]) {
                    que.add(new int[]{node * 2, depth + 1, time, sum + time});
                } else if (tree[node * 2 + 1]) {
                    que.add(new int[]{node * 2 + 1, depth + 1, time, sum + time});
                }
            }
        }
    }

    static void getPonyTime(int idx, int depth) {
        if (idx == lastNode) {
            isEnd = true;
            return;
        }

        if (depth < D) {
            if (tree[idx * 2]) {
                pony += U;
                getPonyTime(idx * 2, depth + 1);

                if (!isEnd) {
                    pony += U;
                }
            }

            if (tree[idx * 2 + 1]) {
                pony += U;
                getPonyTime(idx * 2 + 1, depth + 1);

                if (!isEnd) {
                    pony += U;
                }
            }
        }
    }
}