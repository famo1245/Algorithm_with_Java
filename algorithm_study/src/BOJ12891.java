import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12891 {

    static int[] requiredDNA;
    static int S, P, answer;
    static String input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        input = br.readLine();

        st = new StringTokenizer(br.readLine());
        requiredDNA = new int['Z'];

        requiredDNA['A'] = Integer.parseInt(st.nextToken());
        requiredDNA['C'] = Integer.parseInt(st.nextToken());
        requiredDNA['G'] = Integer.parseInt(st.nextToken());
        requiredDNA['T'] = Integer.parseInt(st.nextToken());

        int idx = 0;
        int size = 0;
        while(idx < S) {
            char dna = input.charAt(idx++);
            requiredDNA[dna]--;
            size++;

            if (size == P) {
                isValidSecret();
                char removed = input.charAt(idx - P);
                requiredDNA[removed]++;
                size--;
            }
        }

        System.out.println(answer);
    }

    private static void isValidSecret() {
        if (requiredDNA['A'] <= 0 && requiredDNA['C'] <= 0 && requiredDNA['G'] <= 0 && requiredDNA['T'] <= 0) {
            answer++;
        }
    }
}
