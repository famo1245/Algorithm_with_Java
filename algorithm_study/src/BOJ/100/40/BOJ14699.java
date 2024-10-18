import java.io.*;
import java.util.*;

public class BOJ14699 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] map = new ArrayList[N + 1];
        int[] heights = new int[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(x -> x[1])));

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int height = Integer.parseInt(st.nextToken());
            heights[i] = height;
            pq.add(new int[]{i, height});
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (heights[a] < heights[b]) {
                int temp = a;
                a = b;
                b = temp;
            }

            map[a].add(b);
        }

        int[] D = new int[N + 1];
        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int now = data[0];
            D[now] = Math.max(D[now], 1);

            for (int next : map[now]) {
                D[next] = Math.max(D[next], D[now] + 1);
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(D[i]).append('\n');
        }

        System.out.print(sb);
    }
}
