import java.io.*;

public class BOJ1463 {
    public static void main(String[] args) throws IOException {
        final int MAX = 1000001;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] D = new int[MAX];
        D[2] = 1;
        D[3] = 1;

        for (int i = 4; i < MAX; i++) {
            int case1, case2, case3;
            case1 = case2 = case3 = Integer.MAX_VALUE;
            // 1 뺴기
            case1 = D[i - 1];
            // 2나누기
            if (i % 2 == 0) {
                case2 = D[i / 2];
            }
            // 3 나누기
            if (i % 3 == 0) {
                case3 = D[i / 3];
            }

            D[i] = Math.min(Math.min(case1, case2), case3) + 1;
        }
        int N = Integer.parseInt(br.readLine());
        System.out.println(D[N]);
    }
}