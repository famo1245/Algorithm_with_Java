import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11659V2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // segment tree의 크기를 정하기 위한 트리의 높이 => logN
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int size = startIndex * 2;
        // leaf의 수가 N이므로 N*logN 만큼 초기화
        int[] segmentTree = new int[size];


        // 입력한 데이터를 리프 노드에 추가
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            segmentTree[startIndex + i] = Integer.parseInt(input[i]);
        }

        // 세그먼트 트리 초기화
        int parent = startIndex - 1;
        while (parent > 1) {
            // parent = left child + right child
            segmentTree[parent] = segmentTree[parent * 2] + segmentTree[parent * 2 + 1];
            parent--;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 시작과 끝 인덱스
            int start = Integer.parseInt(st.nextToken()) - 1 + startIndex;
            int end = Integer.parseInt(st.nextToken()) - 1 + startIndex;
            sb.append(getRangeSum(segmentTree, start, end)).append("\n");

        }

        System.out.println(sb);
    }

    static int getRangeSum(int[] segmentTree, int startIndex, int endIndex) {
        int result = 0;

        while (startIndex <= endIndex) {
            if (startIndex % 2 == 1) {
                result += segmentTree[startIndex];
                startIndex += 1;
            }
            if (endIndex % 2 == 0) {
                result += segmentTree[endIndex];
                endIndex -= 1;
            }

            startIndex /= 2;
            endIndex /= 2;
        }

        return result;
    }
}
