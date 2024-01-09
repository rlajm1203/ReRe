package com.econovation.rere.exception;

public class AlreadyExistsInPwException extends AlreadyExistsInUserException{
    public AlreadyExistsInPwException() {
        this("이미 존재하는 비밀번호 입니다.");
    }

    public AlreadyExistsInPwException(String message){
        super(message);
    }
}
