import java.util.*;
import java.io.*;

public class BOJ10999 {

    static final int UPDATE = 1;

    static long[] lazy, tree, input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        tree = new long[N * 4];
        lazy = new long[N * 4];
        input = new long[N];

        for (int i = 0; i < N; i++) {
            input[i] = Long.parseLong(br.readLine());
        }
        init(1, 0, N - 1);
        System.out.println(Arrays.toString(tree));

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            if (command == UPDATE) {
                long value = Long.parseLong(st.nextToken());
                update(1, 0, N - 1, start, end, value);
            } else {
                sb.append(query(1, 0, N - 1, start, end)).append('\n');
            }
        }

        System.out.print(sb);
    }

    static long init(int node, int start, int end) {
        if (start == end) {
            tree[node] = input[start];
            return tree[node];
        }

        int mid = (start + end) / 2;
        tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
        return tree[node];
    }

    static void updateLazy(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            lazy[node] = 0;
        }
    }

    static void update(int node, int start, int end, int left, int right, long value) {
        updateLazy(node, start, end);
        if (left > end || right < start) {  // 범위 밖
            return;
        }

        if (left <= start && end <= right) {    // 범위 안
            lazy[node] += value;
            updateLazy(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, left, right, value);
        update(node * 2 + 1, mid + 1, end, left, right, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long query(int node, int start, int end, int left, int right) {
        updateLazy(node, start, end);
        if (left > end || right < start) {
            return 0L;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        long result1 = query(node * 2, start, mid, left, right);
        long result2 = query(node * 2 + 1, mid + 1, end, left, right);
        return result1 + result2;
    }
}

// 반례
//https://www.acmicpc.net/board/view/98886