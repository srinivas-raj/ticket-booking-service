package com.veda.online.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TicketExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {TicketSelectedException.class})
    public Object handleConflict(TicketSelectedException ticketSelectedException) {
        return new ResponseEntity<Object>(
                ticketSelectedException.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}

