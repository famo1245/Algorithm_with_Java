import java.util.*;
import java.io.*;

public class SWEA7465 {

    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            group = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                group[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            Set<Integer> groupNum = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                groupNum.add(find(i));
            }

            sb.append(groupNum.size()).append('\n');
        }

        System.out.print(sb);
    }

    static int find(int x) {
        if (group[x] == x) {
            return x;
        }

        int root = find(group[x]);
        group[x] = root;
        return root;
    }

    static void union(int a, int b) {
        if (a != group[a]) {
            a = find(a);
        }

        if (b != group[b]) {
            b = find(b);
        }

        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        if (a != b) {
            group[a] = b;
        }
    }
}
