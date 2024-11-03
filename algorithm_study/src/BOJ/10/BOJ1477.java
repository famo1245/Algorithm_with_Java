import java.io.*;
import java.util.*;

public class BOJ1477 {

    static int N, M, L, answer;
    static int[] restAreas;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        restAreas = new int[N + 2];
        restAreas[0] = 0;
        restAreas[N + 1] = L;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            restAreas[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(restAreas);

        answer = 0;
        calcMin(1, L - 1);
        System.out.println(answer);
    }

    private static void calcMin(int start, int end) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        if (check(mid)) {
            answer = mid;
            calcMin(start, mid - 1);
        } else {
            calcMin(mid + 1, end);
        }
    }

    private static boolean check(int value) {
        int count = 0;
        for (int i = 1; i <= N + 1; i++) {
            if (restAreas[i] - restAreas[i - 1] > value) {
                count += (restAreas[i] - restAreas[i - 1] - 1) / value;
            }
        }

        return count <= M;
    }
}
