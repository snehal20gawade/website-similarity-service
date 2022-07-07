package com.cisco.webiste.similarity.controller;

import com.cisco.webiste.similarity.dto.WebsiteSimilarityCheckDto;
import com.cisco.webiste.similarity.service.HTMLTextExtractService;
import com.cisco.webiste.similarity.service.JaccardIndexService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;


@RestController
@RequestMapping(path = "/api")
public class WebsiteSimilarityController {

    private final HTMLTextExtractService htmlTextExtractService;
    private final JaccardIndexService jaccardIndexService;

    public WebsiteSimilarityController(HTMLTextExtractService htmlTextExtractService,
                                       JaccardIndexService jaccardIndexService) {
        this.htmlTextExtractService = htmlTextExtractService;
        this.jaccardIndexService = jaccardIndexService;
    }

    @PostMapping(path = "/jaccord-index", produces="application/json")
    public ResponseEntity<String> getJaccordIndex(@Valid @RequestBody WebsiteSimilarityCheckDto websiteSimilarityCheckDto) {
        try {
            String[] words = htmlTextExtractService.extractWordsFromWebsite(websiteSimilarityCheckDto.getWebSiteURL1());
            String[] words1 = htmlTextExtractService.extractWordsFromWebsite(websiteSimilarityCheckDto.getWebSiteURL2());

            BigDecimal jaccordIndex = jaccardIndexService.calculateIndex(words, words1);
            return ResponseEntity.status(HttpStatus.OK).body(jaccordIndex.toString());

        } catch (IOException ioException){
            return ResponseEntity.badRequest()
                    .body(ioException.getMessage());
        }

    }

}
