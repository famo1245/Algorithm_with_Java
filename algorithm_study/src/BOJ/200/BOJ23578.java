import java.io.*;
import java.util.*;

public class BOJ23578 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<PriorityQueue<Node>> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.peek().diff));

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            PriorityQueue<Node> temp = new PriorityQueue<>();
            temp.add(new Node(Integer.parseInt(st.nextToken())));
            pq.add(temp);
        }

        while (pq.size() > 1) {
            PriorityQueue<Node> q1 = pq.poll();
            PriorityQueue<Node> q2 = pq.poll();

            Node a = q1.poll();
            Node b = q2.poll();

            a.calcDiff();
            b.calcDiff();

            q1.add(a);
            q2.add(b);

            if (q1.size() > q2.size()) {
                concat(q1, q2);
            } else {
                concat(q2, q1);
                q1 = q2;
            }

            pq.add(q1);
        }

        long answer = 0;
        for (Node now : pq.poll()) {
            answer += now.anger;
        }

        System.out.println(answer);
    }

    static void concat(PriorityQueue<Node> q1, PriorityQueue<Node> q2) {
        while(!q2.isEmpty()) {
            q1.add(q2.poll());
        }
    }

    static class Node implements Comparable<Node> {
        int studentCount;
        long anger;
        long expectedAnger;
        long diff;
        int edgeCount;

        public Node(int studentCount) {
            this.studentCount = studentCount;
            this.diff = studentCount;
            this.expectedAnger = (long) (this.edgeCount + 1) * (this.edgeCount + 1) * this.studentCount;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.diff, o.diff);
        }

        public void calcDiff() {
            this.edgeCount++;
            this.anger = this.expectedAnger;
            this.expectedAnger = (long) (this.edgeCount + 1) * (this.edgeCount + 1) * this.studentCount;
            this.diff = this.expectedAnger - this.anger;
        }
    }
}
