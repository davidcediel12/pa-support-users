package com.per.adoption.support.users.exception;

import com.per.adoption.support.users.dto.Error;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final Error error;
    private final HttpStatus httpStatusCode;

    public ApiException(Error error, HttpStatus httpStatusCode) {
        super(error.message());
        this.error = error;
        this.httpStatusCode = httpStatusCode;
    }
}
