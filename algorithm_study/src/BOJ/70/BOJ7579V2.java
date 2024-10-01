import java.util.*;
import java.io.*;

public class BOJ7579V2 {

    public static void main(String[] args) throws IOException {
        final int MAX = 10100;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer sizes = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(sizes.nextToken());
        int M = Integer.parseInt(sizes.nextToken());

        sizes = new StringTokenizer(br.readLine());
        StringTokenizer costs = new StringTokenizer(br.readLine());

        int[] D = new int[MAX + 1];
        for (int i = 1; i <= N; i++) {
            int size = Integer.parseInt(sizes.nextToken());
            int cost = Integer.parseInt(costs.nextToken());

            for (int j = MAX; j >= cost; j--) {
                if (D[j] < D[j - cost] + size) {
                    D[j] = D[j - cost] + size;
                }
            }
        }

        for (int i = 0; i <= MAX; i++) {
            if (D[i] >= M) {
                System.out.println(i);
                return;
            }
        }
    }
}
