import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952V2 {

    static final int TYPE_OF_FEE = 4;
    static final int MONTH = 13;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");

            int[] fee = new int[TYPE_OF_FEE];
            int[] month = new int[MONTH];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < TYPE_OF_FEE; i++) {
                fee[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < MONTH; i++) {  // 계산의 편의를 위해 1부터 시작
                month[i] = Integer.parseInt(st.nextToken());
            }

            int yearly = fee[3];
            for (int i = 1; i < 13; i++) {
                // month[1]부터 초기화 하여 여기서 month[i - 1]에 대한 예외 처리 불필요
                int daily = month[i] * fee[0] + month[i - 1];
                int monthly = fee[1] + month[i - 1];
                int quarter = Integer.MAX_VALUE;

                if (i >= 3) {   // 3월부터 적용 가능
                    quarter = fee[2] + month[i - 3];
                }

                month[i] = Math.min(Math.min(daily, monthly), Math.min(quarter, yearly));
            }

            sb.append(month[12]).append("\n");
        }

        System.out.println(sb);
    }
}
