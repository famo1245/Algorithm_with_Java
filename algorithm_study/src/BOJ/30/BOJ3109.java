import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109 {

    static int R, C;
    static char[][] map;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        answer = 0;
        for (int i = 0; i < R; i++) {
            dfs(i, 0);
        }

        System.out.println(answer);
    }

    static private boolean dfs(int row, int col) {
        // 불가능한 경우 배제
        map[row][col] = 'x';
        if (col == C - 1) {
            answer++;
            return true;
        }

        int nextCol = col + 1;
        for (int offset = -1; offset < 2; offset++) {
            int nextRow = row + offset;

            if (nextRow >= R || nextRow < 0) {
                continue;
            }

            if (map[nextRow][nextCol] != 'x') {
                // 앞에서 연결에 성공했다면 굳이 다른 위치를 탐색할 필요 X
                boolean flag = dfs(nextRow, nextCol);

                if (flag) {
                    return true;
                }
            }
        }

        return false;
    }
}