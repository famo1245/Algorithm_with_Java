import java.io.*;
import java.util.*;

public class SWEA1251V2 {

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
            double[][] map = new double[N][N];
            boolean[] visited = new boolean[N];
            PriorityQueue<Road> pq = new PriorityQueue<>();

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
                    map[i][j] = weight;
                    map[j][i] = weight;
                }
            }

            double answer = 0.0;
            int count = 0;
            pq.add(new Road(0, 0));
            while (!pq.isEmpty()) {
                if (count == N) {
                    break;
                }

                Road now = pq.poll();
                if (visited[now.to]) {
                    continue;
                }

                answer += now.weight;
                visited[now.to] = true;
                count++;

                for (int i = 0; i < N; i++) {
                    if (i != now.to && !visited[i]) {
                        pq.add(new Road(i, map[now.to][i]));
                    }
                }
            }

            sb.append(Math.round(answer)).append('\n');
        }
        System.out.print(sb);
    }

    static class Road implements Comparable<Road> {
        int to;
        double weight;

        public Road(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Road o) {
            return Double.compare(this.weight, o.weight);
        }
    }
}
