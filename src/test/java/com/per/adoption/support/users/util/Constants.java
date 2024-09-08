package com.per.adoption.support.users.util;

import com.per.adoption.support.users.dto.UserRequest;
import com.per.adoption.support.users.model.UserRole;

public class Constants {
    private Constants() {
    }

    private static final String USER_ROLE_NAME = "USER";

    public static final UserRole USER_ROLE = new UserRole(1, USER_ROLE_NAME, null);

    public static final UserRequest USER_REQUEST = new UserRequest(
            "115de516-ae8a-4db5-b63b-969c6696a848",
            "USER123", "user@gmail.com", "Spain", USER_ROLE_NAME, "2092");
}
