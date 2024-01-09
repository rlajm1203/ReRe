package com.econovation.rere.controller;


import com.econovation.rere.apiresponse.ApiResult;
import com.econovation.rere.apiresponse.ApiUtils;
import com.econovation.rere.exception.AlreadyExistsInUserCardBookException;
import com.econovation.rere.exception.DuplicateLoginIdException;
import com.econovation.rere.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.AlreadyBoundException;

@RestControllerAdvice
// @RestCotrollerAdvice는 @ControllerAdvice와 @ResponseBody를 가지고 있다.
// @ControllerAdvice는 @Controller와 handler에서 발생하는 에러들을 모두 잡아준다.
// @ControllerAdvice 안에서 @ExceptionHandler를 사용하여 에러를 잡을 수 있다.
public class GlobalExceptionHandler {

//    카드북/목차/사용자/유저가 존재하지 않을 경우에 대한 예외 처리
    @ExceptionHandler(EntityNotFoundException.class)
    public ApiResult<?> handlerEntityNotFoundException(EntityNotFoundException e){
        return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsInUserCardBookException.class)
    public ApiResult<?> handlerAlreadyExistsInUserCardBookExcepion(AlreadyExistsInUserCardBookException e){
        return ApiUtils.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ApiUtils.error("값을 입력해주세요.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateLoginIdException.class)
    public ApiResult<?> handlerduplicateLoginIdException(DuplicateLoginIdException e){
        return ApiUtils.error("중복된 아이디 입니다.", HttpStatus.BAD_REQUEST);
    }
}
