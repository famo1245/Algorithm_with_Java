import java.io.*;
import java.util.*;

public class BOJ12899 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int size = startIndex * 2;
        int[] tree = new int[size];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int x = Integer.parseInt(st.nextToken());
                insert(i + startIndex, x, tree);
            } else {
                int order = Integer.parseInt(st.nextToken());
                sb.append(query(order, tree)).append('\n');
            }
        }

        System.out.print(sb);
    }

    static int query(int order, int[] tree) {
        int result = Integer.MIN_VALUE;
        int changeCount = 0;
        int index = 1;
        while (index <= tree.length / 2) {
            if (changeCount == order) {
                if (tree[index * 2] == result) {
                    tree[index] = tree[index * 2 + 1];
                    index = index * 2 + 1;
                } else {
                    tree[index] = tree[index * 2];
                    index *= 2;
                }
            } else {
                if (result < tree[index]) {
                    result = tree[index];
                    changeCount++;
                    if (tree[index * 2] < tree[index * 2 + 1]) {
                        index *= 2;
                    } else {
                        index = index * 2 + 1;
                    }
                }
            }
        }

        tree[index] = 0;
        return result;
    }

    static void insert(int index, int value, int[] tree) {
        int idx = index;
        while (idx > 1) {
            if (tree[idx] == 0) {
                tree[idx] = value;
            } else {
                tree[idx] = Math.min(tree[idx], value);
            }

            idx /= 2;
        }
    }
}
