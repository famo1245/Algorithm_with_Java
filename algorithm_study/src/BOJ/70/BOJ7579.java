import java.util.*;
import java.io.*;

public class BOJ7579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] sizes = new int[N + 1];
        int[] costs = new int[N + 1];
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sizes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int cost = Integer.parseInt(st.nextToken());
            costs[i] = cost;
            sum += cost;
        }

        int[] D = new int[sum + 1];
        for (int i = 1; i <= N; i++) {
            int size = sizes[i];
            int cost = costs[i];

            for (int j = sum; j >= cost; j--) {
                if (D[j] < D[j - cost] + size) {
                    D[j] = D[j - cost] + size;
                }
            }
        }

        for (int i = 0; i <= sum; i++) {
            if (D[i] >= M) {
                System.out.println(i);
                return;
            }
        }
    }
}
