package com.lkiszka.rt.githubdetails.controller;

import com.lkiszka.rt.githubdetails.application.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Lukasz Kiszka
 */
@ControllerAdvice
@Slf4j
class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    protected ResponseEntity<?> handleDefault(RuntimeException ex, WebRequest request) {
        log.error("Default exception handler handle exception", ex);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<?> handleNotFoundException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }
}
