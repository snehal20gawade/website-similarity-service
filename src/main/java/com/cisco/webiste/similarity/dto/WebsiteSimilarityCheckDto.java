package com.cisco.webiste.similarity.dto;


import javax.validation.constraints.NotBlank;

public class WebsiteSimilarityCheckDto {

    //TODO add regex to check valid URL
    @NotBlank(message = "WebsiteURL1 can not be blank")
    private String webSiteURL1;

    @NotBlank(message = "WebsiteURL2 can not be blank")
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
