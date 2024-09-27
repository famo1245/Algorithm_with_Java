import java.util.*;
import java.io.*;

public class BOJ14427 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int size = startIndex * 2;
        int[] tree = new int[size];
        int[] indexTable = new int[N + 1];
        indexTable[0] = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            tree[startIndex + i] = i + 1;
            indexTable[i + 1] = value;
        }

        int i = startIndex - 1;
        while (i > 0) {
            int indexLeft = tree[i * 2];
            int indexRight = tree[i * 2 + 1];
            if (indexTable[indexLeft] < indexTable[indexRight]) {
                tree[i] = indexLeft;
            } else if (indexTable[indexLeft] > indexTable[indexRight]) {
                tree[i] = indexRight;
            } else {    // 값이 같은 경우
                tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
            }
            i--;
        }

        int M = Integer.parseInt(br.readLine());
        for (i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int originIndex = Integer.parseInt(st.nextToken());
                int newValue = Integer.parseInt(st.nextToken());
                indexTable[originIndex] = newValue;

                int index = startIndex + originIndex - 1;
                update(tree, indexTable, index / 2);
            } else {
                sb.append(tree[1]).append('\n');
            }
        }

        System.out.print(sb);
    }

    static void update(int[] tree, int[] indexTable, int index) {
        if (index == 0) {
            return;
        }

        int indexLeft = tree[index * 2];
        int indexRight = tree[index * 2 + 1];
        if (indexTable[indexLeft] < indexTable[indexRight]) {
            tree[index] = indexLeft;
        } else if (indexTable[indexLeft] > indexTable[indexRight]) {
            tree[index] = indexRight;
        } else {    // 값이 같은 경우
            tree[index] = Math.min(tree[index * 2], tree[index * 2 + 1]);
        }

        update(tree, indexTable, index / 2);
    }
}