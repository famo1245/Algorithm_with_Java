public class SWEA4796 {

    static int N, answer;
    static int[] H;

    public static void main(String[] args) throws Exception {
        Reader reader = new Reader();
        StringBuilder sb = new StringBuilder();

        int T = reader.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            N = reader.nextInt();
            H = new int[N];

            for (int i = 0; i < N; i++) {
                H[i] = reader.nextInt();
            }

            answer = 0;
            for (int i = 0; i < N - 2; i++) {
                int idx = i;
                int left = 0;

                // 높은산 찾기
                while (H[idx] < H[idx + 1]) {
                    left++;
                    if (idx + 2 >= N) {
                        idx = i;
                        break;
                    }
                    idx++;
                }

                // 해당 위치보다 높은 산이 X
                if (i == idx)
                    continue;

                i = idx - 1;
                // 다음 높은산까지 개수
                int right = 0;
                while (H[idx] > H[++idx]) {
                    right++;
                    if (idx + 1 >= N) {
                        break;
                    }
                }
                answer += left * right;
            }

            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static class Reader {
        final int SIZE = 1 << 10;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32)
                ;
            boolean neg = c == '-' ? true : false;
            if (neg)
                c = read();
            do
                n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return neg ? -n : n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0)
                    buffer[0] = -1;
            }
            return buffer[index++];
        }
    }
}
