import java.io.*;
import java.util.*;

public class BOJ14003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int size = startIndex * 2;
        int[] tree = new int[size];
        Num[] sequence = new Num[N];
        int[] originSeq = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            sequence[i] = new Num(value, i);
            originSeq[i] = value;
        }
        Arrays.sort(sequence);

        for (int i = 0; i < N; i++) {
            int index = sequence[i].index;

            if (index == 0) {
                update(startIndex, 1, tree);
            } else {
                int length = query(startIndex, index + startIndex - 1, tree);
                update(index + startIndex, length + 1, tree);
            }
        }

        sb.append(tree[1]).append('\n');
        int beforeLength = tree[1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; i--) {
            int treeIndex = i + startIndex;
            if (beforeLength == tree[treeIndex]) {
                q.addLast(originSeq[i]);
                beforeLength--;
            }
        }

        while (!q.isEmpty()) {
            sb.append(q.pollLast()).append(' ');
        }

        System.out.println(sb);
    }

    static int query(int start, int end, int[] tree) {
        int result = 0;
        while (start <= end) {
            if (start % 2 == 1) {
                result = Math.max(result, tree[start]);
                start++;
            }

            if (end % 2 == 0) {
                result = Math.max(result, tree[end]);
                end--;
            }

            start /= 2;
            end /= 2;
        }
        return result;
    }

    static void update(int index, int value, int[] tree) {
        int idx = index;
        while (idx > 0) {
            if (tree[idx] >= value) {
                break;
            }

            tree[idx] = value;
            idx /= 2;
        }
    }

    static class Num implements Comparable<Num> {
        int value;
        int index;

        public Num(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Num o) {
            if (this.value == o.value) {
                return Integer.compare(o.index, this.index);
            }

            return Integer.compare(this.value, o.value);
        }
    }
}
