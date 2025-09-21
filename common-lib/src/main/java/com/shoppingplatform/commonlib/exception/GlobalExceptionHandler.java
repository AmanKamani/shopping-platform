package com.shoppingplatform.commonlib.exception;

import com.shoppingplatform.commonlib.constant.GeneralApiErrors;
import com.shoppingplatform.commonlib.dto.ApiError;
import com.shoppingplatform.commonlib.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(new ErrorResponse(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ApiError badRequest = GeneralApiErrors.BAD_REQUEST;
        Map<String, String> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, fieldError -> fieldError.getDefaultMessage() == null ? "" : fieldError.getDefaultMessage()));

        return ResponseEntity
                .status(badRequest.httpStatus())
                .body(new ErrorResponse(badRequest.errorCode(), badRequest.message(), errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ApiError unknownError = GeneralApiErrors.UNKNOWN_ERROR;
        return ResponseEntity
                .status(unknownError.httpStatus())
                .body(new ErrorResponse(unknownError.errorCode(), e.getMessage()));
    }
}
