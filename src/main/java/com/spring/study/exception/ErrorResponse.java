package com.spring.study.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private String errorCode;
    private List<String> errorMessage;

    public ErrorResponse(String errorCode, List<String> errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = (errorMessage != null) ? errorMessage : new ArrayList<>();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }
}