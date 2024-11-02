import java.io.*;
import java.util.*;

public class BOJ1011 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int distance = y - x;
            int maxSpeed = (int) Math.sqrt(distance);
            int standard = maxSpeed * maxSpeed;

            if (distance == standard) {
                sb.append(maxSpeed * 2 - 1);
            } else if (distance <= standard + maxSpeed) {
                sb.append(maxSpeed * 2);
            } else {
                sb.append(maxSpeed * 2 + 1);
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }
}
