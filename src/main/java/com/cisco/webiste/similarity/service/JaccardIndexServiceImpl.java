package com.cisco.webiste.similarity.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JaccardIndexServiceImpl implements JaccardIndexService {

    @Override
    public BigDecimal calculateIndex(String[] words, String[] words1) {
        int numberOfWordsInBothSet = numberOfWordsInBothSet(words, words1);
        int numberOfWordsInEitherSet = numberOfWordsInEitherSet(words, words1);
        return BigDecimal.valueOf(numberOfWordsInBothSet)
                .divide(BigDecimal.valueOf(numberOfWordsInEitherSet), 3, RoundingMode.FLOOR);
    }

    protected int numberOfWordsInEitherSet(String[] words, String[] words1){
        return Math.toIntExact(Stream.of(words, words1).flatMap(Stream::of)
                .distinct().count());
    }

    protected int numberOfWordsInBothSet(String[] words, String[] words1){
        return Arrays.stream(words)
                .distinct()
                .filter(Arrays.asList(words1)::contains)
                .collect(Collectors.toSet())
                .size();
    }
}
