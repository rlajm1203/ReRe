package com.econovation.rere.exception;

public class InvalidLogoutException extends RuntimeException{
    public InvalidLogoutException(){ this("로그인 되어 있지 않습니다."); }
    public InvalidLogoutException(String message){
        super(message);
    }
}
