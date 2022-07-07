# Talking-clock
This project is developed in java version 1.8, Spring Boot, Junit 5 and Mockito.

Its simple REST API service developed with spring rest controller to compute the similarity of the contents of two URLs.

When the REST API is called, it will fetch the content body of the two URLs (i.e.,
actual contents of webpage), and compute the similarity of their contents based
on the similarity measure called the Jaccard Index.



## Rest API URL's
To compare two ULR use below POST api with request body
  https://localhost:8080/api/jaccord-index
    {
        webSiteURL1: https://some.example.com
        webSiteURL2: https://some.another.example.com
    }
   

## Program execution
### Sring Boot Application
  1. Start spring boot application : WebsiteSimilarityServiceApplication.java and execute above API's

