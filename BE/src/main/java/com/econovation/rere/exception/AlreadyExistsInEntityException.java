package com.econovation.rere.exception;

public class AlreadyExistsInEntityException extends RuntimeException{

    public AlreadyExistsInEntityException(){ this("이미 존재하는 정보입니다."); }

    public AlreadyExistsInEntityException(String message){
        super(message);
    }
}
