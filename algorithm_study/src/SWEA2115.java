import java.util.*;
import java.io.*;

public class SWEA2115 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append('#').append(testCase).append(' ');
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] pots = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    pots[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ArrayList<Profit> profits = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    int bag = 0;
                    int profit = 0;
                    int sum = 0;
                    for (int k = 0; k < (1 << M); k++) {
                        for (int offset = 1; offset < M; offset++) {
                            if ((offset & k) == 0) {
                                continue;
                            }

                            int honey = pots[i][j + offset - 1];
                            if (bag + honey > C) {
                                break;
                            }

                            bag += honey;
                            sum += honey * honey;
                        }
//                        System.out.print(sum + " ");
                        profit = Math.max(profit, sum);
                    }
//                    System.out.println();
//                    
//                    for (int k = j; k < j + M; k++) {
//                        if (bag + pots[i][k] > C) {
//                            profit = Math.max(profit, sum);
//                            bag = 0;
//                            sum = 0;
//                        }
//                        
//                        bag += pots[i][k];
//                        sum += pots[i][k] * pots[i][k];
////                        System.out.print(sum + " ");
//                    }
//                    
//                    profit = Math.max(profit, sum);
                    System.out.print(profit + " ");
                    profits.add(new Profit(i, j, j + M - 1, profit));
                }
                System.out.println();
            }

            PriorityQueue<Profit> pq = new PriorityQueue<>(profits);
            Profit profit1 = pq.poll();
            Profit profit2 = pq.poll();
            System.out.println(profit2);
            while (!pq.isEmpty()) {
                if (!isDuplicated(profit1, profit2)) {
                    System.out.println("==============");
                    System.out.println(profit1);
                    System.out.println(profit2);
                    sb.append(profit1.profit + profit2.profit).append('\n');
                    break;
                }

                profit2 = pq.poll();
                System.out.println(profit2);
            }
        }

        System.out.println(sb);
    }

    static boolean isDuplicated(Profit p1, Profit p2) {
        boolean isSameRow = p1.startRow == p2.startRow;
        boolean isDuplicatedCol = p1.endCol >= p2.startCol;
        return isSameRow && isDuplicatedCol;
    }

    static class Profit implements Comparable<Profit> {
        int startRow;
        int startCol;
        int endCol;
        int profit;

        public Profit(int startRow, int startCol, int endCol, int profit) {
            this.startRow = startRow;
            this.startCol = startCol;
            this.endCol = endCol;
            this.profit = profit;
        }

        @Override
        public int compareTo(Profit o) {
            if (this.profit == o.profit) {
                return this.endCol - o.endCol;
            }
            return o.profit - this.profit;
        }

        @Override
        public String toString() {
            return "Profit [startRow=" + startRow + ", startCol=" + startCol + ", endCol=" + endCol + ", profit="
                    + profit + "]";
        }

    }
}