package com.cognizant.garage.web.exception;

import com.cognizant.garage.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalRestException {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundHandler(Exception e) {
        log.error("Id not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
}