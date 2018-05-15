package com.example.demo.Service;

import com.example.demo.Crawler.Crawler;
import com.example.demo.Model.Domain.Base;
import com.example.demo.Model.Domain.ScheduledBase;
import com.example.demo.Model.Exception.InvalidUrlException;
import com.example.demo.Repository.BaseRepository;
import com.example.demo.Repository.ScheduledBaseRepository;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class BaseService {
    @Autowired
    BaseRepository baseRepository;

    @Autowired
    Crawler crawler;

    @Autowired
    ScheduledBaseRepository scheduledBaseRepository;

    public String updateData() throws InvalidUrlException {

        HashSet<String> links = new HashSet<String>();

        String url = "http://www.tcmb.gov.tr/";
        crawler.findLinks(url, links);

        url = "http://www.tuik.gov.tr/";
        crawler.findLinks(url, links);

        ArrayList<Base> collection = new ArrayList<>();

        collection = crawler.findArticles(links);
        for (Base base : collection) {
            //baseRepository.save(base);
        }
        //baseRepository.save(collection);
        return url;
    }

    public int findDocuments() {
        String blogUrl = "https://spring.io/blog";
        org.jsoup.nodes.Document doc = null;
        try {
            doc = Jsoup.connect(blogUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = doc.title();

        Base base = new Base();
        base.setText("Deneme 1 2");
        baseRepository.save(base);


        return 1;


    }


    public List<Base> findResults(String q) {
        int index;
        int firstIndex;
        int lastIndex;

        List<Base> searchResults = baseRepository.findByTextLike(q);
        List<Base> searchResults2 = new ArrayList<>();

        for (Base base : searchResults) {
            Base base2 = new Base();
            String newText;
            index = 0;
            firstIndex = 0;
            lastIndex = 0;
            index = base.getText().indexOf(q);
            if (index != 0) {
                if (index > 30) {
                    firstIndex = index - 30;
                }
                lastIndex = index + 60;
                newText   = "..." + base.getText().substring(firstIndex, lastIndex) + "...";
                base2.setText(newText);
                base2.setUrl(base.getUrl());
                searchResults2.add(base2);
            }

        }

        return searchResults2;
    }

    public int Test() {

        HashSet<String> links = new HashSet<String>();

        String url = "http://www.tcmb.gov.tr/";
        crawler.findLinks(url, links);

        url = "http://www.tuik.gov.tr/";
        crawler.findLinks(url, links);

        ArrayList<ScheduledBase> collection = new ArrayList<>();

        collection = crawler.findArticles(links);
        if (collection != null){
            scheduledBaseRepository.deleteAll();
            for (ScheduledBase scheduledBase : collection) {
                scheduledBaseRepository.save(scheduledBase);
            }
            return 1;

        }
        else {
            return 0;
        }
    }
}
