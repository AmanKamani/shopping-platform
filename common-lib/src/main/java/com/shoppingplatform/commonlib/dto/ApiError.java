package com.shoppingplatform.commonlib.dto;

import org.springframework.http.HttpStatus;

public record ApiError(
        HttpStatus httpStatus,
        String message,
        int errorCode
) {
}
