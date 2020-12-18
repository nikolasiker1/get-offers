package com.nikolasiker.lib_api.model;

public class BaseResponse {
    protected String code;
    protected String message;

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
