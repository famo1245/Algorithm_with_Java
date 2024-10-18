import java.io.*;
import java.util.*;

public class BOJ13544 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int size = startIndex * 2;
        int[][] tree = new int[size][0];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i + startIndex] = new int[]{Integer.parseInt(st.nextToken())};
        }

        int parent = startIndex - 1;
        while (parent > 0) {
            tree[parent] = merge(tree[parent * 2], tree[parent * 2 + 1]);
            parent--;
        }

        int M = Integer.parseInt(br.readLine());
        int lastAns = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = (Integer.parseInt(st.nextToken()) ^ lastAns) - 1 + startIndex;
            int end = (Integer.parseInt(st.nextToken()) ^ lastAns) - 1 + startIndex;
            int key = Integer.parseInt(st.nextToken()) ^ lastAns;
            lastAns = query(start, end, key, tree);
            sb.append(lastAns).append('\n');
        }

        System.out.print(sb);
    }

    static int query(int start, int end, int key, int[][] tree) {
        int count = 0;
        while (start <= end) {
            if (start % 2 == 1) {
                int location = upperBound(tree[start], key);
                count += tree[start].length - location - 1;
                start++;
            }

            if (end % 2 == 0) {
                int location = upperBound(tree[end], key);
                count += tree[end].length - location - 1;
                end--;
            }

            start /= 2;
            end /= 2;
        }

        return count;
    }

    static int[] merge(int[] leftChild, int[] rightChild) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;
        int maxLeft = leftChild.length;
        int maxRight = rightChild.length;
        int[] result = new int[maxLeft + maxRight];

        while (leftIndex < maxLeft && rightIndex < maxRight) {
            if (leftChild[leftIndex] < rightChild[rightIndex]) {
                result[index++] = leftChild[leftIndex++];
            } else {
                result[index++] = rightChild[rightIndex++];
            }
        }

        if (leftIndex != maxLeft) {
            for (int i = leftIndex; i < maxLeft; i++) {
                result[index++] = leftChild[i];
            }
        } else if (rightIndex != maxRight) {
            for (int i = rightIndex; i < maxRight; i++) {
                result[index++] = rightChild[i];
            }
        }

        return result;
    }

    private static int upperBound(int[] arr, int value) {
        int max = arr.length;
        int min = 0;
        while (min < max) {
            int mid = (min + max) / 2;
            if (value < arr[mid]) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min - 1;
    }
}