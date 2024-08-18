import java.io.*;
import java.util.*;

public class BOJ1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(temp[i]);
        }

        Arrays.sort(nums);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int num = nums[i];
            int start = 0;
            int end = N - 1;

            while (start < end) {
                int sum = nums[start] + nums[end];
                if (num == sum) {
                    if (start != i && end != i) {
                        answer++;
                        break;
                    } else if (start == i) {
                        start++;
                    } else {
                        end--;
                    }
                } else if (num > sum) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        System.out.println(answer);
    }
}