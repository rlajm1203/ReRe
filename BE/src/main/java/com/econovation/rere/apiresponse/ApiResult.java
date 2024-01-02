package com.econovation.rere.apiresponse;

import lombok.Getter;

@Getter
public class ApiResult<T> {
    private final boolean success;
    private final T response;
    private final ApiError apiError;

    public ApiResult(boolean success, T response, ApiError apiError) {
        this.success = success;
        this.response = response;
        this.apiError = apiError;
    }
}
