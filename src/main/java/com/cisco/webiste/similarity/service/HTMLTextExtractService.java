package com.cisco.webiste.similarity.service;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HTMLTextExtractService implements TextExtractService {

    public static final String SPECIAL_CHAR_REGEX = "[\\s@&.,?$+-]+";

    private final ConnectionFactory connectionFactory;

    public HTMLTextExtractService(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public String[] extractWordsFromWebsite(String url) throws IOException {
        Document doc = connectionFactory.getConnection(url).get();
        return doc.body().text().split(SPECIAL_CHAR_REGEX);
    }
}
