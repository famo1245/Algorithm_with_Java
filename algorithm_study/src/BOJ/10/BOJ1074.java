import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074 {

    static int N, r, c, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        cnt = 0;

        z(0, 0, (int) Math.pow(2, N));
    }

    static void z(int row, int col, int size) {
        if (size == 1) {
            if (row == r && col == c) {
                System.out.println(cnt);
                return;
            }
            cnt++;
            return;
        }
        
        int middle = size / 2;
        if (r < row + middle && c < col + middle) { // 1 사분면
            z(row, col, middle);
        } else if (r < row + middle && c >= col + middle) { // 2 사분면
            cnt += middle * middle;
            z(row, col + middle, middle);
        } else if (r >= row + middle && c < col + middle) { // 3 사분면
            cnt += middle * middle * 2;
            z(row + middle, col, middle);
        } else {    // 4 사분면
            cnt += middle * middle * 3;
            z(row + middle, col + middle, middle);
        }
    }
}
