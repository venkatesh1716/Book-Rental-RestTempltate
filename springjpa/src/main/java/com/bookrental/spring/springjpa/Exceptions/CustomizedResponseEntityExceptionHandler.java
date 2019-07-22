package com.bookrental.spring.springjpa.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerExc.class)
    public final ResponseEntity<Object>handleInternalServerExc (Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),"Duplicates are not allowed");
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),"Check the details");
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsertionFailed.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Validation Failed","Check the email pattern");
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
@ExceptionHandler(InsertIdExc.class)
    private ResponseEntity<Object> handleMethodArgumentNotValid(ExceptionResponse exceptionResponse, HttpStatus badRequest) {
        exceptionResponse = new ExceptionResponse(new Date(), exceptionResponse.getMessage(),"Check the id pattern");
        return handleMethodArgumentNotValid(exceptionResponse, HttpStatus.BAD_REQUEST );
    }


}
