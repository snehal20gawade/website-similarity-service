package com.cisco.webiste.similarity.service;

import java.math.BigDecimal;

public interface JaccardIndexService {

    BigDecimal calculateIndex(String[] words, String[] words1);
}
