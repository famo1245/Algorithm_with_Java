import java.io.*;
import java.util.*;

public class BOJ15686 {

    static final int BLANK = 0;
    static final int HOME = 1;
    static final int CHICKEN = 2;

    static List<int[]> homeCord, chickenCord;
    static ArrayDeque<Integer> que;
    static int N, M, minChickenDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        que = new ArrayDeque<>();
        homeCord = new ArrayList<>();
        chickenCord = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int element = Integer.parseInt(st.nextToken());
                if (element == CHICKEN) {
                    chickenCord.add(new int[] {i, j});
                } else if (element == HOME) {
                    homeCord.add(new int[] {i, j});
                }
            }
        }

        minChickenDistance = Integer.MAX_VALUE;
        findMinDistance(0, 0);
        System.out.println(minChickenDistance);
    }

    static void findMinDistance(int row, int col) {
        if (row > chickenCord.size()) {
            return;
        }

        if (col == M) {
            int result = 0;
            for (int[] cord : homeCord) {
                int distance = Integer.MAX_VALUE;
                int rowHome = cord[0];
                int colHome = cord[1];

                for (int index : que) {
                    int[] chicken = chickenCord.get(index);
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

        que.push(row);
        findMinDistance(row + 1, col + 1);
        que.pop();
        findMinDistance(row + 1, col);
    }
}