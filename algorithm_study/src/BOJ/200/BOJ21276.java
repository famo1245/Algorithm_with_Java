import java.util.*;
import java.io.*;

public class BOJ21276 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] degree = new int[N];
        List<Integer>[] family = new ArrayList[N];
        List<Integer>[] directFamily = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            family[i] = new ArrayList<>();
            directFamily[i] = new ArrayList<>();
        }

        String[] indexTable = br.readLine().split(" ");
        Arrays.sort(indexTable);
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int descendant = Arrays.binarySearch(indexTable, st.nextToken());
            int ancestor = Arrays.binarySearch(indexTable, st.nextToken());

            family[ancestor].add(descendant);
            degree[descendant]++;
        }

        List<Integer> root = new ArrayList<>();
        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (degree[i] == 0) {
                root.add(i);
                que.add(i);
            }
        }

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int descendant : family[now]) {
                degree[descendant]--;

                if (degree[descendant] == 0) {
                    directFamily[now].add(descendant);
                    que.add(descendant);
                }
            }
        }

        sb.append(root.size()).append('\n');
        Collections.sort(root);
        for (int now : root) {
            sb.append(indexTable[now]).append(' ');
        }
        sb.append('\n');

        for (int i = 0; i < N; i++) {
            sb.append(indexTable[i]).append(' ');
            sb.append(directFamily[i].size()).append(' ');

            if (!directFamily[i].isEmpty()) {
                // 자식 리스트도 이름 순 정렬
                Collections.sort(directFamily[i]);
                for (int descendant : directFamily[i]) {
                    sb.append(indexTable[descendant]).append(' ');
                }
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }
}
