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
        int route = 0;
        route += distances[start] + distances[end];
        while (start != end) {
            int remain = sum - route;

            if (route > remain) {
                route -= distances[start];
                start = (start + 1) % N;
                System.out.println("bigger" + start + " " + end);
                continue;
            }

            if (route > answer) {
                answer = route;
            }

            end = (end + 1) % N;
            route += distances[end];
            System.out.println(start + " " + end);
        }

        start = 0;
        end = 1;
        route = 0;
        route += distances[start] + distances[end];
        while (start != end) {
            int remain = sum - route;

            if (route > remain) {
                start = (start - 1 + N) % N;
                route -= distances[start];
                System.out.println("bigger" + start + " " + end);
                continue;
            }

            if (route > answer) {
                answer = route;
            }

            end = (end - 1 + N) % N;
            route += distances[end];
            System.out.println(start + " " + end);
        }

        System.out.println(answer);
    }
}
