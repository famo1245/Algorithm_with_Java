import java.io.*;
import java.util.*;

public class BOJ2110 {

    static int answer, N, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[] houses = new int[N];
        int[] distance = new int[N - 1];
        int maxPos = -1;

        for (int i = 0; i < N; i++) {
            int position = Integer.parseInt(br.readLine());
            houses[i] = position;
            maxPos = Math.max(position, maxPos);
        }
        Arrays.sort(houses);

        for (int i = 0; i < N - 1; i++) {
            distance[i] = houses[i + 1] - houses[i];
        }

        answer = -1;
        calcMax(1, maxPos, distance);
        System.out.println(answer);
    }

    static boolean fn(int value, int[] houses) {
        int group = 0;
        int sum = 0;

        for (int i = 0; i < N - 1; i++) {
            sum += houses[i];
            if (sum >= value) {
                group++;
                sum = 0;
            }
        }

        group++;
        return group >= C;
    }

    static void calcMax(int start, int end, int[] houses) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;
        if (fn(mid, houses)) {
            answer = Math.max(mid, answer);
            calcMax(mid + 1, end, houses);
        } else {
            calcMax(start, mid - 1, houses);
        }
    }
}