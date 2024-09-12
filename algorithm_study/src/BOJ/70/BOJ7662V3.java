import java.util.*;
import java.io.*;

// 통과
public class BOJ7662V3 {

    static final String INPUT = "I";
    static final int FROM_MAX = 1;

    static PriorityQueue<Node> min, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        min = new PriorityQueue<>();
        max = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < T; i++) {
            int Q = Integer.parseInt(br.readLine());
            for (int j = 0; j < Q; j++) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                if (command.equals(INPUT)) {
                    Node data = new Node(Integer.parseInt(st.nextToken()));
                    min.add(data);
                    max.add(data);
                } else {
                    int from = Integer.parseInt(st.nextToken());
                    Node deleted;
                    if (from == FROM_MAX) {
                        deleted = getValue(max);
                    } else {
                        deleted = getValue(min);
                    }
                    deleted.isDeleted = true;
                }
            }

            // check
            Node minValue = getValue(min);
            Node maxValue = getValue(max);

            if (minValue.isDeleted) {
                sb.append("EMPTY");
            } else {
                sb.append(maxValue.value).append(' ').append(minValue.value);
            }
            sb.append('\n');

            min.clear();
            max.clear();
        }

        System.out.print(sb);
    }

    static Node getValue(PriorityQueue<Node> heap) {
        Node result = new Node(0);
        result.isDeleted = true;
        while (!heap.isEmpty()) {
            result = heap.poll();

            if (!result.isDeleted) {
                return result;
            }
        }

        return result;
    }

    static class Node implements Comparable<Node> {

        int value;
        boolean isDeleted;

        public Node(int value) {
            super();
            this.value = value;
            this.isDeleted = false;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }

    }
}