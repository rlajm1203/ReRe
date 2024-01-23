package com.econovation.rere.exception;

public class NotAthenticationException extends RuntimeException{

    public NotAthenticationException(){ super("권한이 없습니다.");}

    public NotAthenticationException(String message){
        super(message);
    }
}
