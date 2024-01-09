package com.econovation.rere.exception;

public class InvalidLoginException extends RuntimeException {

    public InvalidLoginException(){ this("아이디와 비밀번호가 일치하지 않습니다."); }
    public InvalidLoginException(String message){
        super(message);
    }

}
