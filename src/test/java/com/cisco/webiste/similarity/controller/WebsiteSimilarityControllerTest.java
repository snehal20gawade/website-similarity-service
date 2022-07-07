package com.cisco.webiste.similarity.controller;

import com.cisco.webiste.similarity.dto.WebsiteSimilarityCheckDto;
import com.cisco.webiste.similarity.service.HTMLTextExtractService;
import com.cisco.webiste.similarity.service.JaccardIndexService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.math.BigDecimal;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WebsiteSimilarityControllerTest {

    private static final String URL_1 = "URL1";
    private static final String URL_2 = "URL2";
    private static final BigDecimal JACCARD_INDEX = BigDecimal.ONE;
    private static final String[] words = new String[] {"A", "B"};
    private static final String ERROR_CONNECTION_ISSUE = "some connection issue";

    @Mock
    private HTMLTextExtractService textExtractService;
    @Mock
    private JaccardIndexService indexService;
    @Mock
    private WebsiteSimilarityCheckDto websiteSimilarityCheckDto;

    private WebsiteSimilarityController controller;

    @BeforeEach
    void setUp() {
        controller = new WebsiteSimilarityController(textExtractService, indexService);
    }

    @Test
    void givenJaccardIndexCalculateSuccessfully() throws IOException {
        givenJaccardIndexCalculatedSuccessfully();
        ResponseEntity<String> response = controller.getJaccordIndex (websiteSimilarityCheckDto);
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat(JACCARD_INDEX).isEqualTo(response.getBody());
    }

    @Test
    void givenInvalidWebsiteURL() throws IOException {
        when(websiteSimilarityCheckDto.getWebSiteURL1()).thenReturn(URL_1);
        when(textExtractService.extractWordsFromWebsite(URL_1)).thenThrow(new IOException(ERROR_CONNECTION_ISSUE));
        ResponseEntity<String> response = controller.getJaccordIndex (websiteSimilarityCheckDto);
        assertThat(HttpStatus.BAD_REQUEST).isEqualTo(response.getStatusCode());
        assertThat(ERROR_CONNECTION_ISSUE).isEqualTo(response.getBody());
    }

    private void givenJaccardIndexCalculatedSuccessfully() throws IOException {
        when(websiteSimilarityCheckDto.getWebSiteURL1()).thenReturn(URL_1);
        when(websiteSimilarityCheckDto.getWebSiteURL2()).thenReturn(URL_2);
        when(textExtractService.extractWordsFromWebsite(URL_1)).thenReturn(words);
        when(textExtractService.extractWordsFromWebsite(URL_2)).thenReturn(words);
        when(indexService.calculateIndex(words, words)).thenReturn(JACCARD_INDEX);
    }

}