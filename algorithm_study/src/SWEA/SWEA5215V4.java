import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215V4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            int[] tastes = new int[N];
            int[] calories = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                tastes[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }

            int answer = Integer.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                int[] indexes = new int[N];
                for (int j = N - 1; j >= N - i; j--) {
                    indexes[j] = 1;
                }
                do {
                    boolean isPossible = true;
                    int sumCalories = 0;
                    int sumTaste = 0;
                    for (int j = 0; j < N; j++) {
                        if (indexes[j] == 1) {
                            sumTaste += tastes[j];
                            sumCalories += calories[j];
                        }

                        if (sumCalories > L) {
                            isPossible = false;
                            break;
                        }
                    }

                    if (isPossible) {
                        answer = Math.max(answer, sumTaste);
                    }
                } while(np(indexes));
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static boolean np(int[] p) {
        int n = p.length;

        int i = n - 1;
        while (i > 0 && p[i - 1] >= p[i]) {
            --i;
        }

        if (i == 0) {
            return false;
        }

        int j = n - 1;
        while (p[i - 1] >= p[j]) {
            --j;
        }

        swap(p, i - 1, j);

        int k = n - 1;
        while (i < k) {
            swap(p, i++, k--);
        }

        return true;
    }

    static void swap(int[] p, int i, int j) {
        int temp = p[i];
        p[i] = p[j];
        p[j] = temp;
    }
}
