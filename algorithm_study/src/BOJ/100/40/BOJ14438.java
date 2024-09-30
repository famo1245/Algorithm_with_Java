import java.util.*;
import java.io.*;

public class BOJ14438 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int size = startIndex * 2;
        int[] tree = new int[size];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i + startIndex] = Integer.parseInt(st.nextToken());
        }

        int parent = startIndex - 1;
        while (parent > 0) {
            tree[parent] = Math.min(tree[parent * 2], tree[parent * 2 + 1]);
            parent--;
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                int index = Integer.parseInt(st.nextToken()) + startIndex - 1;
                int value = Integer.parseInt(st.nextToken());
                tree[index] = value;

                update(tree, index / 2);
            } else {
                int from = Integer.parseInt(st.nextToken()) + startIndex - 1;
                int to = Integer.parseInt(st.nextToken()) + startIndex - 1;
                int result = Integer.MAX_VALUE;
                while (from <= to) {
                    if (from % 2 == 1) {
                        result = Math.min(result, tree[from]);
                        from++;
                    }

                    if (to % 2 == 0) {
                        result = Math.min(result, tree[to]);
                        to--;
                    }

                    from /= 2;
                    to /= 2;
                }

                sb.append(result).append('\n');
            }
        }

        System.out.print(sb);
    }

    static void update(int[] tree, int index) {
        if (index == 0) {
            return;
        }

        tree[index] = Math.min(tree[index * 2], tree[index * 2 + 1]);
        update(tree, index / 2);
    }
}