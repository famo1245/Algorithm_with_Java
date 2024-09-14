import java.io.*;

public class BOJ5639 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Node root = new Node(Integer.parseInt(input));
        Node current = root;

        while ((input = br.readLine()) != null) {
            Node node = new Node(Integer.parseInt(input));
            add(current, node);
            current = node;
        }

        postOrder(root);
        System.out.print(sb);
    }

    static void add(Node current, Node node) {
        if (current.key > node.key) {
            if (current.left == null) {
                current.left = node;
                node.smallestParent = current;
                return;
            }

            add(current.left, node);
            return;
        }

        if (current.smallestParent != null && current.smallestParent.key < node.key) {
            add(current.smallestParent, node);
            return;
        }

        if (current.right == null) {
            current.right = node;
            node.smallestParent = current.smallestParent;
            return;
        }

        add(current.right, node);
    }

    static void postOrder(Node node) {
        if (node.left != null) {
            postOrder(node.left);
        }

        if (node.right != null) {
            postOrder(node.right);
        }

        sb.append(node.key).append('\n');
    }

    static class Node {
        int key;
        Node left;
        Node right;
        Node smallestParent;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.smallestParent = null;
        }
    }
}
