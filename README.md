# Website Similarity REST API
--------
This project is developed in <br> 
* java version 1.8
* Spring Boot
* jsoup: Java HTML Parser
* Junit 5 and Mockito.

Its simple REST API service developed with spring rest controller to compute the similarity of the contents of two Websites.

When the REST API called, it will fetch the content body of the two URLs (i.e.,
actual contents of webpage), and compute the similarity of their contents based
on the similarity measure called the Jaccard Index.


# Rest API URL
-----
To compare two ULR use below POST api with request body mentioned as per below examples.
   https://localhost:8080/api/jaccord-index/ 

# Examples
1. Compared URL's with few similar words
* **Jaccord index 0.389**

```json

    {
       "webSiteURL1": "https://policies.google.com/technologies/voice?hl=en-GB&fg=1"
       "webSiteURL2":  "https://policies.google.com/technologies/wallet?hl=en-GB&fg=1"
    }

```
2.Compared URL's with almost similar content:
 * Jacaard index **0.98**

```json

    {
	  "webSiteURL1": "https://jobs.cisco.com/jobs/ProjectDetail/Software-Engineer-Internal-Applications-Intern-India-UHR/1379113?   	source=Cisco+Jobs+Career+Site&tags=CDC+Browse+all+jobs+careers",
	
	  "webSiteURL2":  "https://jobs.cisco.com/jobs/ProjectDetail/Software-Engineer-Internal-Applications-New-Grad-India-UHR/1379114?source=Cisco+Jobs+Career+Site&tags=CDC+Browse+all+jobs+careers"
   }
   
   ```

3. Compared same URL's would return **Jaccard Index 1**:

```json

    {
	  "webSiteURL1": "https://policies.google.com/technologies/voice?hl=en-GB&fg=1",
	
	  "webSiteURL2":  "https://policies.google.com/technologies/voice?hl=en-GB&fg=1"
}

```
## Program execution
### Sring Boot Application
Start spring boot application : <br>
1. Do maven clean (mvn clean) on project root directory <br>
2. Run WebsiteSimilarityServiceApplication.java
3. Execute above metioned REST api endpoint by using API platform like Postman or cURLS.

