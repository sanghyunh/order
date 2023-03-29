package com.sanghyun.order.exception;

import com.sanghyun.order.constant.Errors;

public class AuthorizedException extends RuntimeException {

    private Errors error;
    private String message;

    public AuthorizedException(Errors e) {
        this.error = e;
        this.message = e.getMessage();
    }

    public Errors getError() {
        return error;
    }

    public String getMessage() {
        return this.message;
    }
}
