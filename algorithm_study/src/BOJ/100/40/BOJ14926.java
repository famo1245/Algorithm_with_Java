import java.io.*;

public class BOJ14926 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        boolean[][] G = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    G[i][j] = true;
                }
            }
        }


        G[0][N - 1] = G[N - 1][0] = true;
        int now = 0;
        for (int i = 0; i < N * (N - 1) / 2; i++) {
            sb.append("a").append(now + 1).append(' ');
            for (int j = 0; j < N; j++) {
                if (G[now][j]) {
                    continue;
                }

                G[now][j] = G[j][now] = true;
                now = j;
                break;
            }
        }

        sb.append("a1");
        System.out.print(sb);
    }
}
