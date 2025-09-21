package com.shoppingplatform.commonlib.constant;

import com.shoppingplatform.commonlib.dto.ApiError;
import org.springframework.http.HttpStatus;

public final class GeneralApiErrors {
    private GeneralApiErrors() {
        // do nothing
    }


    private static final int CODE = 0;

    public static final ApiError UNKNOWN_ERROR = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error", CODE + 1);
}
