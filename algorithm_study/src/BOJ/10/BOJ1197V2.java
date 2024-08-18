import java.io.*;
import java.util.*;

public class BOJ1197V2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");
        int V = Integer.parseInt(temp[0]);
        int E = Integer.parseInt(temp[1]);

        // 연결된 node 중 가중치 최소값을 이용
        PriorityQueue<Node> queue = new PriorityQueue<>();
        // 노드 방문 처리
        boolean[] visited = new boolean[V + 1];
        ArrayList<Node>[] G = new ArrayList[V + 1];

        for (int i = 0; i < V + 1; i++) {
            G[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            temp = br.readLine().split(" ");
            int startNode = Integer.parseInt(temp[0]);
            int endNode = Integer.parseInt(temp[1]);
            int weight = Integer.parseInt(temp[2]);

            G[startNode].add(new Node(endNode, weight));
            G[endNode].add(new Node(startNode, weight));
        }


        // 가중치의 합이 최소인 경우를 구하는 것이 최소 신장 트리
        int minimumSpanningTree = 0;
        // 최소 신장 트리에 포함된 간선(edge)의 수를 확인
        int count = 0;

        queue.add(new Node(V, 0));
        while (!queue.isEmpty()) {
            // 최소 신장 트리의 정의 => V개의 정점(vertex, node)을 가지는 그래프에서 V - 1개의 간선(edge)으로 연결
            if (count == V) {
                break;
            }

            Node now = queue.poll();

            // 방문 했을 경우 skip
            if (visited[now.node]) {
                continue;
            }

            visited[now.node] = true;
            minimumSpanningTree += now.weight;

            for (Node node : G[now.node]) {
                if (!visited[node.node]) {
                    queue.add(node);
                }
            }

            count++;
        }

        System.out.println(minimumSpanningTree);

//        혹시나 배열로 선언했지만 메모리초과
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String[] temp = br.readLine().split(" ");
//        int V = Integer.parseInt(temp[0]);
//        int E = Integer.parseInt(temp[1]);
//
//        // 연결된 node 중 가중치 최소값을 이용
//        PriorityQueue<Node> queue = new PriorityQueue<>();
//        // 노드 방문 처리
//        boolean[] visited = new boolean[V + 1];
//        Node[][] G = new Node[V + 1][V];
//        // latest index
//        int[] index = new int[V + 1];
//
//        for (int i = 0; i < E; i++) {
//            temp = br.readLine().split(" ");
//            int startNode = Integer.parseInt(temp[0]);
//            int endNode = Integer.parseInt(temp[1]);
//            int weight = Integer.parseInt(temp[2]);
//
//            G[startNode][index[startNode]++] = new Node(endNode, weight);
//            G[endNode][index[endNode]++] = new Node(startNode, weight);
//        }
//
//
//        // 가중치의 합이 최소인 경우를 구하는 것이 최소 신장 트리
//        int minimumSpanningTree = 0;
//        // 최소 신장 트리에 포함된 간선(edge)의 수를 확인
//        int count = 0;
//
//        queue.add(new Node(V, 0));
//        while (!queue.isEmpty()) {
//            // 최소 신장 트리의 정의 => V개의 정점(vertex, node)을 가지는 그래프에서 V - 1개의 간선(edge)으로 연결
//            if (count == V) {
//                break;
//            }
//
//            Node now = queue.poll();
//
//            // 방문 했을 경우 skip
//            if (visited[now.node]) {
//                continue;
//            }
//
//            visited[now.node] = true;
//            minimumSpanningTree += now.weight;
//
//            for (int i = 0; i < index[now.node]; i++) {
//                Node node = G[now.node][i];
//                if (!visited[node.node]) {
//                    queue.add(node);
//                }
//            }
//
//            count++;
//        }
//
//        System.out.println(minimumSpanningTree);
    }
}

class Node implements Comparable<Node> {
    int node;
    // 가중치
    int weight;

    public Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
