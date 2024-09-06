import java.util.*;
import java.io.*;

public class SWEA2477 {
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
            int K = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int[] reception = new int[N];
            int[] repair = new int[M];
            int[][] customers = new int[K][3];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                reception[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                repair[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                customers[i][0] = Integer.parseInt(st.nextToken()); // 도착 시간
                customers[i][1] = i + 1; // 접수 번호
            }

            int answer = 0;
            int[] waitingTime = new int[N];

            for (int i = 0; i < K; i++) {
                int receptionIndex = 0;
                for (int j = 0; j < N; j++) {
                    if (waitingTime[j] <= customers[i][0]) {
                        receptionIndex = j;
                        break;
                    } else if (waitingTime[j] < waitingTime[receptionIndex]) {
                        receptionIndex = j;
                    }
                }

                customers[i][2] = receptionIndex;
                waitingTime[receptionIndex] = Math.max(waitingTime[receptionIndex], customers[i][0])
                        + reception[receptionIndex];
                customers[i][0] = waitingTime[receptionIndex];  // 먼저 기다리는 고객 정렬용
            }
            Arrays.sort(customers, (o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return o1[2] - o2[2];
                }
                return o1[0] - o2[0];
            });

            waitingTime = new int[M];
            for (int i = 0; i < K; i++) {
                int repairIndex = 0;
                for (int j = 0; j < M; j++) {
                    if (waitingTime[j] <= customers[i][0]) {
                        repairIndex = j;
                        break;
                    } else if (waitingTime[j] < waitingTime[repairIndex]) {
                        repairIndex = j;
                    }
                }

                waitingTime[repairIndex] = Math.max(waitingTime[repairIndex], customers[i][0]) + repair[repairIndex];
                if (customers[i][2] == A && repairIndex == B) {
                    answer += customers[i][1];
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
