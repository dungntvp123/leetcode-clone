package com.college.leetcodeclone.common;

public enum ResponseStatus {
    AUTHENTICATION_SUCCESSFUL(200100, "Successfully Authenticated", "ok"),
    REGISTRATION_SUCCESSFUL(200101, "Successfully Registered", "ok"),

    AUTHENTICATION_FAILED(400100, "Authentication Failed", "failed")
    ;

    public long code;
    public String message;
    public String status;

    ResponseStatus(long code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
