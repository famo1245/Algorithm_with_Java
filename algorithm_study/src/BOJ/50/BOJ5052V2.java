import java.io.*;

// 배열을 이용하여 Map보다 속도 증가
public class BOJ5052V2 {

    static final int WORD_SIZE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < t; testCase++) {
            int n = Integer.parseInt(br.readLine());
            Node[] trie = new Node[WORD_SIZE];
            boolean isDuplicated = false;

            for (int i = 0; i < n; i++) {
                String input = br.readLine();

                if (isDuplicated) {
                    continue;
                }

                isDuplicated = insert(input, trie);
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

    static boolean insert(String input, Node[] trie) {
        int length = input.length();
        boolean flag = false;
        Node[] current = trie;

        for (int i = 0; i < length; i++) {
            int value = input.charAt(i) - '0';

            Node node;
            if (current[value] != null) {
                node = current[value];

                if (node.isLast || i == length - 1) {
                    flag = true;
                    break;
                }

            } else {
                node = new Node(i == length - 1);
                current[value] = node;
            }
            current = node.trie;
        }

        return flag;
    }

    static class Node {
        boolean isLast;
        Node[] trie;

        public Node(boolean isLast) {
            this.isLast = isLast;
            this.trie = new Node[WORD_SIZE];
        }
    }
}
