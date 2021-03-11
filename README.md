# java-sprong-boot-github-api
REST API with GitHub repository data from Url

In this repository created program return user repositories GitHub from Url as REST API. Created REST in controller have one parameter "sort" which specify sort ascending or descending by stars (like: "stars,asc" or "stars,desc").

Task content:

Create a simple REST service that returns details of all public repositories of a given user, the data should be sorted in ascending or descending order depending on the number of stars the repository has received from users. The sort parameter should be sent in the request.

Details should include:
- full name of the repository,
- description of the repository,
- GIT cloning URL,
- number of observers,
- date of creation (ISO format).
- 
The service API should look as follows:

- GET /repositories/{owner}?sort=stars,asc – for sort ascending
- GET /repositories/{owner}?sort=stars,desc – for sort descending
