import java.util.*;
import java.io.*;

public class BOJ1976V2 {

    private static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String connected = "1";
        StringTokenizer st;
        // 도시의 수
        int N = Integer.parseInt(br.readLine());
        group = new int[N];

        // union-find 사용을 위한 초기화
        for (int i = 0; i < N; i++) {
            group[i] = i;
        }

        // 여행 계획에 있는 도시의 수
        int M = Integer.parseInt(br.readLine());
        int[] routes = new int[M];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (input[j].equals(connected)) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            routes[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        boolean isPossible = true;
        for (int i = 0; i < M - 1; i++) {
            if (find(routes[i]) != find(routes[i + 1])) {
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            System.out.println("YES");
            return;
        }

        System.out.println("NO");
    }

    private static void union(int a, int b) {
        // 어느 그룹에 속하는지 찾기
        if (group[a] != a) {
            a = find(a);
        }

        if (group[b] != b) {
            b = find(b);
        }

        // 서로 다른 그룹에 속해있는 경우 표시
        if (a != b) {
            group[b] = a;
        }
    }

    // 그룹 번호 찾기
    private static int find(int num) {
        if (group[num] == num) {
            return num;
        }

        int root = find(group[num]);
        group[num] = root;
        return root;
    }
}
