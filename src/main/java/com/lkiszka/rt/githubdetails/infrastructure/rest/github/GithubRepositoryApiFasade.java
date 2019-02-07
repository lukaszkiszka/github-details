package com.lkiszka.rt.githubdetails.infrastructure.rest.github;

import com.lkiszka.rt.githubdetails.application.exceptions.NotFoundException;
import com.lkiszka.rt.githubdetails.application.exceptions.RepositoryApiException;
import com.lkiszka.rt.githubdetails.application.exceptions.RepositoryApiTechnicalException;
import com.lkiszka.rt.githubdetails.infrastructure.rest.RepositoryDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

/**
 * @author Lukasz Kiszka
 */
@Repository
@Slf4j
public class GithubRepositoryApiFasade implements RepositoryDetails<GithubRepositoryDetailsDto> {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${repositories.github.apiUrl}")
    private String apiUrl;

    @Value("application/vnd.github.${repositories.github.apiVersion}+json")
    private String acceptHeader;

    private <R> R callGithubApi(@NotNull String requestUrl, @NotNull Class<R> resultClass) throws RepositoryApiException {

        HttpHeaders requestHeaders = new HttpHeaders();

        requestHeaders.set(HttpHeaders.ACCEPT, acceptHeader);
        HttpEntity httpEntity = new HttpEntity<>(requestHeaders);
        String url = String.format("%s/%s", apiUrl, requestUrl);

        try {
            ResponseEntity<R> response = restTemplate.
                    exchange(url, HttpMethod.GET, httpEntity, resultClass);

            return response.getBody();

        } catch (HttpStatusCodeException httpStatusCodeException) {
            if (httpStatusCodeException.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException();
            }

            log.error("HttpStatusCodeException while call GihubApi", httpStatusCodeException);
            throw new RepositoryApiException(httpStatusCodeException.getStatusCode());
        } catch (Exception exception) {
            log.error("Exception while call GihubApi", exception);
            throw new RepositoryApiTechnicalException(exception.getMessage(), exception.getCause());
        }

    }

    @Override
    public GithubRepositoryDetailsDto getDetails(String owner, String repositoryName) {

        final String requestUrl = String.format("repos/%s/%s", owner, repositoryName);
        return callGithubApi(requestUrl, GithubRepositoryDetailsDto.class);
    }
}
