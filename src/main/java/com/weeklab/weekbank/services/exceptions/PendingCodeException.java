package com.weeklab.weekbank.services.exceptions;

public class PendingCodeException extends RuntimeException{
    public PendingCodeException(String error){
        super(error);
    }
}
