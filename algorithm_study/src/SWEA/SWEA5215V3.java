import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215V3 {
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

            int[] tastes = new int[N + 1];
            int[] calories = new int[N + 1];
            int[] bits = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                tastes[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
                bits[i] = 1 << (i - 1);
            }

            int answer = Integer.MIN_VALUE;
            int combination = 1 << N;
            for (int select = 0; select < combination; select++) {
                int maxTaste = 0;
                int sumCalories = 0;
                boolean isPossible = true;

                for (int i = 1; i <= N; i++) {
                    if ((select & bits[i]) > 0) {
                        maxTaste += tastes[i];
                        sumCalories += calories[i];

                        if (sumCalories > L) {
                            isPossible = false;
                            break;
                        }
                    }
                }

                if (isPossible) {
                    answer = Math.max(answer, maxTaste);
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
