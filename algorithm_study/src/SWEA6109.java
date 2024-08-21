import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA6109 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append("\n");
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            int[][] table = new int[N][N];
            int[][] answer = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] line;
            if (direction.equals("up")) {
                for (int col = 0; col < N; col++) {
                    int row = N - 1;
                    int index = 0;
                    line = new int[N];

                    while (row >= 0) {
                        if (table[row][col] != 0) {
                            line[index++] = table[row][col];
                        }
                        row--;
                    }

                    row = 0;
                    index--;
                    while (row < N) {
                        if (index >= 0) {
                            if (row != 0 && index < N - 1 && line[index] == table[row - 1][col]) {
                                table[--row][col] *= 2;
                            } else {
                                table[row][col] = line[index];
                            }
                            index--;
                        } else {
                            table[row][col] = 0;
                        }

                        row++;
                    }
                }
            } else if (direction.equals("down")) {
                for (int col = 0; col < N; col++) {
                    int row = 0;
                    int index = 0;
                    line = new int[N];

                    while (row < N) {
                        line[index++] = table[row++][col];
                    }

                    row = N - 1;
                    index--;
                    while (row >= 0) {
                        while (line[index] == 0) {
                            index--;
                        }
                        
                        if (index >= 0) {
                            if (row != N - 1 && index < N - 1 && line[index] == table[row + 1][col]) {
                                table[++row][col] *= 2;
                            } else {
                                table[row][col] = line[index];
                            }
                            index--;
                        } else {
                            table[row][col] = 0;
                        }

                        row--;
                    }
                }
            } else if (direction.equals("right")) {
                for (int col = 0; col < N; col++) {
                    int row = N - 1;
                    int index = 0;
                    line = new int[N];

                    while (row >= 0) {
                        if (table[row][col] != 0) {
                            line[index++] = table[row][col];
                        }
                        row--;
                    }

                    row = 0;
                    index--;
                    while (row < N) {
                        if (index >= 0) {
                            if (row != 0 && index < N - 1 && line[index] == table[row - 1][col]) {
                                table[--row][col] *= 2;
                            } else {
                                table[row][col] = line[index];
                            }
                            index--;
                        } else {
                            table[row][col] = 0;
                        }

                        row++;
                    }
                }
            } else {
                for (int col = 0; col < N; col++) {
                    int row = N - 1;
                    int index = 0;
                    line = new int[N];

                    while (row >= 0) {
                        if (table[row][col] != 0) {
                            line[index++] = table[row][col];
                        }
                        row--;
                    }

                    row = 0;
                    index--;
                    while (row < N) {
                        if (index >= 0) {
                            if (row != 0 && index < N - 1 && line[index] == table[row - 1][col]) {
                                table[--row][col] *= 2;
                            } else {
                                table[row][col] = line[index];
                            }
                            index--;
                        } else {
                            table[row][col] = 0;
                        }

                        row++;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(table[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
