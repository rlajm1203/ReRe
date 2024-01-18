package com.econovation.rere.exception;

public class ImageNotLoadException extends RuntimeException {
    public ImageNotLoadException() { this("해당 이미지를 로드할 수 없습니다."); }
    public ImageNotLoadException(String message){
        super(message);
    }
}
