package com.weeklab.weekbank.resources.handlers;

import com.weeklab.weekbank.entities.DTOs.StandardError;
import com.weeklab.weekbank.services.exceptions.*;
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
    @ExceptionHandler(NameAlreadyChoosenException.class)
    public ResponseEntity<StandardError> alreadyChooseName(NameAlreadyChoosenException e, HttpServletRequest request){
        String error = "someone already choose that name.";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<StandardError> notEnoughMoney(NotEnoughMoneyException e, HttpServletRequest request){
        String error = "you dont have enough money bro.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PendingCodeException.class)
    public ResponseEntity<StandardError> pendingCode(PendingCodeException e, HttpServletRequest request){
        String error = "there's already a Code for that name";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MinimumBudgetTransferenceException.class)
    public ResponseEntity<StandardError> pendingCode(MinimumBudgetTransferenceException e, HttpServletRequest request){
        String error = "you cant send that amount, its too low";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
