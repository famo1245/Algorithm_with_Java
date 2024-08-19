import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA6808V3 {

    final static ArrayList<Integer> cards = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18));
    static int[] possibleCards = new int[9];
    static int win = 0;
    static int lose = 0;
    static boolean[] visited = new boolean[9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        new ArrayList<>();
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int testCase = 1; testCase < T + 1; testCase++) {
            sb.append("#").append(testCase).append(" ");
            st = new StringTokenizer(br.readLine());
            Integer[] playerCard = new Integer[9];
            for (int i = 0; i < 9; i++) {
                playerCard[i] = Integer.parseInt(st.nextToken());
            }

            Set<Integer> opponentCard = new TreeSet<>(cards);
            Set<Integer> player = new TreeSet<>(Arrays.asList(playerCard));
            opponentCard.removeAll(player);

            Integer[] opponent = new Integer[opponentCard.size()];
            opponentCard.toArray(opponent);

            findCase(playerCard, opponent, 0);
            sb.append(win).append(" ");
            sb.append(lose).append("\n");

            win = 0;
            lose = 0;
        }

        System.out.println(sb);
    }

    private static void findCase(Integer[] player, Integer[] opponent, int idx) {
        if (idx == 9) {
            calcScore(player);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (visited[i]) {
                continue;
            }

            possibleCards[idx] = opponent[i];
            visited[i] = true;
            findCase(player, opponent, idx + 1);
            visited[i] = false;
        }
    }

    private static void calcScore(Integer[] player) {
        int myScore = 0;
        int opponentScore = 0;
        for (int i = 0; i < 9; i++) {
            int playerCard = player[i];
            int opponentCard = possibleCards[i];
            if (playerCard > opponentCard) {
                myScore += playerCard + opponentCard;
            } else {
                opponentScore += playerCard + opponentCard;
            }
        }

        if (myScore > opponentScore) {
            win++;
        } else if (myScore < opponentScore) {
            lose++;
        }
    }
}