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

        for (int i = 1; i <= N; i++) {
            network[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            network[a].add(new Computer(b, weight));
            network[b].add(new Computer(a, weight));
        }
    }

    static class Computer {
        int num;
        int weight;

        public Computer(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }
}
