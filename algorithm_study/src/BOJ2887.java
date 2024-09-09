import java.io.*;
import java.util.*;

public class BOJ2887 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N + 1][N];
        Coord[] coords = new Coord[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            coords[i] = new Coord(x, y, z);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int minDistance = Math.min(Math.abs(coords[i].x - coords[j].x), Math.min(Math.abs(coords[i].y - coords[j].y),
                                Math.abs(coords[i].z - coords[j].z)));
                map[i][j] = minDistance;
                map[j][i] = minDistance;
            }
        }

        int answer = 0;
        int count = 0;
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.add(new Road(0, 0));

        while (!pq.isEmpty()) {
            if (count == N) {
                break;
            }

            Road now = pq.poll();
            if (map[N][now.to] == 1) {
                continue;
            }

            answer += now.distance;
            map[N][now.to] = 1;
            count++;

            for (int i = 0; i < N; i++) {
                if (i != now.to && map[N][i] == 0) {
                    pq.add(new Road(i, map[now.to][i]));
                }
            }
        }

        System.out.println(answer);
    }

    static class Road implements Comparable<Road> {
        int to;
        int distance;

        public Road(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Road o) {
            return this.distance - o.distance;
        }
    }

    static class Coord {
        int x, y, z;

        public Coord(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
// 메모리 초과 해결하기 => 좌표 값 잘라내기?