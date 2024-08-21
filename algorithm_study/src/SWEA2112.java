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


        }
    }
}
