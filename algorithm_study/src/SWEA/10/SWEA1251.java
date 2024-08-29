import java.io.*;
import java.util.*;

public class SWEA1251 {

    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');

            int N = Integer.parseInt(br.readLine());
            int[] x = new int[N];
            int[] y = new int[N];
            group = new int[N];
            PriorityQueue<Road> pq = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                group[i] = i;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                x[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                y[i] = Integer.parseInt(st.nextToken());
            }

            double E = Double.parseDouble(br.readLine());
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    double weight = (Math.pow((x[i] - x[j]), 2) + Math.pow((y[i] - y[j]), 2)) * E;
                    System.out.println(weight);
                    pq.add(new Road(i, j, weight));
                }
            }

            double answer = 0.0;
            int count = 0;
            while (!pq.isEmpty() && count < N - 1) {
                Road now = pq.poll();

                if (find(now.from) != find(now.to)) {
                    union(now.from, now.to);
                    answer += now.weight;
                    count++;
                }
            }

            sb.append(Math.round(answer)).append('\n');
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if (group[x] == x) {
            return x;
        }

        int root = find(group[x]);
        group[x] = root;
        return root;
    }

    static void union(int a, int b) {
        if (a != group[a]) {
            a = find(a);
        }

        if (b != group[b]) {
            b = find(b);
        }

        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        if (a != b) {
            group[a] = b;
        }
    }

    static class Road implements Comparable<Road> {
        int from;
        int to;
        double weight;

        public Road(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Road o) {
            return Double.compare(this.weight, o.weight);
        }
    }
}
