package com.weeklab.weekbank.services.exceptions;

public class ContentNotFoundException extends RuntimeException{
    public  ContentNotFoundException(Object id){
        super ("Resource not found: " + id);
    }

}
