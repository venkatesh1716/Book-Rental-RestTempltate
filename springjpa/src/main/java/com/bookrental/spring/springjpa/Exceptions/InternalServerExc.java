package com.bookrental.spring.springjpa.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InternalServerExc extends RuntimeException
{
    public InternalServerExc(String message) {
        super(message);
    }
}
