package com.cisco.webiste.similarity.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

@Service
public class ConnectionFactory {

    public Connection getConnection(String url){
        return Jsoup.connect(url);
    }
}
