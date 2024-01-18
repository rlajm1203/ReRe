package com.econovation.rere.exception;

public class UserNotFoundException extends EntityNotFoundException{

    public UserNotFoundException(){ this("해당 사용자가 존재하지 않습니다."); }
    public UserNotFoundException(String message){
        super(message);
    }
}
