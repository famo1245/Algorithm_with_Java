import java.util.*;
import java.io.*;

public class BOJ14428 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] indexTable = new int[N + 1];
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int firstIndex = (int) Math.pow(2, height);
        int size = firstIndex * 2;
        int[] tree = new int[size];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            indexTable[i + 1] = value;
            tree[i + firstIndex] = i + 1;
        }

        int parent = firstIndex - 1;
        while (parent > 0) {
            int leftIndex = tree[parent * 2];
            int rightIndex = tree[parent * 2 + 1];
            if (indexTable[leftIndex] < indexTable[rightIndex]) {
                tree[parent] = leftIndex;
            } else if (indexTable[leftIndex] > indexTable[rightIndex]) {
                tree[parent] = rightIndex;
            } else {
                tree[parent] = Math.min(indexTable[leftIndex], indexTable[rightIndex]);
            }

            parent--;
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int index = Integer.parseInt(st.nextToken());
                int newValue = Integer.parseInt(st.nextToken());
                indexTable[index] = newValue;
                int treeIndex = index + firstIndex - 1;
                update(tree, indexTable, treeIndex / 2);
            } else {
                int start = Integer.parseInt(st.nextToken()) + firstIndex - 1;
                int end = Integer.parseInt(st.nextToken()) + firstIndex - 1;
                int result = Integer.MAX_VALUE;

                while (start <= end) {
                    if (start % 2 == 1) {
                        result = Math.min(result, tree[start]);
                        start++;
                    }

                    if (end % 2 == 0) {
                        result = Math.min(result, tree[end]);
                        end--;
                    }

                    start /= 2;
                    end /= 2;
                }

                sb.append(result).append('\n');
            }
        }

        System.out.print(sb);
    }

    static void update(int[] tree, int[] indexTable, int index) {
        if (index == 0) {
            return;
        }

        int leftIndex = index * 2;
        int rightIndex = index * 2 + 1;
        if (indexTable[tree[leftIndex]] < indexTable[tree[rightIndex]]) {
            tree[index] = leftIndex;
        } else if (indexTable[tree[leftIndex]] > indexTable[tree[rightIndex]]) {
            tree[index] = rightIndex;
        } else {
            tree[index] = Math.min(indexTable[leftIndex], indexTable[rightIndex]);
        }

        update(tree, indexTable, index / 2);
    }
}
