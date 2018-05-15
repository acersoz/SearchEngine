package com.example.demo.Schedule;

import com.example.demo.Crawler.Crawler;
import com.example.demo.Model.Domain.Base;
import com.example.demo.Model.Domain.ScheduledBase;
import com.example.demo.Repository.BaseRepository;
import com.example.demo.Repository.ScheduledBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class CollectionJob {

    @Autowired
    Crawler crawler;

    @Autowired
    ScheduledBaseRepository scheduledBaseRepository;

    @Autowired
    BaseRepository baseRepository;

    @Scheduled(fixedRate = 120000)
    public void Scheduler(){

        HashSet<String> links = new HashSet<String>();

        String url = "http://www.tcmb.gov.tr/";
        crawler.findLinks(url, links);

        url = "http://www.tuik.gov.tr/";
        crawler.findLinks(url, links);

        url = "http://www.boun.edu.tr/";
        crawler.findLinks(url, links);

        ArrayList<Base> collection = new ArrayList<>();

        collection = crawler.findArticles(links);
        if (collection != null){
            baseRepository.deleteAll();
            for (Base base : collection) {
                baseRepository.save(base);
            }

        }
    }
}
