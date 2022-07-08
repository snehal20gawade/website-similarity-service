package com.cisco.webiste.similarity.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class WebsiteSimilarityCheckDto {

    private final String REGEX_TO_CHECK_VALID_URL = "((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._\\+~#?&//=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._\\+~#?&//=]*)";

    @Pattern(regexp = REGEX_TO_CHECK_VALID_URL, message = "Invalid URL WebsiteURL1")
    private String webSiteURL1;

    @Pattern(regexp = REGEX_TO_CHECK_VALID_URL, message = "Invalid URL WebsiteURL2")
    private String webSiteURL2;

    public WebsiteSimilarityCheckDto(){
        super();
    }

    public WebsiteSimilarityCheckDto(String webSiteURL1, String webSiteURL2) {
        this.webSiteURL1 = webSiteURL1;
        this.webSiteURL2 = webSiteURL2;
    }

    public String getWebSiteURL1() {
        return webSiteURL1;
    }

    public String getWebSiteURL2() {
        return webSiteURL2;
    }

}
