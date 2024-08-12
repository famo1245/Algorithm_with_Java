import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ3151 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        int[] students = new int[N];
        Map<Integer, Integer> counts = new HashMap<>();

        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(temp[i]);
            int count = counts.getOrDefault(students[i], 0) + 1;
            counts.put(students[i], count);
        }

        Arrays.sort(students);
//        int answer = 0;
        long answer = 0;
        for (int i = 0; i < N - 1; i++) {
            int student = students[i];
            int start = i + 1;
            int end = N - 1;

            while (start < end) {
                int sum = students[start] + students[end] + student;
                if (sum == 0) {
                    if (students[start] == students[end]) {
                        answer += end - start;
                    } else {
                        answer += counts.get(students[end]);
                    }
                    start++;
                } else if (sum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        System.out.println(answer);
    }
}

/*
 * 1. 중복에 대한 고려 x => 합이 0이 되는 경우의 수를 한 번만 확인하고 넘어감
 * 2. 더하는 값이 똑같은 경우 고려 x => 무작정 곱하기만 함
 * 3. 중복 값이 있는 경우 => map을 이용하여 count
 * 4. 알고보니 경우의 수가 int를 벗어남.. => long type으로 처리
 * */