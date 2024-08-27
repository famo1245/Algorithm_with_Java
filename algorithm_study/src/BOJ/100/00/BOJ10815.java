import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            sb.append(findNum(cards, num)).append(" ");
        }

        System.out.println(sb);
    }

    static int findNum(int[] cards, int num) {
        int start = 0;
        int end = cards.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (cards[mid] == num) {
                return 1;
            } else if (cards[mid] < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return 0;
    }

    // BitSet을 쓰는 미친 풀이
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        int N = Integer.parseInt(br.readLine());
//        BitSet set = new BitSet(20000010);
//
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < N; i++) {
//            set.set(Integer.parseInt(st.nextToken()) + 10000000);
//        }
//
//        int M = Integer.parseInt(br.readLine());
//        StringBuilder sb = new StringBuilder();
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < M; i++) {
//            int num = Integer.parseInt(st.nextToken()) + 10000000;
//            sb.append(set.get(num) ? 1 : 0).append(" ");
//        }
//
//        System.out.println(sb);
//    }
}
