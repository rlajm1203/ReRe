package com.econovation.rere.exception;

public class DuplicateLoginIdException extends AlreadyExistsInEntityException{

    public DuplicateLoginIdException() { this("중복된 아이디입니다."); }
    public DuplicateLoginIdException(String message) { super(message); }
}
