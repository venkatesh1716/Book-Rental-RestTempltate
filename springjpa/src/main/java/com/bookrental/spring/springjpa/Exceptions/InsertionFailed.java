package com.bookrental.spring.springjpa.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsertionFailed extends RuntimeException {
    public InsertionFailed(String message) {
        super(message);
    }
}

