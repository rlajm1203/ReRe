package com.econovation.rere.exception;

public class ThemeNotFoundException extends EntityNotFoundException{

    public ThemeNotFoundException(){ this("해당 목차가 존재하지 않습니다."); }
    public ThemeNotFoundException(String message){
        super(message);
    }
}
