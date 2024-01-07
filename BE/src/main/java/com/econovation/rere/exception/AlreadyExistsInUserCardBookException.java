package com.econovation.rere.exception;

public class AlreadyExistsInUserCardBookException extends RuntimeException{
    public AlreadyExistsInUserCardBookException(String message){
        super(message);
    }
}