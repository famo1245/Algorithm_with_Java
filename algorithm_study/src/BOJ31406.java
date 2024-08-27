import java.io.*;
import java.util.*;

public class BOJ31406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        List<Directory>[] sys = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            sys[i] = new ArrayList<>();
        }

        // init
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int childCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < childCount; j++) {
                int child = Integer.parseInt(st.nextToken());
                sys[i].add(new Directory(child));
            }
        }

        int index = 0;
        int nodeIndex = 1;
        int parentNode = 1;
        Directory current = sys[nodeIndex].get(index);
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("toggle")) {
                current.open = !current.open;
            } else {
                int count = Integer.parseInt(st.nextToken());
                while (count > 0) {
                    if (current.open) {
                        nodeIndex = current.directoryNum;
                        index = 0;
                    } else {
                        if (index < sys[nodeIndex].size() - 2) {
                            current = sys[nodeIndex].get(++index);
                        } else {
                            count = 0;
                        }
                    }
                    count--;
                }

                sb.append(current.directoryNum).append('\n');
            }
        }

        System.out.print(sb);
    }

    static class Directory {
        int directoryNum;
        boolean open;

        public Directory(int directoryNum) {
            this.directoryNum = directoryNum;
        }

        @Override
        public String toString() {
            return "Directory{" +
                    "directoryNum=" + directoryNum +
                    '}';
        }
    }
}
