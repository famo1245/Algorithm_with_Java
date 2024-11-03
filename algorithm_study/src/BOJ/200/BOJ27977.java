import java.io.*;
import java.util.*;

public class BOJ27977 {

    static int answer, L, N, K;
    static int[] powers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K == 0) {
            System.out.println(L);
            return;
        }

        powers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            powers[i] = Integer.parseInt(st.nextToken());
        }

        calcMinCost(0, L);
        System.out.println(answer);
    }

    private static void calcMinCost(int min, int max) {
        if (min > max) {
            return;
        }

        int mid = (min + max) / 2;
        if (validate(mid)) {
            answer = mid;
            calcMinCost(min, mid - 1);
        } else {
            calcMinCost(mid + 1, max);
        }
    }

    private static boolean validate(int value) {
        int battery = value;
        int visitCount = 0;
        int index = 0;
        int position = 0;

        while (position < L) {
            if (battery <= 0) {
                return false;
            }

            position++;
            battery--;
            if (index < N && powers[index] == position) {
                int nextPosition = (index == N - 1) ? L : powers[index + 1];
                int remainingDistance = nextPosition - position;

                if (battery < remainingDistance && visitCount < K) {
                    battery = value;
                    visitCount++;
                }
                index++;
            }
        }

        return true;
    }
}
