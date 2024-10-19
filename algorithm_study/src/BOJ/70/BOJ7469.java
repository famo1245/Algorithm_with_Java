import java.util.*;
import java.io.*;

public class BOJ7469 {

    static final int MIN = -1_000_000_001;
    static final int MAX = 1_000_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int height = 32 - Integer.numberOfLeadingZeros(n - 1);
        int startIndex = 1 << height;
        int size = 1 << (height + 1);
        int[][] tree = new int[size][0];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tree[i + startIndex] = new int[]{Integer.parseInt(st.nextToken())};
        }

        int parent = startIndex - 1;
        while (parent > 0) {
            tree[parent] = merge(tree[parent * 2], tree[parent * 2 + 1]);
            parent--;
        }

        for (int q = 0; q < m; q++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1 + startIndex;
            int j = Integer.parseInt(st.nextToken()) - 1 + startIndex;
            int k = Integer.parseInt(st.nextToken());

            int low = MIN;
            int high = MAX;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (query(i, j, mid, tree) < k) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            sb.append(high).append('\n');
        }

        System.out.print(sb);
    }

    private static int query(int start, int end, int key, int[][] tree) {
        int result = 0;

        while (start <= end) {
            if (start % 2 == 1) {
                result += lowerBound(tree[start], key);
                start++;
            }

            if (end % 2 == 0) {
                result += lowerBound(tree[end], key);
                end--;
            }

            start /= 2;
            end /= 2;
        }

        return result;
    }

    private static int lowerBound(int[] arr, int key) {
        int start = 0;
        int end = arr.length;

        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= key) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }

    private static int[] merge(int[] left, int[] right) {
        int leftSize = left.length;
        int rightSize = right.length;
        int[] result = new int[leftSize + rightSize];
        int leftIdx = 0;
        int rightIdx = 0;
        int index = 0;

        while (leftIdx < leftSize && rightIdx < rightSize) {
            if (left[leftIdx] < right[rightIdx]) {
                result[index++] = left[leftIdx++];
            } else {
                result[index++] = right[rightIdx++];
            }
        }

        if (leftIdx != leftSize) {
            while (leftIdx < leftSize) {
                result[index++] = left[leftIdx++];
            }
        } else if (rightIdx != rightSize) {
            while (rightIdx < rightSize) {
                result[index++] = right[rightIdx++];
            }
        }

        return result;
    }
}
