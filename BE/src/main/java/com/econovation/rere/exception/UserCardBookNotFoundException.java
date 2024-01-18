package com.econovation.rere.exception;

import com.econovation.rere.domain.entity.UserCardBook;

public class UserCardBookNotFoundException extends EntityNotFoundException{
    public UserCardBookNotFoundException() { this("카드북을 담지 않았습니다."); }

    public UserCardBookNotFoundException(String message){
        super(message);
    }
}
