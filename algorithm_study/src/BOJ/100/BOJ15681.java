import java.io.*;
import java.util.*;

public class BOJ15681 {

    static int[] counts;
    //    static Map<Integer, List<Integer>> G;
    // list 배열이 map 보다 빠름
    static List<Integer>[] G;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        counts = new int[N + 1];
//        G = new HashMap<>();
        G = new ArrayList[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

//            List<Integer> list = G.getOrDefault(start, new ArrayList<>());
//            list.add(end);
//            G.put(start, list);
//
//            list = G.getOrDefault(end, new ArrayList<>());
//            list.add(start);
//            G.put(end, list);
            if (G[start] == null) {
                G[start] = new ArrayList<>();
            }

            G[start].add(end);

            if (G[end] == null) {
                G[end] = new ArrayList<>();
            }

            G[end].add(start);
        }

        // BFS => 메모리 초과
//        Deque<Integer> que = new ArrayDeque<>();
//        que.add(R);
//
//        // 트리 만들기
//        while (!que.isEmpty()) {
//            int parent = que.pollFirst();
//            if (G.get(parent) != null) {
//                for (int child : G.get(parent)) {
//                    G.get(child).remove((Integer) parent);
//                    que.add(child);
//                }
//            }
//        }

        // tree making
        makeTree(R);

        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            sb.append(counts[query]).append("\n");
        }

        System.out.println(sb);
    }

    private static void makeTree(int parent) {
        counts[parent]++;
//        if (G.get(parent) == null || G.get(parent).size() == 0) {
//            return;
//        }
        if (G[parent] == null || G[parent].isEmpty()) {
            return;
        }

//        for (int child : G.get(parent)) {
//            G.get(child).remove((Integer) parent);
//            makeTree(child);
//            counts[parent] += counts[child];
//        }
        for (int child : G[parent]) {
            G[child].remove((Integer) parent);
            makeTree(child);
            counts[parent] += counts[child];
        }
    }
}