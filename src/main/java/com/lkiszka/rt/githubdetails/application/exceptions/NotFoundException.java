package com.lkiszka.rt.githubdetails.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * @author Lukasz Kiszka
 */
public class NotFoundException extends HttpStatusCodeException {

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }


}
