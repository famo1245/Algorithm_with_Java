import java.io.*;

public class BOJ2023 {

    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        getNums(2);
        getNums(3);
        getNums(5);
        getNums(7);

        System.out.println(sb);
    }

    static boolean isPrime(int n) {
        for (int i = 2; i < Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    static void getNums(int n) {
        if (Integer.toString(n).length() == N) {
            sb.append(n).append("\n");
            return;
        }

        for (int i = 1; i < 10; i += 2) {
            int next = n * 10 + i;
            if (isPrime(next)) {
                getNums(next);
            }
        }
    }
}
