package com.econovation.rere.exception;

public class AlreadyExistsInUserCardBookException extends RuntimeException{

    public AlreadyExistsInUserCardBookException(){ this("해당 카드북을 이미 담았습니다.");}
    public AlreadyExistsInUserCardBookException(String message){
        super(message);
    }
}