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

        // 초기화
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int childCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < childCount; j++) {
                int child = Integer.parseInt(st.nextToken());
                sys[i].add(new Directory(child, i));
            }
        }

        int currentNode = 1;
        int currentIndex = 0;

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("toggle")) {
                sys[currentNode].get(currentIndex).toggle();
            } else if (command.equals("move")) {
                int count = Integer.parseInt(st.nextToken());
                if (count > 0) {
                    for (int j = 0; j < count; j++) {
                        Directory current = sys[currentNode].get(currentIndex);
                        if (current.open) {
                            currentNode = current.directoryNum;
                            currentIndex = 0;
                        } else {
                            if (currentIndex < sys[currentNode].size() - 1) {
                                currentIndex++;
                            } else {
                                break;
                            }
                        }
                    }
                } else {
                    for (int j = 0; j > count; j--) {
                        if (currentNode == 1) {
                            break;  // 루트에서는 더 이상 상위로 이동 불가
                        }
                        int parentNode = sys[currentNode].get(0).parent;
                        currentIndex = getIndexInParent(sys[parentNode], currentNode);
                        currentNode = parentNode;
                    }
                }
                sb.append(sys[currentNode].get(currentIndex).directoryNum).append('\n');
            }
        }

        System.out.print(sb);
    }

    static int getIndexInParent(List<Directory> parentDir, int childNum) {
        for (int i = 0; i < parentDir.size(); i++) {
            if (parentDir.get(i).directoryNum == childNum) {
                return i;
            }
        }
        return -1;
    }

    static class Directory {
        int directoryNum;
        int parent;
        boolean open;

        public Directory(int directoryNum, int parent) {
            this.directoryNum = directoryNum;
            this.parent = parent;
            this.open = false;
        }

        public void toggle() {
            this.open = !this.open;
        }
    }
}