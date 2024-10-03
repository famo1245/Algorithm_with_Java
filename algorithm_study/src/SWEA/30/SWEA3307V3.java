import java.util.*;
import java.io.*;

public class SWEA3307V3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            int N = Integer.parseInt(br.readLine());
            int height = (int) Math.ceil(Math.log(N) / Math.log(2));
            int startIndex = (int) Math.pow(2, height);
            int size = startIndex * 2;
            int[] tree = new int[size];
            Num[] sequence = new Num[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                sequence[i] = new Num(Integer.parseInt(st.nextToken()), i);
            }
            Arrays.sort(sequence);

            for (int i = 0; i < N; i++) {
                int index = sequence[i].index;

                if (index == 0) {
                    update(startIndex, 1, tree);
                } else {
                    int treeIndex = startIndex + index - 1;
                    int length = query(startIndex, treeIndex - 1, tree);
                    update(treeIndex, length + 1, tree);
                }
            }

            sb.append(tree[1]).append('\n');
        }

        System.out.print(sb);
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
