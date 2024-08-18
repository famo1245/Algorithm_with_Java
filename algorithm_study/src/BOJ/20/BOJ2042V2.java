import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2042V2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // segment tree의 크기를 정하기 위한 트리의 높이 => logN
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int size = startIndex * 2;
        // leaf의 수가 N이므로 N*logN 만큼 초기화
        long[] segmentTree = new long[size];


        // 입력한 데이터를 리프 노드에 추가
        for (int i = 0; i < N; i++) {
            segmentTree[startIndex + i] = Long.parseLong(br.readLine());
        }

        // 세그먼트 트리 초기화
        int parent = startIndex - 1;
        while (parent > 1) {
            // parent = left child + right child
            segmentTree[parent] = segmentTree[parent * 2] + segmentTree[parent * 2 + 1];
            parent--;
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                // 실제 N번째 데이터의 tree에서의 인덱스로 변환
                int index = Integer.parseInt(st.nextToken()) - 1 + startIndex;
                long changedNum = Long.parseLong(st.nextToken());
                long diff = changedNum - segmentTree[index];

                // 누적합 변경
                update(segmentTree, index, diff);
            } else {    // 2
                // 시작과 끝 인덱스
                int start = Integer.parseInt(st.nextToken()) - 1 + startIndex;
                int end = Integer.parseInt(st.nextToken()) - 1 + startIndex;
                sb.append(getRangeSum(segmentTree, start, end)).append("\n");
            }
        }

        System.out.println(sb);
    }

    static void update(long[] segmentTree, int index, long value) {
        if (index == 0)
            return;

        segmentTree[index] += value;
        // 현재 노드의 부모 노드로 향하며 갱신
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
