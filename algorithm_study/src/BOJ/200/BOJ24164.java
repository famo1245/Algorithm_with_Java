import java.io.*;
import java.util.*;

public class BOJ24164 {

    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        group = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            group[i] = i;
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(find(i));
        }

        System.out.println(set.size() - 1);
    }

    static int find(int x) {
        if (x == group[x]) {
            return x;
        }

        return group[x] = find(group[x]);
    }

    static void union(int a, int b) {
        if (a != group[a]) {
            a = find(group[a]);
        }

        if (b != group[b]) {
            b = find(group[b]);
        }

        if (a != b) {
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            group[b] = a;
        }
    }
}