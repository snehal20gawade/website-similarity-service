# Website Similarity REST API
--------
This project is developed in <br> 
* java version 1.8
* Spring Boot
* jsoup: Java HTML Parser
* Junit 5 and Mockito.

Its simple REST API service developed with spring rest controller to compute the similarity of the contents of two URLs.

When the REST API is called, it will fetch the content body of the two URLs (i.e.,
actual contents of webpage), and compute the similarity of their contents based
on the similarity measure called the Jaccard Index.


# Rest API URL
-----
To compare two ULR use below POST api with request body
   https://localhost:8080/api/jaccord-index/ 

eg. request body: 

    {
       "webSiteURL1": "https://policies.google.com/technologies/voice?hl=en-GB&fg=1"
       "webSiteURL2":  "https://policies.google.com/technologies/wallet?hl=en-GB&fg=1"
    }
  


## Program execution
### Sring Boot Application
  1. Start spring boot application : 
  * Do maven clean on project root directory and then excute WebsiteSimilarityServiceApplication.java

