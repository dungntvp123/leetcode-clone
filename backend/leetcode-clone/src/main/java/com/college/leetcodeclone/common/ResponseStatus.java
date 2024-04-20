package com.college.leetcodeclone.common;

public enum ResponseStatus {
    AUTHENTICATION_SUCCESSFUL(200001, "Successfully Authenticated", "ok"),
    REGISTRATION_SUCCESSFUL(201001, "Successfully Registered", "ok"),

    REGISTRATION_INFORMATION_CONSTRAINT_VIOLATE(400001, "registration information violate constraint", "error"),
    AUTHENTICATION_FAILED(401100, "Authentication Failed", "failed")
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
