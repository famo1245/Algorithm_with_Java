import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 시간 초과 최대 => O(n^2)
public class BOJ7662V2 {

    private static final String INPUT = "I";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase < T + 1; testCase++) {
            int k = Integer.parseInt(br.readLine());
            LinkedList<Integer> que = new LinkedList<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (command.equals(INPUT)) {
                    insertNum(num, que);
                } else {
                    if (!que.isEmpty()) {
                        if (num == 1) {
                            que.pollLast();
                        } else {
                            que.pollFirst();
                        }
                    }
                }
            }

            if (que.isEmpty()) {
                sb.append("EMPTY");
            } else {
                sb.append(que.getLast());
                sb.append(" ");
                sb.append(que.getFirst());
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void insertNum(int num, LinkedList<Integer> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i) >= num) {
                list.add(i, num);
                return;
            }
        }

        list.add(num);
    }
}