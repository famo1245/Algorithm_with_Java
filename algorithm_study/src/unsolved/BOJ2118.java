package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2118 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] distances = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            distances[i] = Integer.parseInt(br.readLine());
            sum += distances[i];
        }

        int answer = -1;
        int start = 0;
        int end = 1;
        int route = distances[start];
        int remain = sum - route;
        while (start < N) {
            answer = Math.max(answer, Math.min(route, remain));
            if (route == remain) {
                break;
            }

            if (route > remain) {
                route -= distances[start];
                remain += distances[start];
                start++;
                continue;
            }

            remain -= distances[end];
            route += distances[end];
            end = (end + 1) % N;
        }

        System.out.println(answer);
    }
}
