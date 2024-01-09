package com.econovation.rere.exception;

public class AlreadyExistsInLoginIdException extends AlreadyExistsInUserException{
    public AlreadyExistsInLoginIdException(){
        this("이미 존재하는 ID 입니다.");
    }

    public AlreadyExistsInLoginIdException(String message){
        super(message);
    }
}
