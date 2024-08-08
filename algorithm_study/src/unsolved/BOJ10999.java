package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10999 {
    static long[] lazy;
    static long[] tree;
    static int startIndex;

    public static void main(String[] args) throws IOException {
        //TO => 기존 segment tree에 한계
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // init segment tree
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        startIndex = (int) Math.pow(2, height);
        int treeSize = startIndex * 2;

        tree = new long[treeSize];
        lazy = new long[N];

        for (int i = 0; i < N; i++) {
            tree[i + startIndex] = Long.parseLong(br.readLine());
        }

        int parent = startIndex - 1;
        while (parent > 1) {
            tree[parent] = tree[parent * 2] + tree[parent * 2 + 1];
            parent--;
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                // 실제 N번째 데이터의 tree에서의 인덱스로 변환
                int index = Integer.parseInt(st.nextToken());
                int endIndex = Integer.parseInt(st.nextToken());
                long diff = Long.parseLong(st.nextToken());

                if (index == endIndex) {
                    update(index - 1 + startIndex, diff);
                } else {
                    for(int j = startIndex - 1 ; j < endIndex; j++) {
                        lazy[j] = diff;
                    }
                }
            } else {    // 2
                // 시작과 끝 인덱스
                int start = Integer.parseInt(st.nextToken()) - 1 + startIndex;
                int end = Integer.parseInt(st.nextToken()) - 1 + startIndex;
                sb.append(getRangeSum(start, end)).append("\n");
            }
        }

        System.out.println(sb);
    }

    static void update(int index, long value) {
        if (index == 0)
            return;

        tree[index] += value;
        update(index / 2, value);
    }

    static long getRangeSum(int start, int end) {
        long result = 0;
        if (start > end)
            return result;

        if (start % 2 == 1) {
            result += tree[start];
            start += 1;
        }
        if (end % 2 == 0) {
            result += tree[end];
            end -= 1;
        }

        return result + getRangeSum(start / 2 , end / 2);
    }
}
