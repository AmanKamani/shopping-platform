package com.shoppingplatform.commonlib.exception;

import com.shoppingplatform.commonlib.constant.GeneralApiErrors;
import com.shoppingplatform.commonlib.dto.ApiError;
import com.shoppingplatform.commonlib.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ApiError unknownError = GeneralApiErrors.UNKNOWN_ERROR;
        return ResponseEntity
                .status(unknownError.httpStatus())
                .body(new ErrorResponse(unknownError.errorCode(), e.getMessage()));
    }
}
