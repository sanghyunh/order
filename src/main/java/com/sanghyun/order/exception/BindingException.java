package com.sanghyun.order.exception;

import org.springframework.validation.FieldError;

public class BindingException extends RuntimeException {

    private FieldError fieldError;

    public BindingException(FieldError error) {
        this.fieldError = error;
    }

    public FieldError getFieldError() {
        return fieldError;
    }
}
