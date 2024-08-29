import java.io.*;
import java.util.*;

public class BOJ15686 {

    static final int HOME = 1;
    static final int CHICKEN = 2;
	static final int MAX_CHICKEN = 13;

	static int[][] chickenCoord, homeCoord;
    static ArrayDeque<Integer> que;
    static int N, M, minChickenDistance, chickenSize, homeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        que = new ArrayDeque<>();
		homeCoord = new int[2 * N + 1][];
		chickenCoord = new int[MAX_CHICKEN][];
		chickenSize = 0;
		homeSize = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int element = Integer.parseInt(st.nextToken());
                if (element == CHICKEN) {
					chickenCoord[chickenSize++] = new int[] {i, j};
                } else if (element == HOME) {
					homeCoord[homeSize++] = new int[] {i, j};
                }
            }
        }

        minChickenDistance = Integer.MAX_VALUE;
        findMinDistance(0, 0);
        System.out.println(minChickenDistance);
    }

    static void findMinDistance(int index, int count) {
        if (index > chickenSize) {
            return;
        }

        if (count == M) {
            int result = 0;
            for (int i = 0; i < homeSize; i++) {
                int distance = Integer.MAX_VALUE;
                int[] coord = homeCoord[i];
                int rowHome = coord[0];
                int colHome = coord[1];

                for (int idx : que) {
                    int[] chicken = chickenCoord[idx];
                    int rowChicken = chicken[0];
                    int colChicken = chicken[1];
                    distance = Math.min(distance,
                            Math.abs(rowHome - rowChicken) + Math.abs(colHome - colChicken));
                }
                result += distance;
            }

            minChickenDistance = Math.min(minChickenDistance, result);
            return;
        }

        que.push(index);
        findMinDistance(index + 1, count + 1);
        que.pop();
        findMinDistance(index + 1, count);
    }
}