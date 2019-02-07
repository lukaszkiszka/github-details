package com.lkiszka.rt.githubdetails.e2e;

import com.lkiszka.rt.githubdetails.controller.dto.RepositoryDetailsResponseDto;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 1080, files = "classpath:/wiremock/")
public class GithubRepositoryDetailsTests {

    @Value("${local.server.port}")
    private String serverPort;


    @Test
    public void shouldCorrectlyReturnGithubRepositoryDetails() {
        // given
        String requestResource = "githubdetails/v1/repositories/testOwner/testRepository";

        String str = "2016-02-06 22:05:49";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        RepositoryDetailsResponseDto expectedResult = new RepositoryDetailsResponseDto();
        expectedResult.setFullName("testOwner/testRepository");
        expectedResult.setDescription("Test repository description");
        expectedResult.setCloneUrl("https://github.com/testOwner/testRepository.git");
        expectedResult.setStars(10);
        expectedResult.setCreatedAt(LocalDateTime.parse(str, formatter));

        // when
        Response response = given().port(Integer.parseInt(serverPort))
                .contentType("application/json;charset=UTF-8")
                .when().get(requestResource);

        // then
        RepositoryDetailsResponseDto responseBody = response.then()
                .statusCode(HttpStatus.OK.value())
                .extract().body().as(RepositoryDetailsResponseDto.class);

        assertEquals(expectedResult, responseBody);
    }

    @Test
    public void shouldCorrectlyHandle404Error() {
        // given
        String requestResource = "githubdetails/v1/repositories/notExistsOwner/testRepository";


        // when
        Response response = given().port(Integer.parseInt(serverPort))
                .contentType("application/json;charset=UTF-8")
                .when().get(requestResource);

        // then
        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body(Matchers.isEmptyOrNullString());
    }

    @Test
    public void shouldCorrectlyHandle500Error() {
        // given
        String requestResource = "githubdetails/v1/repositories/serverErrorOwner/testRepository";


        // when
        Response response = given().port(Integer.parseInt(serverPort))
                .contentType("application/json;charset=UTF-8")
                .when().get(requestResource);

        // then
        response.then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(Matchers.isEmptyOrNullString());
    }

    @Test
    public void shouldCorrectlyHandleTimeoutError() {
        // given
        String requestResource = "githubdetails/v1/repositories/timeoutOwner/testRepository";


        // when
        Response response = given().port(Integer.parseInt(serverPort))
                .contentType("application/json;charset=UTF-8")
                .when().get(requestResource);

        // then
        response.then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(Matchers.isEmptyOrNullString());
    }

    @Test
    public void shouldReturnNotFoundWhenCallNotExistsResource() {
        // given
        String requestResource = "githubdetails/v1/repo/timeoutOwner/testRepository";


        // when
        Response response = given().port(Integer.parseInt(serverPort))
                .contentType("application/json;charset=UTF-8")
                .when().get(requestResource);

        // then
        response.then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}
