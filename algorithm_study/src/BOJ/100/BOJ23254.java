import java.util.*;
import java.io.*;

public class BOJ23254 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        N *= 24;
        int M = Integer.parseInt(st.nextToken());
        int maxScore = 0;

        String[] a = br.readLine().split(" ");
        String[] b = br.readLine().split(" ");
        PriorityQueue<Subject> q = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            q.add(new Subject(Integer.parseInt(a[i]), Integer.parseInt(b[i])));
        }

        while (N > 0) {
            if (q.isEmpty()) {
                break;
            }

            Subject s = q.poll();
            int maxStudyTime = (100 - s.baseScore) / s.acquireScore;
            int calcScore = (maxStudyTime * s.acquireScore) + s.baseScore;
            if (maxStudyTime < N) {
                if (calcScore < 100) {
                    q.add(new Subject(calcScore, 100 - calcScore));
                    N -= maxStudyTime;
                    continue;
                }
            } else {
                maxStudyTime = N;
                calcScore = N * s.acquireScore + s.baseScore;
                calcScore = calcScore > 100 ? 100 : calcScore;
            }

            maxScore += calcScore;
            N -= maxStudyTime;
        }

        if (!q.isEmpty()) {
            while (!q.isEmpty()) {
                Subject s = q.poll();
                maxScore += s.baseScore;
            }
        }

        System.out.println(maxScore);
    }
}

class Subject implements Comparable<Subject> {

    int baseScore;
    int acquireScore;

    public Subject(int baseScore, int acquireScore) {
        this.baseScore = baseScore;
        this.acquireScore = acquireScore;
    }

    @Override
    public int compareTo(Subject o) {
        if (acquireScore == o.acquireScore) {
            return -Integer.compare(baseScore, o.baseScore);
        }
        return -Integer.compare(acquireScore, o.acquireScore);
    }
}