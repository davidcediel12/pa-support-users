package com.per.adoption.support.users.controller;

import com.per.adoption.support.users.dto.Error;
import com.per.adoption.support.users.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
@Slf4j
public class UserControllerAdvice {


    @ExceptionHandler(ApiException.class)
    private ResponseEntity<Error> handleApiException(final ApiException e) {
        return new ResponseEntity<>(e.getError(), e.getHttpStatusCode());
    }

    @ExceptionHandler
    private ResponseEntity<Error> handleDataIntegrityViolation(final DataIntegrityViolationException e) {

        log.error("Error while interacting with the database {}", e.getMessage());

        String errorMessage;
        Pattern pattern = Pattern.compile("\\([^)]*\\)");
        Matcher matcher = pattern.matcher(e.getMessage());

        if(matcher.find()) {
            String fieldName;
            fieldName = matcher.group();
            fieldName = fieldName.substring(1, fieldName.length() - 1);
            errorMessage = "Error validating the field '" + fieldName + "', please try again with other value";
        } else {
            errorMessage =  e.getMessage();
        }
        return new ResponseEntity<>(
                new Error("SP-99", errorMessage, List.of(e.getMessage())),
                HttpStatus.CONFLICT);
    }
}
