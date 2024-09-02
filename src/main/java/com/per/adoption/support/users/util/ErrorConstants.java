package com.per.adoption.support.users.util;

import com.per.adoption.support.users.dto.Error;
import com.per.adoption.support.users.exception.ApiException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorConstants {

    private ErrorConstants() {}

    public static final Error ROLE_DOES_NOT_EXISTS = new Error(
            "SP-01", "Role doesn't exists", List.of());

    public static final ApiException ROLE_DOES_NOT_EXISTS_EXCEPTION =
            new ApiException(ROLE_DOES_NOT_EXISTS, HttpStatus.BAD_REQUEST);
}
