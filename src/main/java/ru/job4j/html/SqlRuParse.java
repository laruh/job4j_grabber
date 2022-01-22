package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlRuParse {
    private final static String PATTERN = "(.*\\d{2}:\\d{2})";

    public static Post parsePost(Element href) throws IOException {
        String url = href.attr("href");
        Document doc = Jsoup.connect(url).get();
        String description = doc.select(".msgBody").get(1).text();
        SqlRuDateTimeParser parserTime = new SqlRuDateTimeParser();
        String time = doc.selectFirst(".msgFooter").text();
        Matcher matcher = Pattern.compile(PATTERN).matcher(time);
        LocalDateTime created = parserTime.parse(matcher.group());
        return new Post(href.text(), url, description, created);
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i <= 5; i++) {
            Document doc = Jsoup.connect(
                    "https://www.sql.ru/forum/job-offers/" + i).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                Element parent = td.parent();
                System.out.println(href.attr("href"));
                System.out.println(href.text());
                SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
                System.out.println(sqlRuDateTimeParser.parse(parent.child(5).text()));
                System.out.println(parsePost(href));
            }
        }
    }
}
