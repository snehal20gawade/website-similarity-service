package com.cisco.webiste.similarity.service;

import java.io.IOException;

public interface TextExtractService {

    String[] extractWordsFromWebsite(String url) throws IOException;
}
