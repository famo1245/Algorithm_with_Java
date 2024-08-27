import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215 {

    static int N, L;
    static Ingredient[] ingredients;
    static StringBuilder sb;
    static int maxTaste;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            sb.append("#").append(testCase).append(" ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            ingredients = new Ingredient[N];


            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int taste = Integer.parseInt(st.nextToken());
                int calorie = Integer.parseInt(st.nextToken());
                ingredients[i] = new Ingredient(taste, calorie);
            }

            getMaxTaste(0, 0, 0);

            sb.append(maxTaste).append("\n");
            maxTaste = 0;
        }

        System.out.println(sb);
    }

    static void getMaxTaste(int index, int sumCalories, int sumTaste) {
        if (sumCalories <= L) {
            if (index == N) {
                maxTaste = Math.max(maxTaste, sumTaste);
                return;
            }

            Ingredient ingredient = ingredients[index];
            getMaxTaste(index + 1, sumCalories + ingredient.calorie, sumTaste + ingredient.taste);
            getMaxTaste(index + 1, sumCalories, sumTaste);
        }

    }

    static class Ingredient {
        int taste;
        int calorie;

        public Ingredient(int taste, int calorie) {
            this.taste = taste;
            this.calorie = calorie;
        }
    }
}
