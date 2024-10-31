import java.io.*;
import java.util.*;

public class BOJ5676 {

    private static final String CHANGE = "C";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        String input;

        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input);

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int height = 32 - Integer.numberOfLeadingZeros(N - 1);
            int startIndex = 1 << height;
            int size = 1 << (height + 1);
            int[] tree = new int[size];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int value = Integer.parseInt(st.nextToken());

                if (value > 0) {
                    value = 1;
                } else if (value < 0) {
                    value = -1;
                }

                tree[startIndex + i] = value;
            }

            int parent = startIndex - 1;
            while (parent > 0) {
                tree[parent] = tree[parent * 2] * tree[parent * 2 + 1];
                parent--;
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();

                if (command.equals(CHANGE)) {
                    int index = Integer.parseInt(st.nextToken()) + startIndex - 1;
                    int value = Integer.parseInt(st.nextToken());

                    if (value > 0) {
                        value = 1;
                    } else if (value < 0) {
                        value = -1;
                    }

                    if (tree[index] != value) {
                        tree[index] = value;
                        index /= 2;

                        while (index > 0) {
                            tree[index] = tree[index * 2] * tree[index * 2 + 1];
                            index /= 2;
                        }
                    }
                } else {
                    int from = Integer.parseInt(st.nextToken()) + startIndex - 1;
                    int to = Integer.parseInt(st.nextToken()) + startIndex - 1;
                    int result = 1;

                    while (from <= to) {
                        if (from % 2 == 1) {
                            result *= tree[from];
                            from++;
                        }

                        if (to % 2 == 0) {
                            result *= tree[to];
                            to--;
                        }

                        from /= 2;
                        to /= 2;
                    }

                    if (result > 0) {
                        sb.append('+');
                    } else if (result < 0) {
                        sb.append('-');
                    } else {
                        sb.append('0');
                    }
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
