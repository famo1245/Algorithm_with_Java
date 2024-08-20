import java.io.*;
import java.util.*;

public class BOJ16926 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        // 작은 수의 절반만큼 쌓임
        int layers = Math.min(N, M) / 2;
        int[][] array = new int[N][M];
        int[][] answer = new int[N][M];
        Deque<Integer> que = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 층마다 데크에 집어넣고 돌리기
        for (int i = 0; i < layers; i++) {
            // 윗줄 넣기
            for (int j = i; j < M - i; j++) {
                que.add(array[i][j]);
            }

            // 오른쪽 줄 넣기
            for (int j = i + 1; j < N - i; j++) {
                que.add(array[j][M - i - 1]);
            }

            // 아래줄 넣기
            for (int j = M - i - 2; j >= i; j--) {
                que.add(array[N - i - 1][j]);
            }

            // 왼쪽 줄 넣기
            for (int j = N - i - 2; j > i; j--) {
                que.add(array[j][i]);
            }

            // 회전
            for (int r = 0; r < R % que.size(); r++) {
                que.add(que.poll());
            }

            // 윗줄 넣기
            for (int j = i; j < M - i; j++) {
                answer[i][j] = que.poll();
            }

            // 오른쪽 줄 넣기
            for (int j = i + 1; j < N - i; j++) {
                answer[j][M - i - 1] = que.poll();
            }

            // 아래줄 넣기
            for (int j = M - i - 2; j >= i; j--) {
                answer[N - i - 1][j] = que.poll();
            }

            // 왼쪽 줄 넣기
            for (int j = N - i - 2; j > i; j--) {
                answer[j][i] = que.poll();
            }

            que.clear();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
