package com.sanghyun.order.entity;

import com.sanghyun.order.constant.Errors;
import lombok.Data;

@Data
public class ExceptionResponse {

    private int status;
    private String title;
    private String message;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Errors e) {
        this.status = e.getCode();
        this.title = e.getTitle();
        this.message = e.getMessage();
    }
}
