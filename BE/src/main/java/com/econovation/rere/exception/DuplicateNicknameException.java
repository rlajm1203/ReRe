package com.econovation.rere.exception;

public class DuplicateNicknameException extends RuntimeException {

    private DuplicateNicknameException() { this("중복된 아이디입니다."); }
    public DuplicateNicknameException(String message) { super(message); }

}
