package com.weeklab.weekbank.services.exceptions;

public class MinimumBudgetTransferenceException extends RuntimeException{
    public  MinimumBudgetTransferenceException(String error){
        super(error);
    }
}
