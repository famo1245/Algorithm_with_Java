import java.io.*;
import java.util.*;

public class BOJ1197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");
        int V = Integer.parseInt(temp[0]);
        int E = Integer.parseInt(temp[1]);

        // edge list 이용
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        // kruskal 알고리즘 이용 => union-find 알고리즘을 써서 cycle 확인
        int[] connected = new int[V + 1];

        // 초기화
        for (int i = 0; i < E; i++) {
            temp = br.readLine().split(" ");
            edges.add(new Edge(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
        }

        for (int i = 1; i < V + 1; i++) {
            connected[i] = i;
        }

        // 가중치의 합이 최소인 경우를 구하는 것이 최소 신장 트리
        int minimumSpanningTree = 0;
        // 최소 신장 트리에 포함된 간선(edge)의 수를 확인
        int count = 0;

        // 최소 신장 트리의 정의 => V개의 정점(vertex, node)을 가지는 그래프에서 V - 1개의 간선(edge)으로 연결
        while (count < V - 1) {
            Edge now = edges.poll();

            // 두 간선(edge)가 cycle을 형성하는지 확인
            if (find(now.startNode, connected) != find(now.endNode, connected)) {
                // 아닐 경우 union을 통해 그룹화
                union(now.startNode, now.endNode, connected);
                minimumSpanningTree += now.weight;
                count++;
            }
        }

        System.out.println(minimumSpanningTree);
    }

    static void union(int node1, int node2, int[] connected) {
        if (node1 != connected[node1]) {
            node1 = find(node1, connected);
        }

        if (node2 != connected[node2]) {
            node2 = find(node2, connected);
        }

        if (node1 > node2) {
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }

        if (node1 != node2) {
            connected[node1] = node2;
        }
    }

    static int find(int node, int[] connected) {
        if (connected[node] == node) {
            return node;
        }

        int root = find(connected[node], connected);
        connected[node] = root;
        return root;
    }
}

class Edge implements Comparable<Edge> {
    int startNode;
    int endNode;
    // 가중치
    int weight;

    public Edge(int startNode, int endNode, int weight) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}
