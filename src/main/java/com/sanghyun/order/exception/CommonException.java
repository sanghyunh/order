package com.sanghyun.order.exception;


import com.sanghyun.order.constant.Errors;

public class CommonException extends RuntimeException {

    private Errors error;
    private String message;

    public CommonException(Errors e) {
        this.error = e;
        this.message = e.getMessage();
    }

    public CommonException(Errors e, String message) {
        this.error = e;
        this.message = message;
    }
    public CommonException(Errors e, Exception ex) {
        super(ex);
        this.error = e;
    }

    public Errors getError() {
        return error;
    }

    public String getMessage() {
        return this.message;
    }
}
