import java.util.*;
import java.io.*;

public class BOJ2211 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Computer>[] network = new ArrayList[N + 1];
        int[] distance = new int[N + 1];
        int[] connected = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            network[i] = new ArrayList<>();
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            network[a].add(new Computer(b, weight));
            network[b].add(new Computer(a, weight));
        }

        PriorityQueue<Computer> pq = new PriorityQueue<>();
        distance[1] = 0;
        pq.add(new Computer(1, 0));
        while (!pq.isEmpty()) {
            Computer c = pq.poll();
            if (c.weight > distance[c.num]) {
                continue;
            }

            for (Computer next : network[c.num]) {
                if (distance[next.num] > c.weight + next.weight) {
                    distance[next.num] = c.weight + next.weight;
                    connected[next.num] = c.num;
                    pq.add(new Computer(next.num, distance[next.num]));
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= N; i++) {
            if (connected[i] == 0) {
                continue;
            }

            count++;
            sb.append(i).append(' ').append(connected[i]).append('\n');
        }

        System.out.println(count);
        System.out.print(sb);
    }

    static class Computer implements Comparable<Computer> {
        int num;
        int weight;

        public Computer(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Computer o) {
            return this.weight - o.weight;
        }
    }
}
