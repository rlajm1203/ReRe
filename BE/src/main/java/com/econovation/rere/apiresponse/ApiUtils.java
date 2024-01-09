package com.econovation.rere.apiresponse;

import org.springframework.http.HttpStatus;

public class ApiUtils {
    public static <T>ApiResult<T> success(T response, String message) {
        return new ApiResult<>(true, response, null, message);
    }

    public static ApiResult<?> error(String message, HttpStatus status){
        return new ApiResult<>(false, null, new ApiError(status.value()), message);
    }

    // 인스턴스 생성 방지(유틸리티 클래스의 정적 메서드만을 사용하도록 강제)
    private ApiUtils() {
    }
}
