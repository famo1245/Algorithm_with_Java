import java.util.*;
import java.io.*;

public class BOJ5052 {

    static Map<String, Node> trie;
    static boolean isDuplicated;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < t; testCase++) {
            int n = Integer.parseInt(br.readLine());
            trie = new HashMap<>();
            isDuplicated = false;

            for (int i = 0; i < n; i++) {
                String[] input = br.readLine().split("");

                if (isDuplicated) {
                    continue;
                }

                isDuplicated = insert(input);
            }

            if (isDuplicated) {
                sb.append("NO");
            } else {
                sb.append("YES");
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }

    static boolean insert(String[] input) {
        int index = 0;
        int length = input.length;
        boolean flag = false;
        Map<String, Node> current = trie;

        while (!flag) {
            String value = input[index];

            if (current.containsKey(value)) {
                Node node = current.get(value);
                if (node.isLast || index == length - 1) {
                    flag = true;
                    continue;
                }

                current = node.next;
            } else {
                Node node = new Node(index == length - 1);
                current.put(value, node);

                if (index == length - 1) {
                    break;
                }

                current = node.next;
            }

            index++;
        }

        return flag;
    }

    static class Node {
        boolean isLast;
        Map<String, Node> next;

        public Node(boolean isLast) {
            this.isLast = isLast;
            if (!isLast) {
                next = new HashMap<>();
            }
        }
    }
}
