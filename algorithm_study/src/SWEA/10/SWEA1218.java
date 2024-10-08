import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1218 {
        public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int testCase = 1; testCase <= 10; testCase++) {
            sb.append("#").append(testCase).append(" ");
            int len = Integer.parseInt(br.readLine());
            int[] counts = new int[4];
            String[] temp = br.readLine().split("");
            boolean isPossible = true;

            if (len % 2 == 0) {
                for (String e : temp) {
                    switch (e) {
                        case "(":
                            counts[0]++;
                            break;
                        case ")":
                            counts[0]--;
                            break;
                        case "[":
                            counts[1]++;
                            break;
                        case "]":
                            counts[1]--;
                            break;
                        case "{":
                            counts[2]++;
                            break;
                        case "}":
                            counts[2]--;
                            break;
                        case "<":
                            counts[3]++;
                            break;
                        case ">":
                            counts[3]--;
                            break;
                    }
                }

                for (int i = 0; i < 4; i++) {
                    if (counts[i] != 0) {
                        isPossible = false;
                        break;
                    }
                }

                if (isPossible) {
                    sb.append(1);
                } else {
                    sb.append(0);
                }
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
