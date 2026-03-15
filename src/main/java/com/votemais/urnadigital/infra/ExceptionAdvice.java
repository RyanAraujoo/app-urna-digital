package com.votemais.urnadigital.infra;

import com.votemais.urnadigital.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<String> notFoundHandler (NotFoundException notFoundExceptions) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundExceptions.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> exceptionHandler (Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
