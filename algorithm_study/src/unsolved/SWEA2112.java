package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

public class SWEA2112 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");

            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            BitSet[] films = new BitSet[D];
            for (int i = 0; i < D; i++) {
                BitSet temp = new BitSet(W);
                String input= br.readLine();

                for (int j = 0; j < W; j++) {
                    if (input.charAt(j) == '1') {
                        temp.set(j);
                    }
                }

                films[i] = temp;
            }

            int answer = 0;
            boolean halt = false;

            for (int i = 0; i < D; i++) {
                int[] index = new int[D];
                for (int j = D - 1; j > D - i - 1; j--) {
                    index[j] = 1;
                }

                do {
                    int possiblity = 1 << i;
                    for (int solution = 0; solution < possiblity; solution++) {
                        if (isPossible(i, solution)) {
                            answer = i;
                            halt = true;
                            break;
                        }
                    }

                    if (halt) {
                        break;
                    }
                } while(nextPermutation(index));

                if (halt) {
                    break;
                }
            }

            sb.append(answer).append("\n");
        }
    }

    static boolean isPossible(int select, int solution) {
        return false;
    }

    static boolean nextPermutation(int[] index) {
        int n = index.length;

        int i = n - 1;
        while (i > 0 && index[i - 1] >= index[i]) {
            --i;
        }

        if (i == 0) {
            return false;
        }

        int j = n - 1;
        while (index[i - 1] >= index[j]) {
            --j;
        }

        swap(index, i - 1, j);

        int k = n - 1;
        while (i < k) {
            swap(index, i++, k--);
        }
        return true;
    }

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}