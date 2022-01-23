package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {
    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> rsl = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            try {
                Document doc = Jsoup.connect(link + i).get();
                Elements row = doc.select(".postslisttopic");
                for (Element td : row) {
                    Element href = td.child(0);
                    String url = href.attr("href");
                    if (href.text().contains(" Java ")) {
                        rsl.add(detail(url));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rsl;
    }

    @Override
    public Post detail(String link) {
        try {
            Document doc = Jsoup.connect(link).get();
            String title = doc.select(".messageHeader").get(0).ownText();
            String description = doc.select(".msgBody").get(1).text();
            String time = doc.selectFirst(".msgFooter").text();
            String timeClean = time.substring(0, time.indexOf("[")).trim();
            LocalDateTime created = dateTimeParser.parse(timeClean);
            return new Post(title, link, description, created);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "https://www.sql.ru/forum/job-offers/";
        SqlRuParse sqlRuParse = new SqlRuParse(new SqlRuDateTimeParser());
        List<Post> listPosts = sqlRuParse.list(url);
        System.out.println(listPosts.get(1));
        System.out.println(listPosts.size());
    }
}
