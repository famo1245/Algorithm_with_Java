import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1952 {

    static final int TYPE_OF_FEE = 4;
    static final int MONTH = 12;

    static int[] fee, month;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");

            fee = new int[TYPE_OF_FEE];
            month = new int[MONTH];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < TYPE_OF_FEE; i++) {
                fee[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < MONTH; i++) {
                month[i] = Integer.parseInt(st.nextToken());
            }

            // 1년권을 기준으로 비교 시작
            answer = fee[TYPE_OF_FEE - 1];
            dfs(0, 0);
            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }

    static void dfs(int m, int sum) {
        if (m == MONTH) {   // 종료 조건
            answer = Math.min(answer, sum);
            return;
        }

        // 이미 최솟값을 넘어버린 경우 가지치기
        if (answer <= sum) {
            return;
        }

        // 1일권
        dfs(m + 1, sum + fee[0] * month[m]);
        // 1달권
        dfs(m + 1, sum + fee[1]);
        // 3달권
        if (m + 3 <= MONTH) {
            dfs(m + 3, sum + fee[2]);
        }
    }
}
