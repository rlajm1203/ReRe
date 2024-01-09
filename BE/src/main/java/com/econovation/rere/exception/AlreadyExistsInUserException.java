package com.econovation.rere.exception;

public class AlreadyExistsInUserException extends AlreadyExistsInEntityException{
    public AlreadyExistsInUserException(){ this("이미 존재하는 사용자 입니다."); }

    public AlreadyExistsInUserException(String message){
        super(message);
    }
}
