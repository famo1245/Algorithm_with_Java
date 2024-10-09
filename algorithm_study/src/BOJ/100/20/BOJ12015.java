import java.io.*;
import java.util.*;

public class BOJ12015 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int[] D = new int[N + 1];
        int length = 0;
        for (int i = 0; i < N; i++) {
            if (sequence[i] > D[length]) {
                D[++length] = sequence[i];
            } else {
                int index = binarySearch(0, length, sequence[i], D);
                D[index] = sequence[i];
            }
        }

        System.out.print(length);
    }

    static int binarySearch(int start, int end, int key, int[] D) {
        if (start >= end) {
            return end;
        }

        int mid = (start + end) / 2;
        if (D[mid] < key) {
            return binarySearch(mid + 1, end, key, D);
        } else {
            return binarySearch(start, mid, key, D);
        }
    }
}
