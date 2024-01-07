package com.econovation.rere.exception;

public class EntityNotFoundException extends RuntimeException{

    private EntityNotFoundException() { this("해당 정보가 존재하지 않습니다."); }
    public EntityNotFoundException(String message){
        super(message);
    }
}
