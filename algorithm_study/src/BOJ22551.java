import java.util.*;
import java.io.*;

public class BOJ22551 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] D = new int[W + 1][N + 1];
        int[] weights = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {

        }
    }
}
