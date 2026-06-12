package com.weeklab.weekbank.resources.handlers;

import com.weeklab.weekbank.entities.DTOs.StandardError;
import com.weeklab.weekbank.services.exceptions.ContentNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<StandardError> contentNotFound(ContentNotFoundException e, HttpServletRequest request){
        String error = "content not Found rapaz";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
