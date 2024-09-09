import java.util.*;
import java.io.*;

public class BOJ10999 {

    static final int UPDATE = 1;

    static long[] lazy, tree;
    static int startIndex, treeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        startIndex = (int) Math.pow(2, height);
//        startIndex = 1;
//        while (startIndex < N) {
//            startIndex *= 2;
//        }
        treeSize = startIndex * 2;

        tree = new long[treeSize];
        lazy = new long[treeSize];

        for (int i = 0; i < N; i++) {
            tree[i + startIndex] = Long.parseLong(br.readLine());
        }

        int parent = startIndex - 1;
        while (parent > 0) {
            tree[parent] = tree[parent * 2] + tree[parent * 2 + 1];
            parent--;
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (command == UPDATE) {    // update
                long diff = Long.parseLong(st.nextToken());
                updateRange(1, 1, N, start, end, diff);
            } else {    // 구간 합 출력
                sb.append(getRangeSum(1, 1, N, start, end)).append("\n");
            }
//            System.out.println("tree " + Arrays.toString(tree));
//            System.out.println("lazy " + Arrays.toString(lazy));
        }

        System.out.print(sb);
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

    static void updateRange(int node, int start, int end, int left, int right, long value) {
        updateLazy(node, start, end);
        if (left > end || right < start) {
            return;
        }

        if (left <= start && end <= right) {
            tree[node] += (end - start + 1) * value;
            if (start != end) {
                lazy[node * 2] += value;
                lazy[node * 2 + 1] += value;
            }

            return;
        }

        int mid = (start + end) / 2;
        updateRange(node * 2, start, mid, left, right, value);
        updateRange(node * 2 + 1, mid + 1, end, left, right, value);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long getRangeSum(int node, int start, int end, int left, int right) {
        updateLazy(node, start, end);

        if (left > end || right < start) {
            return 0L;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return getRangeSum(node * 2, start, mid, left, right) +
                getRangeSum(node * 2 + 1, mid + 1, end, left, right);
    }
}

// 반례
//https://www.acmicpc.net/board/view/98886