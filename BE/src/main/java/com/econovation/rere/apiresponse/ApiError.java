package com.econovation.rere.apiresponse;

import lombok.Getter;

@Getter
public class ApiError {
    private final int status;

    public ApiError(int status) {
        this.status = status;
    }
}
