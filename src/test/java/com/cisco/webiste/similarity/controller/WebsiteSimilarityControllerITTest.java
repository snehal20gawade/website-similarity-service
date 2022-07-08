package com.cisco.webiste.similarity.controller;

import com.cisco.webiste.similarity.dto.WebsiteSimilarityCheckDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebsiteSimilarityControllerITTest {

    private static final String URL = "https://www.cisco.com/c/en_uk/about/contact-cisco.html/software-licensing-support";
    private static final String API_JACCORD_INDEX = "/api/jaccord-index";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenBothSameURL() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(API_JACCORD_INDEX)
                .content(asJsonString(new WebsiteSimilarityCheckDto(URL, URL)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1.000"));
    }

    @Test
    public void givenDifferentURL() throws Exception {
        String URL1 = "https://policies.google.com/technologies/voice?hl=en-GB&fg=1";
        String URL2 = "https://policies.google.com/technologies/wallet?hl=en-GB&fg=1";
        mockMvc.perform(MockMvcRequestBuilders
                .post(API_JACCORD_INDEX)
                .content(asJsonString(new WebsiteSimilarityCheckDto(URL1, URL2)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("0.389"));
    }

    @Test
    public void givenInvalidWebsiteUrlURLShouldReturnBadRequestStatus() throws Exception {
        String URL1 = "xyz.com";
        String URL2 = "abcd";
        mockMvc.perform(MockMvcRequestBuilders
                .post(API_JACCORD_INDEX)
                .content(asJsonString(new WebsiteSimilarityCheckDto(URL1, URL2)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}