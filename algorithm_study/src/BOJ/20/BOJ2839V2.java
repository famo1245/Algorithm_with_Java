import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2839V2 {

    static final int MAX = 5001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] D = new int[MAX];
        // 3kg, 5kg 가방
        D[3] = 1;
        D[5] = 1;

        for (int i = 6; i < MAX; i++) {
            // 3kg 가방으로 담을 수 있는 경우
            if (D[i - 3] != 0) {
                D[i] = D[i - 3] + 1;
            }

            /*
            * 5kg으로 담을 수 있는 경우
            * 이미 위에서 처리한 경우와 아닌 경우로 구분해야 Math.min() 사용 가능
            * */
            if (D[i - 5] != 0) {
                D[i] = D[i] == 0 ? D[i - 5] + 1 : Math.min(D[i], D[i - 5] + 1);
            }
        }

        int answer = D[N] == 0 ? -1 : D[N];
        System.out.println(answer);
    }
}
