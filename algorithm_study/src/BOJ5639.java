import java.util.*;
import java.io.*;

public class BOJ5639 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        int root = Integer.parseInt(input);
        Queue<Integer> que = new ArrayDeque<>();

        while ((input = br.readLine()) != null) {
            que.add(Integer.parseInt(input));
        }


    }

    static void postOrder() {

    }

    static class Node {
        int value;
        Node left;
        Node right;
    }
}
