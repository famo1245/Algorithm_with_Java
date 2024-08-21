import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {

    static int N, r, c;
    static long cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        cnt = 0;

        z(0, 0, N * N);
    }

    static void z(int row, int col, int size) {
        if (row == r && col == c) {
            System.out.println(cnt);
            return;
        }

        if (size == 1) {
            cnt++;
            return;
        }

        z(row, col, size / 2);
        z(row, col + size / 2, size / 2);
        z(row + size / 2, col, size / 2);
        z(row + size / 2, col + size / 2, size / 2);
    }
}
