package com.shoppingplatform.commonlib.exception;

import com.shoppingplatform.commonlib.dto.ApiError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final int errorCode;

    public BaseException(ApiError apiError, String message) {
        super(message);
        this.httpStatus = apiError.httpStatus();
        this.errorCode = apiError.errorCode();
    }

    public BaseException(ApiError apiError) {
        this(apiError, apiError.message());
    }
}
