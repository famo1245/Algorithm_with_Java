import java.io.*;
import java.util.*;

public class BOJ12899 {

    static final int MAX = 2_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int height = (int) Math.ceil(Math.log(MAX) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int size = startIndex * 2;
        int[] tree = new int[size];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int x = Integer.parseInt(st.nextToken());
                insert(startIndex + x - 1, tree);
            } else {
                int order = Integer.parseInt(st.nextToken());
                sb.append(query(order, tree, startIndex)).append('\n');
            }
        }

        System.out.print(sb);
    }

    static int query(int order, int[] tree, int startIndex) {
        int index = 1;
        while (index < startIndex) {
            if (tree[index * 2] >= order) {
                index = index * 2;
            } else {
                order -= tree[index * 2];
                index = index * 2 + 1;
            }
        }

        int result = index - startIndex + 1;
        update(index, -1, tree);
        return result;
    }

    static void update(int index, int diff, int[] tree) {
        while (index > 0) {
            tree[index] += diff;
            index /= 2;
        }
    }

    static void insert(int index, int[] tree) {
        update(index, 1, tree);
    }
}