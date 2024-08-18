import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
* 시간 초과
* LinkedList에 대한 정렬 => O(nlogn)
* LinkedList에 insert 할 때 위치를 미리 잡기?
* */
public class BOJ7662 {

    private static final String INPUT = "I";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase < T + 1; testCase++) {
            int k = Integer.parseInt(br.readLine());
            LinkedList<Integer> que = new LinkedList<>();
            boolean isSorted = false;

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (command.equals(INPUT)) {
                    que.add(num);
                    isSorted = false;
                } else {
                    if (!que.isEmpty()) {
                        if (!isSorted) {
                            Collections.sort(que);
                            isSorted = true;
                        }
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
                if (!isSorted) {
                    Collections.sort(que);
                }
                sb.append(que.getLast());
                sb.append(" ");
                sb.append(que.getFirst());
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
