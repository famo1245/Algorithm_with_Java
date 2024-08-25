import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ31476 {

    static int D, U, T, height;
    static boolean[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        height = (int) Math.pow(2, D) - 1;
        tree = new boolean[height + 1];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            tree[Integer.parseInt(input[1])] = false;
        }

        int twin = getTwinTime(1);
        int pony = getPonyTime(1);

        if (twin == pony) {
            System.out.println(":blob_twintail_thinking:");
        } else if (twin < pony) {
            System.out.println(":blob_twintail_aww:");
        } else {
            System.out.println(":blob_twintail_sad:");
        }
    }

    static int getTwinTime(int idx) {
//        if (idx == height) {
//            return
//        }
        return 1;
    }

    static int getPonyTime(int idx) {
        if (idx == height) {
            return U;
        }

        int time = 0;
        if (tree[idx * 2]) {
            time += U + getPonyTime(idx * 2);
        }

        if (tree[idx * 2 + 1]) {
            time += U + getPonyTime(idx * 2);
        }

        return U + time;
    }
}
