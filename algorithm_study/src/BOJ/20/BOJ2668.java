import java.io.*;
import java.util.*;

public class BOJ2668 {

    static int[] g;
    static List<Integer> answer;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        g = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());
            g[i] = num;
        }

        answer = new ArrayList<>();
        used = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!used[i]) {
                used[i] = true;
                findCycle(i, i);
            }
        }
        Collections.sort(answer);

        sb.append(answer.size()).append('\n');
        for (int num : answer) {
            sb.append(num).append('\n');
        }

        System.out.print(sb);
    }

    static boolean findCycle(int start, int now) {
        int next = g[now];
        if (next == start) {
            answer.add(start);
            return true;
        }

        if (!used[next]) {
            used[next] = true;
            if (findCycle(start, next)) {
                answer.add(next);
                return true;
            }
            used[next] = false;
        }

        return false;
    }
}