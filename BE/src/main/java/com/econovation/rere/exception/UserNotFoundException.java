package com.econovation.rere.exception;

public class UserNotFoundException extends EntityNotFoundException{
    public UserNotFoundException(String message){
        super(message);
    }
}
