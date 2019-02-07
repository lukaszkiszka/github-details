package com.lkiszka.rt.githubdetails.application.exceptions;

/**
 * @author Lukasz Kiszka
 */
public class RepositoryApiTechnicalException extends RuntimeException {

    public RepositoryApiTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
