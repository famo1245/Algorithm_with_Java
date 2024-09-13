import java.io.*;

public class BOJ5639 {

    static StringBuilder sb = new StringBuilder();
    static Node current;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Node root = new Node(Integer.parseInt(input));
        current = root;

        while ((input = br.readLine()) != null) {
            Node node = new Node(Integer.parseInt(input));
            add(node);
        }

        postOrder(root);
        System.out.print(sb);
    }

    static void add(Node node) {
        System.out.println(current.key + " " + node.key);
        if (current.key > node.key) {
            if (current.left == null) {
                current.left = node;
                node.parent = current;
                current = node;
            } else {
                current = current.left;
                add(node);
            }
        } else {
            if (current.parent != null && current.parent.key < node.key) {
                current = current.parent;
                add(node);
                return;
            }

            if (current.right == null) {
                current.right = node;
                node.parent = current;
                current = node;
            } else {
                System.out.println("right: " + current.key + " " + current.right.key);
                current = current.right;
                add(node);
            }
        }
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
        Node parent;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
}
