package com.shoppingplatform.commonlib.dto;

import com.shoppingplatform.commonlib.exception.BaseException;
import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorResponse {
    private final String message;
    private final int errorCode;
    private final Map<String, String> subErrors;

    public ErrorResponse(BaseException e) {
        this(e.getErrorCode(), e.getMessage(), null);
    }

    public ErrorResponse(int errorCode, String message) {
        this(errorCode, message, null);
    }

    public ErrorResponse(int errorCode, String message, Map<String, String> subErrors) {
        this.message = message;
        this.errorCode = errorCode;
        this.subErrors = subErrors;
    }
}
