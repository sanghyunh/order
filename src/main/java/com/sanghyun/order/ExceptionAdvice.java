package com.sanghyun.order;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sanghyun.order.exception.BindingException;
import com.sanghyun.order.exception.CommonException;
import com.sanghyun.order.exception.ExceptionResponse;

@ControllerAdvice
public class ExceptionAdvice implements ErrorController {

    @ExceptionHandler(value = BindingException.class)
    public ResponseEntity<ExceptionResponse> bindingException(HttpServletRequest request, BindingException e) {
        FieldError fe = e.getFieldError();

        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setTitle("에러");
        response.setMessage("'" + fe.getField() + "' " + fe.getDefaultMessage());

        request.setAttribute("warn", e);
        request.setAttribute("warn-response", response);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ExceptionResponse> commonException(HttpServletRequest request, CommonException e) {

        ExceptionResponse response = new ExceptionResponse(e.getError());
        response.setStatus(e.getError().getCode());
        response.setTitle(e.getError().getTitle());
        response.setMessage(e.getMessage());

        request.setAttribute("warn", e);
        request.setAttribute("warn-response", response);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
