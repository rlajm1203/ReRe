package com.econovation.rere.apiresponse;

import lombok.Getter;

@Getter
public class ApiResult<T> {
    private final boolean success;
    private final T response;
    private final String message;
    private final ApiError apiError;

    public ApiResult(boolean success, T response, ApiError apiError, String message) {
        this.success = success;
        this.response = response;
        this.message = message;
        this.apiError = apiError;
    }
}
