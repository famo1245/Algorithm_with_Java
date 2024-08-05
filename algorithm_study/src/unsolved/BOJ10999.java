package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10999 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // init segment tree
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int treeSize = startIndex * 2;

        long[] tree = new long[treeSize];
        // TODO: lazy update 적용하기

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
                int index = Integer.parseInt(st.nextToken()) - 1 + startIndex;
                int endIndex = Integer.parseInt(st.nextToken()) - 1 + startIndex;
                long diff = Long.parseLong(st.nextToken());

                // 누적합 변경
                for (int j = index; j <= endIndex; j++) {
                    update(tree, j, diff);
                }
            } else {    // 2
                // 시작과 끝 인덱스
                int start = Integer.parseInt(st.nextToken()) - 1 + startIndex;
                int end = Integer.parseInt(st.nextToken()) - 1 + startIndex;
                sb.append(getRangeSum(tree, start, end)).append("\n");
            }
        }

        System.out.println(sb);
    }

    static void update(long[] segmentTree, int index, long value) {
        if (index == 0)
            return;

        segmentTree[index] += value;
        update(segmentTree, index / 2, value);
    }

    static long getRangeSum(long[] segmentTree, int startIndex, int endIndex) {
        long result = 0;
        if (startIndex > endIndex)
            return result;

        if (startIndex % 2 == 1) {
            result += segmentTree[startIndex];
            startIndex += 1;
        }
        if (endIndex % 2 == 0) {
            result += segmentTree[endIndex];
            endIndex -= 1;
        }

        return result + getRangeSum(segmentTree, startIndex / 2 , endIndex / 2);
    }
}
