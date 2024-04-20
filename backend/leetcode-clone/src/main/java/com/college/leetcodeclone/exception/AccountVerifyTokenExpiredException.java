package com.college.leetcodeclone.exception;

public class AccountVerifyTokenExpiredException extends RuntimeException {
    public AccountVerifyTokenExpiredException(String message) {
        super(message);
    }

    public AccountVerifyTokenExpiredException() {
        super();
    }
}
