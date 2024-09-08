import java.util.*;
import java.io.*;

public class BOJ16974 {

    static long[] length, pettyCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());

        length = new long[N + 1];
        pettyCount = new long[N + 1];
        length[0] = 1L;
        pettyCount[0] = 1L;

        for (int i = 1; i <= N; i++) {
            length[i] = length[i - 1] * 2 + 3;
            pettyCount[i] = pettyCount[i - 1] * 2 + 1;
        }

        long result = countPetty(N, X);
        System.out.println(result);
    }

    static long countPetty(int layer, long idx) {
        if (layer == 0) {
            return 1L;
        }

        if (idx == 1) {
            return 0;
        }

        if (idx <= length[layer - 1] + 1) {
            return countPetty(layer - 1, idx - 1);
        } else if (idx == length[layer - 1] + 2) {
            return pettyCount[layer - 1] + 1;
        } else if (idx <= length[layer - 1] * 2 + 2) {
            return pettyCount[layer - 1] + 1 + countPetty(layer - 1, idx - length[layer - 1] - 2);
        }

        return pettyCount[layer - 1] * 2 + 1;

    }
}
