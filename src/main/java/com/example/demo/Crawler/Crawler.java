package com.example.demo.Crawler;

import com.example.demo.Model.Domain.Base;
import com.example.demo.Model.Domain.ScheduledBase;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class Crawler {

    @Autowired
    Crawler crawler;

    public void findLinks(String url, HashSet<String> links) {
        try {
            Document document = Jsoup.connect(url).get();
            if (!links.contains(url)){
                links.add(url);
            }
            Elements linkOnPage = document.select("a[href]");
            for (Element page : linkOnPage){
                String newUrl = page.attr("abs:href");
                if (!links.contains(newUrl) && newUrl.toLowerCase().contains(url.toLowerCase())){
                    links.add(newUrl);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList findArticles(HashSet<String> links) {

        ArrayList<Base> collection= new ArrayList<>();

        links.forEach(url -> {
            Document document;
            Base base = new Base();
            try {
                document = Jsoup.connect(url).get();
                String text = document.body().text();
                base.setText(text);
                base.setUrl(url);
                if (!collection.contains(base)){
                    collection.add(base);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
        return collection;
    }

    public void testUrl(String url) {
        /*StringBuilder sb = new StringBuilder();
        String textOnly = Jsoup.parse(sb.toString()).text();*/
        try {
            Document document = Jsoup.connect(url).get();
            String text = document.body().text();
            text = "123";
        } catch (IOException e) {
            e.printStackTrace();
        }

            /*
            String text;

            Document document = Jsoup.connect(url).get();
            Elements divs = document.select("div");
            for (Element element : divs){
                text = element.html();
            }*/
    }
}
