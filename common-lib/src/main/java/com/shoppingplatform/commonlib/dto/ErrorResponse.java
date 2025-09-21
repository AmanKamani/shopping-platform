package com.shoppingplatform.commonlib.dto;

import com.shoppingplatform.commonlib.exception.BaseException;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String message;
    private final int errorCode;

    public ErrorResponse(BaseException e) {
        this(e.getErrorCode(), e.getMessage());
    }

    public ErrorResponse(int errorCode, String message) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
