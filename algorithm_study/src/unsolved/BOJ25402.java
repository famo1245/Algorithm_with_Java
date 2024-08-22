package unsolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ25402 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] tree = new ArrayList[n + 1];

        for(int i = 1; i < n + 1; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        int query = Integer.parseInt(br.readLine());
        for(int q = 0; q < query; q++){
            st = new StringTokenizer(br.readLine());

            int nSet = Integer.parseInt(st.nextToken());

            HashSet<Integer> hs = new HashSet<>();
            for(int i = 0; i < nSet; i++){
                int include = Integer.parseInt(st.nextToken());
                hs.add(include);
            }

            boolean[] visited = new boolean[n + 1];
            int answer = 0;
            ArrayDeque<Integer> que = new ArrayDeque<>();

            // BFS로 S에 포함된 노드 탐색 -> 시간이 오래 걸린다
            // 이전에 푼 트리와 쿼리에서 메모이제이션을 이용하면 빠르지 않을까?
            // union find를 이용해서 최적화를 하지 않으면 트리 모양이 되는데 이용할 수 있지 않을까 고민
            // 결국 이 부분이 O(N^2)이 될 것 같아 시간 초과를 해결하려면 이 부분을 손 봐야함
            for (int i : hs) {
                if (visited[i]) continue;
                if (hs.contains(i)) {
                    int elementCount = 1;
                    visited[i] = true;
                    que.add(i);

                    while (!que.isEmpty()) {
                        int node = que.poll();
                        for (int e : tree[node]) {
                            if (!visited[e] && hs.contains(e)) {
                                visited[e] = true;
                                elementCount++;
                                que.add(e);
                            }
                        }
                    }
                    answer += (elementCount * (elementCount - 1)) / 2;
                }
            }

            // 조합 합 출력해주기
            sb.append(answer).append("\n");

        }

        System.out.println(sb);
    }
}