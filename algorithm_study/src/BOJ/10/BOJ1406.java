import java.io.*;
import java.util.*;

public class BOJ1406 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String[] temp = br.readLine().split("");
        ArrayDeque<String> left = new ArrayDeque<>(Arrays.asList(temp));
        ArrayDeque<String> right = new ArrayDeque<>();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("P")) {
                String input = st.nextToken();
                left.addLast(input);
            } else if (command.equals("L")) {
                if (!left.isEmpty()) {
                    right.addFirst(left.pollLast());
                }
            } else if (command.equals("D")) {
                if (!right.isEmpty()) {
                    left.addLast(right.pollFirst());
                }
            } else if (command.equals("B")) {
                if (!left.isEmpty()) {
                    left.pollLast();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String elem : left) {
            sb.append(elem);
        }

        for (String elem : right) {
            sb.append(elem);
        }
        System.out.println(sb);
    }

    // q를 하나만 사용해서 반복문 이용 -> TO => q 2개 이용
    // TO
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        String[] temp = br.readLine().split("");
//        LinkedList<String> list = new LinkedList<>(Arrays.asList(temp));
//
//        int M = Integer.parseInt(br.readLine());
//        int pos = temp.length;
//        int size = pos;
//        for (int i = 0; i < M; i++) {
//            st = new StringTokenizer(br.readLine());
//            String command = st.nextToken();
//            if (command.equals("P")) {
//                String input = st.nextToken();
//                list.add(pos++, input);
//                size++;
//            } else if (command.equals("L")) {
//                if (pos != 0) {
//                    pos--;
//                }
//            } else if (command.equals("D")) {
//                if (pos != size) {
//                    pos++;
//                }
//            } else if (command.equals("B")) {
//                if (pos != 0) {
//                    list.remove(pos - 1);
//                    size--;
//                    pos--;
//                }
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (String elem : list) {
//            sb.append(elem);
//        }
//        System.out.println(sb);
//    }
}
