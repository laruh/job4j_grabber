package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class SqlRuParse implements Parse {
    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static Post parsePost(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String title = doc.select(".messageHeader").get(0).ownText();
        String description = doc.select(".msgBody").get(1).text();
        SqlRuDateTimeParser parserTime = new SqlRuDateTimeParser();
        String time = doc.selectFirst(".msgFooter").text();
        String timeClean = time.substring(0, time.indexOf("[")).trim();
        LocalDateTime created = parserTime.parse(timeClean);
        return new Post(title, url, description, created);
    }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 5; i++) {
            Document doc = Jsoup.connect(
                    "https://www.sql.ru/forum/job-offers/" + i).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                Element parent = td.parent();
                String url = href.attr("href");
                System.out.println(url);
                System.out.println(href.text());
                SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
                System.out.println(sqlRuDateTimeParser.parse(parent.child(5).text()));
                System.out.println(parsePost(url));
            }
        }
    }

    @Override
    public List<Post> list(String link) {
        return null;
    }

    @Override
    public Post detail(String link) {
        return null;
    }
}
