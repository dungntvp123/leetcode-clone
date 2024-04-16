package com.college.leetcodeclone.exception.advice;

import com.college.leetcodeclone.common.ResponseBody;
import com.college.leetcodeclone.common.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.BadAttributeValueExpException;

@ControllerAdvice
public class BadCredentialsExceptionAdvice {
    @org.springframework.web.bind.annotation.ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBody handle() {
        return new ResponseBody(ResponseStatus.AUTHENTICATION_FAILED);
    }
}
