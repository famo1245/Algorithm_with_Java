import java.util.*;
import java.io.*;

public class BOJ15787 {

    static final int GET_IN = 1;
    static final int GET_OFF = 2;
    static final int MOVE_BACKWARD = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] train = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken()) - 1;
            int x;

            if (command == GET_IN) {
                x = Integer.parseInt(st.nextToken());
                train[index] |= (1 << x);
            } else if (command == GET_OFF) {
                x = Integer.parseInt(st.nextToken());
                train[index] &= ~(1 << x);
            } else if (command == MOVE_BACKWARD) {
                train[index] = train[index] << 1;
                train[index] &= (1 << 21) - 1;
            } else {
                train[index] = train[index] >> 1;
                train[index] &= ~1;
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(train[i]);
        }

        System.out.println(set.size());
    }
}