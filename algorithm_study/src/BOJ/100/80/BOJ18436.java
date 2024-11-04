import java.io.*;
import java.util.*;

public class BOJ18436 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int height = 32 - Integer.numberOfLeadingZeros(N - 1);
        int startIndex = 1 << height;
        int size = 1 << (height + 1);
        int[][] tree = new int[size][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            tree[startIndex + i][value & 1]++;
        }

        int parent = startIndex - 1;
        while (parent > 0) {
            tree[parent][0] = tree[parent * 2][0] + tree[parent * 2 + 1][0];
            tree[parent][1] = tree[parent * 2][1] + tree[parent * 2 + 1][1];
            parent--;
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int idx = startIndex + l - 1;
                if (tree[idx][r & 1] == 0) {
                    tree[idx][r & 1]++;
                    tree[idx][~r & 1] = 0;
                    idx /= 2;
                    while (idx > 0) {
                        tree[idx][r & 1]++;
                        tree[idx][~r & 1]--;
                        idx /= 2;
                    }
                }


            } else {
                int start = startIndex + l - 1;
                int end = startIndex + r - 1;
                int idx = command & 1;

                int result = 0;
                while (start <= end) {
                    if ((start & 1) == 1) {
                        result += tree[start][idx];
                        start++;
                    }

                    if ((end & 1) == 0) {
                        result += tree[end][idx];
                        end--;
                    }

                    start /= 2;
                    end /= 2;
                }

                sb.append(result).append('\n');
            }
        }
        System.out.print(sb);
    }
}
