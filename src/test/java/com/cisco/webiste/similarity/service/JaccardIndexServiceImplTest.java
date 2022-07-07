package com.cisco.webiste.similarity.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JaccardIndexServiceImplTest {

    private JaccardIndexServiceImpl jaccardIndexService;

    @BeforeEach
    public void setUp(){
        jaccardIndexService = new JaccardIndexServiceImpl();
    }


    @Test
    void givenSixDistinctWordShouldReturnDistinctWordCount() {
        String[] words = new String[] {"This" , "is", "an" ,"apple"};
        String[] words1 = new String[] {"This" , "is", "a" ,"book"};
        int count = jaccardIndexService.numberOfWordsInEitherSet(words, words1);
        assertThat(count).isEqualTo(6);
    }

    @Test
    void givenSevenDistinctWordShouldReturnDistinctWordCount() {
        String[] words = new String[] {"This" , "is", "an" ,"apple"};
        String[] words1 = new String[] {"I" , "am", "human"};
        int count = jaccardIndexService.numberOfWordsInEitherSet(words, words1);
        assertThat(count).isEqualTo(7);
    }

    @Test
    void givenTwoCommonWordShouldReturnCommonWordCount() {
        String[] words = new String[] {"This" , "is", "an" ,"apple"};
        String[] words1 = new String[] {"This" , "is", "a" ,"book"};
        int count = jaccardIndexService.numberOfWordsInBothSet(words, words1);
        assertThat(count).isEqualTo(2);
    }

    @Test
    void calculateJaccardIndex() {
        String[] words = new String[] {"This" , "is", "an" ,"apple"};
        String[] words1 = new String[] {"This" , "is", "a" ,"book"};
        BigDecimal index = jaccardIndexService.calculateIndex(words, words1);
        assertThat(index).isEqualTo(BigDecimal.valueOf(0.333));
    }

    @Test
    void givenNoSimilarWordsJaccardIndexShouldBeZero() {
        String[] words = new String[] {"This" , "is", "an" ,"apple"};
        String[] words1 = new String[] {"I" , "am", "human"};
        BigDecimal index = jaccardIndexService.calculateIndex(words, words1);
        assertThat(index).isEqualTo(new BigDecimal("0.000"));
    }

    @Test
    void givenAllSimilarWordsJaccardIndexShouldBeOne() {
        String[] words = new String[] {"This" , "is", "an" ,"apple"};
        String[] words1 = new String[] {"This" , "is", "an" ,"apple"};
        BigDecimal index = jaccardIndexService.calculateIndex(words, words1);
        assertThat(index).isEqualTo(new BigDecimal("1.000"));
    }
}