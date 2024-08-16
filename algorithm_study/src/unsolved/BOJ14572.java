package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14572 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        Student[] students = new Student[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int nums = Integer.parseInt(st.nextToken());
            int capacity = Integer.parseInt(st.nextToken());
            BitSet algorithms = new BitSet();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < nums; j++) {
                algorithms.set(Integer.parseInt(st.nextToken()));
            }

            students[i] = new Student(capacity, algorithms);
        }

        Arrays.sort(students);

        int start = 0;
        int end = 0;

        int maxEfficiency = 0;
        BitSet knownToAllAlgorithms = new BitSet(K + 1);
        knownToAllAlgorithms.flip(0, K + 1);
        BitSet allAlgorithms = new BitSet();

        while (end < N) {
            if (students[end].capacity - students[start].capacity <= D) {
                knownToAllAlgorithms.and(students[end].algorithms);
                allAlgorithms.or(students[end].algorithms);
                maxEfficiency = Math.max(maxEfficiency, (allAlgorithms.cardinality() - knownToAllAlgorithms.cardinality())*(end-start + 1));
                end += 1;
                continue;
            }
            students[start].algorithms.andNot(knownToAllAlgorithms);
            allAlgorithms.andNot(students[start].algorithms);
            students[start].algorithms.flip(0,K+1);
            knownToAllAlgorithms.or(students[start].algorithms);
            start += 1;
        }
        System.out.println(maxEfficiency);
    }

    static class Student implements Comparable<Student> {
        int capacity;
        BitSet algorithms;

        public Student(int capacity, BitSet algorithms) {
            this.capacity = capacity;
            this.algorithms = algorithms;
        }

        @Override
        public int compareTo(Student o) {
            return capacity - o.capacity;
        }
    }
}
