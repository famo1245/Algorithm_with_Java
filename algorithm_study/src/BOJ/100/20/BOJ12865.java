import java.util.*;
import java.io.*;

public class BOJ12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] D = new int[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            for (int j = K; j >= weight; j--) {
                if (D[j] < D[j - weight] + value) {
                    D[j] = D[j - weight] + value;
                }
            }
        }

        System.out.println(D[K]);
    }
}
