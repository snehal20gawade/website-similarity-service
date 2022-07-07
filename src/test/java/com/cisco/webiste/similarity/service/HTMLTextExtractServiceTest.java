package com.cisco.webiste.similarity.service;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HTMLTextExtractServiceTest {

    public static final String AN_EXAMPLE_LINK = "An example link";
    public static final String AN_EXAMPLE_LINK_WITH_COMMA = "An,example,link";
    public static final String AN_EXAMPLE_LINK_WITH_DOT = "An.example.link";
    public static final String WEBSITE_URL = "https://example.com";
    @Mock
    private ConnectionFactory connectionFactory;

    @Mock
    private Connection connection;

    @Mock
    private Document document;
    @Mock
    private Element element;

    private TextExtractService textExtractService;

    @BeforeEach
    public void setUp(){
        textExtractService = new HTMLTextExtractService(connectionFactory);
    }

    @Test
    public void givenHtmlTextWithSpaceShouldExtractWords() throws IOException {
        givenConnectionDetails();
        when(element.text()).thenReturn(AN_EXAMPLE_LINK);
        String[] words = textExtractService.extractWordsFromWebsite(WEBSITE_URL);
        String[] expected = new String[] {"An" , "example", "link"};
        assertArrayEquals(expected, words);
    }

    @Test
    public void givenHtmlTextWithCommaShouldExtractWords() throws IOException {
        givenConnectionDetails();
        when(element.text()).thenReturn(AN_EXAMPLE_LINK_WITH_COMMA);
        String[] words = textExtractService.extractWordsFromWebsite(WEBSITE_URL);
        String[] expected = new String[] {"An", "example", "link"};
        assertArrayEquals(expected, words);
    }

    @Test
    public void givenHtmlTextWithDotShouldExtractWords() throws IOException {
        givenConnectionDetails();
        when(element.text()).thenReturn(AN_EXAMPLE_LINK_WITH_DOT);
        String[] words = textExtractService.extractWordsFromWebsite(WEBSITE_URL);
        String[] expected = new String[] {"An", "example", "link"};
        assertArrayEquals(expected, words);
    }

    @Test
    void givenInvalidWebsiteURL() throws IOException {
        when(connectionFactory.getConnection(WEBSITE_URL)).thenReturn(connection);
        doThrow(IOException.class).when(connection).get();
        assertThrows(IOException.class, () -> textExtractService.extractWordsFromWebsite(WEBSITE_URL));
    }

    private void givenConnectionDetails() throws IOException {
        when(connectionFactory.getConnection(WEBSITE_URL)).thenReturn(connection);
        when(connection.get()).thenReturn(document);
        when(document.body()).thenReturn(element);
    }

}