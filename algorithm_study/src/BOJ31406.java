import java.io.*;
import java.util.*;

public class BOJ31406 {
    static Map<Integer, List<Integer>> sys = new HashMap<>();
    static List<Integer> visibleFolders = new ArrayList<>();
    static Set<Integer> openedFolders = new HashSet<>();
    static int cursor = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int childCount = Integer.parseInt(st.nextToken());
            List<Integer> children = new ArrayList<>();

            for (int j = 0; j < childCount; j++) {
                children.add(Integer.parseInt(st.nextToken()));
            }
            sys.put(i, children);
        }

        // 1은 펼쳐져야함
        visibleFolders.addAll(sys.get(1));

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("move")) {
                int k = Integer.parseInt(st.nextToken());
                moveCursor(k);
                sb.append(visibleFolders.get(cursor)).append('\n');
            } else if (command.equals("toggle")) {
                toggle(visibleFolders.get(cursor));
            }
        }

        System.out.print(sb);
    }

    static void moveCursor(int k) {
        cursor = Math.max(0, Math.min(cursor + k, visibleFolders.size() - 1));
    }

    static void toggle(int folder) {
        if (openedFolders.contains(folder)) {
            close(folder);
            openedFolders.remove(folder);
        } else {
            open(folder);
            openedFolders.add(folder);
        }
    }

    static void close(int folder) {
        List<Integer> subFolders = getAllSubFolders(folder);
        visibleFolders.removeAll(subFolders);
        cursor = Math.min(cursor, visibleFolders.size() - 1);
    }

    static void open(int folder) {
        int idx = visibleFolders.indexOf(folder) + 1;
        List<Integer> subFolders = getNowSubFolders(folder);
        visibleFolders.addAll(idx, subFolders);
    }

    static List<Integer> getNowSubFolders(int folder) {
        List<Integer> result = new ArrayList<>();
        List<Integer> children = sys.get(folder);

        if (children != null) {
            for (int child : children) {
                result.add(child);
                if (openedFolders.contains(child)) {
                    result.addAll(getNowSubFolders(child));
                }
            }
        }
        return result;
    }

    static List<Integer> getAllSubFolders(int folder) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(folder);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            List<Integer> children = sys.get(current);
            if (children != null) {
                for (int child : children) {
                    result.add(child);
                    queue.add(child);
                }
            }
        }

        return result;
    }
}
