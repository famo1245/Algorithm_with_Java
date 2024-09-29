import java.util.*;
import java.io.*;

public class BOJ14438 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] valueTable = new int[N + 1];
        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int startIndex = (int) Math.pow(2, height);
        int size = startIndex * 2;
        int[] tree = new int[size];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            valueTable[i + 1] = value;
            tree[i + startIndex] = i + 1;
        }

        int parent = startIndex - 1;
        while (parent > 0) {
            int leftIndex = tree[parent * 2];
            int rightIndex = tree[parent * 2 + 1];

            if (valueTable[leftIndex] < valueTable[rightIndex]) {

            }
        }
    }
}
