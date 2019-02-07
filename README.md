# github-details service

Service for get information about some github repository by repository owner and repository name

Service is powered by *spring-boot* framework.

---

## Compile and Run
Project based on maven build tool.

* Compile code
> mvn clean install

* Run service
> mvn spring-boot:run

## Tests

### Unit tests
Unit tests tests mapper class. Run only unit tests by command:
> mvn surefire:test


### E2e tests
E2e tests tests integration with github API.
In project was created two e2e tests:
* spring tests - with uses wiremock and rest-assured
* postman tests

#### Spring tests
This tests testing `github-details` service API and integration with Github API.
Github API is mocked by wiremock.<br/> 
Directory `wiremock` in test resources including wiremock mappings and example responses.
Response files contains real service response payload.

In test application settings file `application.yml` is set github API address to wiremock service.
For run these tests on real API should change Github API url in test `application.yml` to real service address.

Run only e2e tests by command:
> mvn failsafe:integration-test

#### Postman tests
Project contains postman collection file `github-details.postman-example-request-and-tests.json`. <br/>

Postman collection contains:

* variables - in brackets is default values
    * serviceUrl (localhost:8080) - should contains github-details service url address with port
    * repositoryOwner (spring-projects) - should contains github repository owner name
    * repositoryName (spring-framework) - should contains github repository name belonging to set owner
* example request for `github-details` service (*ExampleRequestTemplateGithubDetailsServiceApi*)
* example request for Github API (*ExampleRequestGithubApi*)
* direcotry `test` with tests request. 
    * these requests contains script to setting environment variable depends on test case and response assertion.
    * for run all tests in this directory use `Postman Collection Runner` tool.


### Swagger Gui
Service expose *Swagger UI* page for testing. Page is available at default address:
> http://localhost:8080/swagger-ui.html


