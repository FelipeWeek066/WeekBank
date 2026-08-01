package com.weeklab.weekbank.services.exceptions;

public class DataIntegrityException extends RuntimeException {
    public DataIntegrityException(String error) {
        super(error);
    }
}
