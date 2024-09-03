import java.util.*;
import java.io.*;

public class SWEA2117 {

    static final int HOUSE = 1;

    static int[][] map, houses;
    static int N, M, K, answer, houseSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = N + 2;
            map = new int[N][N];
            houses = new int[N * N][2];

            houseSize = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    map[i][j] = value;

                    if (value == HOUSE) {
                        houses[houseSize][0] = i;
                        houses[houseSize][1] = j;
                        houseSize++;
                    }
                }
            }

            answer = 0;
            calcValue();
            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void calcValue() {
        while (K > 0) {
            int pay = K * K + (K - 1) * (K - 1);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int count = 0;
                    for (int index = 0; index < houseSize; index++) {
                        int row = houses[index][0];
                        int col = houses[index][1];

                        if (Math.abs(row - i) + Math.abs(col - j) < K) {
                            count++;
                        }
                    }

                    if (count * M - pay >= 0) {
                        answer = Math.max(answer, count);
                    }
                }
            }
            K--;
        }
    }
}
