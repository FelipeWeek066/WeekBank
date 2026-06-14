package com.weeklab.weekbank.services.exceptions;

public class NameAlreadyChoosenException extends RuntimeException{
    public NameAlreadyChoosenException(String error){
        super(error);
    }
}
