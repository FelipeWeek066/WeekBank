package com.weeklab.weekbank.services.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String error) {
        super(error);
    }
}

