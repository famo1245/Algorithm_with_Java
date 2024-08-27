import java.io.*;
import java.util.*;

public class BOJ1759 {

    static int L, C;
    static char[] codes, secret;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        secret = new char[L];
        codes = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            codes[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(codes);
        dfs(0, 0, 0);
        System.out.print(sb);
    }

    static void dfs(int index, int count, int vowel) {
        if (count == L) {
            if (vowel >= 1 && (L - vowel) >= 2) {
                for (int i = 0; i < L; i++) {
                    sb.append(secret[i]);
                }
                sb.append('\n');
            }
            return;
        }

        for (int i = index; i < C; i++) {
            secret[count] = codes[i];
            if (isVowel(codes[i])) {
                dfs(i + 1, count + 1, vowel + 1);
            } else {
                dfs(i + 1, count + 1, vowel);

            }
        }
    }

    static boolean isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
}
