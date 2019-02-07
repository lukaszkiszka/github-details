package com.lkiszka.rt.githubdetails.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * @author Lukasz Kiszka
 */
public class RepositoryApiException extends HttpStatusCodeException {

    public RepositoryApiException(HttpStatus statusCode) {
        super(statusCode);
    }
}
