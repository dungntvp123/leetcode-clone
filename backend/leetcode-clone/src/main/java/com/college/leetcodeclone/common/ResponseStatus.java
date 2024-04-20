package com.college.leetcodeclone.common;

public enum ResponseStatus {
    AUTHENTICATION_SUCCESSFUL(200001, "Successfully Authenticated", "ok"),
    REGISTRATION_SUCCESSFUL(201001, "Successfully Registered", "ok"),
    ACCOUNT_VERIFICATION_SUCCESSFUL(200002, "Successfully verify account", "ok"),
    RESET_PASSWORD_SUCCESSFUL(200003, "Successfully reset password", "ok"),

    REGISTRATION_INFORMATION_CONSTRAINT_VIOLATE(400001, "Registration information violate constraint", "error"),
    RESET_PASSWORD_INFORMATION_CONSTRAINT_VIOLATE(400002, "Reset password information violate constraint", "error"),
    INCORRECT_AUTHENTICATION_INFORMATION(401100, "Username or password incorrect", "error"),
    DISABLED_ACCOUNT(401101, "Account has been disabled", "error"),
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
