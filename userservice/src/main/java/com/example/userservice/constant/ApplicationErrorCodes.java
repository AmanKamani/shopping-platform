package com.example.userservice.constant;

import com.shoppingplatform.commonlib.dto.ApiError;
import org.springframework.http.HttpStatus;

public class ApplicationErrorCodes {

    private ApplicationErrorCodes() {
        // do nothing
    }

    public static final int CODE = 1000;

    public static final ApiError USER_NOT_FOUND = new ApiError(HttpStatus.NOT_FOUND, "User not found", CODE + 1);
    public static final ApiError DUPLICATE_USER_EXIST = new ApiError(HttpStatus.BAD_REQUEST, "User already exists", CODE + 2);
    public static final ApiError INVALID_CREDENTIALS = new ApiError(HttpStatus.BAD_REQUEST, "Invalid Credentials", CODE + 3);
    public static final ApiError INCORRECT_PASSWORD = new ApiError(HttpStatus.BAD_REQUEST, "Invalid old password", CODE + 4);
}
