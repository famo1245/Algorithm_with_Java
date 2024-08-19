import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4008 {

    static int N, min, max;
    static int[] numbers, operators;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            N = Integer.parseInt(br.readLine());

            numbers = new int[N];
            operators = new int[N - 1];

            st = new StringTokenizer(br.readLine());
            int index = 0;
            for (int i = 0; i < 4; i++) {
                operators[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

//            do {
//                do {
//                    int result = numbers[0];
//                    for (int i = 1; i < N; i ++) {
//                        int operand = numbers[i];
//                        int operator = operators[i - 1];
//
//                        if (operator == 1) {
//                            result += operand;
//                        } else if (operator == 2) {
//                            result -= operand;
//                        } else if (operator == 3) {
//                            result *= operand;
//                        } else {
//                            result /= operand;
//                        }
//                    }
//
//                    min = Math.min(min, result);
//                    max = Math.max(max, result);
//                } while (nextPermutations(operators));
//            } while(nextPermutations(numbers));

            sb.append(max - min).append("\n");
        }

        System.out.println(sb);
    }

    static void getResults(int count, int index) {
        if (count == N) {
            int result = numbers[0];
            for (int i = 1; i < N; i++) {
                int operand = numbers[i];
                int operator = operators[i - 1];

                if (operator == 1) {
                    result += operand;
                } else if (operator == 2) {
                    result -= operand;
                } else if (operator == 3) {
                    result *= operand;
                } else {
                    result /= operand;
                }
            }

            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (i >= N - 1) {
                if (selectedNum[i]) {
                    continue;
                }

                selectedNum[i] = true;
                getResults(count + 1, i + 1);
                selectedNum[i] = false;
            } else {
                if (se)
            }
        }
    }

    // TLE
    static boolean nextPermutations(int[] p) {
        int n = p.length;

        int i = n - 1;
        while (i > 0 && p[i - 1] >= p[i]) {
            --i;
        }

        if (i == 0) {
            return false;
        }

        int j = n - 1;
        while (p[i - 1] >= p[j]) {
            --j;
        }

        swap(p, i - 1, j);

        int k = n - 1;
        while (i < k) {
            swap(p, i++, k--);
        }

        return true;
    }

    static void swap(int[] p, int i, int j) {
        int temp = p[i];
        p[i] = p[j];
        p[j] = temp;
    }
}
