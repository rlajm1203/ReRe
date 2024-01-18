package com.econovation.rere.exception;

public class SearchNotFoundException extends EntityNotFoundException{
    public SearchNotFoundException(){ this("검색 결과가 존재하지 않습니다."); }

    public SearchNotFoundException(String message){
        super(message);
    }

}
