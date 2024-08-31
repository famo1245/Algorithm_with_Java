import java.io.*;
import java.util.*;

public class SWEA1767 {

    static final int CORE = 1;
    static final int MAX_SIZE = 12;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            int N = Integer.parseInt(br.readLine());

            int[][] pcb = new int[N][N];
            int[][] cores = new int[MAX_SIZE][];
            int coreSize = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int element = Integer.parseInt(st.nextToken());
                    pcb[i][j] = element;

                    if (element == CORE && i != 0 && j != 0) {
                        cores[coreSize++] = new int[] {i, j};
                    }
                }
            }


        }
    }
}
