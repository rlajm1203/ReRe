package com.econovation.rere.exception;

public class StudyCompleteNotFoundException extends EntityNotFoundException {

    public StudyCompleteNotFoundException(){
        this("학습을 1번도 완료하지 않은 상태입니다.");
    }

    public StudyCompleteNotFoundException(String message){
        super(message);
    }

}
