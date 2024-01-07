package com.econovation.rere.exception;

public class CardBookNotFoundException extends EntityNotFoundException{
    public CardBookNotFoundException(){ this("해당 카드북이 존재하지 않습니다."); }
    public CardBookNotFoundException(String message){
        super(message);
    }
}
