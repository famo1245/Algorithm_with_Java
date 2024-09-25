import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ22859 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();

        Pattern mainPattern = Pattern.compile("<main>(.*?)</main>");
        Matcher mainMatcher = mainPattern.matcher(input);
        if (mainMatcher.find()) {
            String main = mainMatcher.group(1);

            Pattern divPattern = Pattern.compile("<div title=\"(.*?)\">(.*?)</div>");
            Matcher divMatcher = divPattern.matcher(main);

            while (divMatcher.find()) {
                String titleName = divMatcher.group(1);
                String div = divMatcher.group(2);

                sb.append("title : ").append(titleName).append('\n');

                Pattern pPattern = Pattern.compile("<p>(.*?)</p>");
                Matcher pMatcher = pPattern.matcher(div);

                while (pMatcher.find()) {
                    String paragraph = pMatcher.group(1);
                    paragraph = paragraph.replaceAll("<.*?>", "");
                    paragraph = paragraph.replaceAll("\\s+", " ").trim();
                    sb.append(paragraph).append('\n');
                }
            }
        }

        System.out.print(sb);
    }
}