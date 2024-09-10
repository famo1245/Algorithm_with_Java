import java.io.*;
import java.util.*;

public class BOJ2887 {

    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<int[]> planets = new ArrayList<>();
        group = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets.add(new int[]{i, x, y, z});
            group[i] = i;
        }

        PriorityQueue<Road> pq = new PriorityQueue<>();
        for (int i = 1; i <= 3; i++) {
            int index = i;
            Collections.sort(planets, Comparator.comparingInt(o -> o[index]));
            for (int j = 1; j < N; j++) {
                int[] planet1 = planets.get(j - 1);
                int[] planet2 = planets.get(j);
                int distance = Math.abs(planet1[index] - planet2[index]);
                pq.add(new Road(planet1[0], planet2[0], distance));
            }
        }

        int answer = 0;
        int count = 0;
        while (!pq.isEmpty() && count < N - 1) {
            Road now = pq.poll();

            if (find(now.from) != find(now.to)) {
                union(now.from, now.to);
                answer += now.distance;
                count++;
            }
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (x == group[x]) {
            return x;
        }

        int root = find(group[x]);
        group[x] = root;
        return root;
    }

    static void union(int a, int b) {
        if (a != group[a]) {
            a = find(group[a]);
        }

        if (b != group[b]) {
            b = find(group[b]);
        }

        if (a != b) {
            group[b] = a;
        }
    }

    static class Road implements Comparable<Road> {
        int from;
        int to;
        int distance;

        public Road(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Road o) {
            return this.distance - o.distance;
        }
    }
}