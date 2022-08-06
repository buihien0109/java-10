package com.example.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {
    // Xử lý not found
    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage handleNotFoundException(NotFoundException e) {
        return new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    }

    // Xử lý bad request
    @ExceptionHandler(BadRequestException.class)
    public ErrorMessage handleBadRequestException(BadRequestException e) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    // Xử lý các exception còn lại
    @ExceptionHandler(Exception.class)
    public ErrorMessage handleOtherException(Exception e) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
