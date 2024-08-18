import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2961 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] sourness = new int[N];
        int[] bitter = new int[N];
        int[] bits = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sourness[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
            bits[i] = 1 << i;
        }

        int answer = Integer.MAX_VALUE;
        int ingredients = 1 << N;
        for(int select = 1; select < ingredients; select++) {
            int sourResult = 1;
            int bitterResult = 0;
            for (int i = 0; i < N; i++) {
                if ((select & bits[i]) > 0) {
                    sourResult *= sourness[i];
                    bitterResult += bitter[i];
                }
            }
            answer = Math.min(answer, Math.abs(sourResult - bitterResult));
        }

        System.out.println(answer);
    }
}
