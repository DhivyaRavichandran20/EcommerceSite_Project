package com.SpringBoot.EcommerceSiteProject.Common;

import java.time.LocalDateTime;

public class ApiResponse {
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private final boolean success;
    private final String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp(){
        return LocalDateTime.now().toString();
    }
}
