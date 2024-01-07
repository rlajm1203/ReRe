package com.econovation.rere.exception;

public class CardNotFoundException extends EntityNotFoundException{

    public CardNotFoundException(){ this("해당 카드가 존재하지 않습니다."); }
    public CardNotFoundException(String message){
        super(message);
    }
}
