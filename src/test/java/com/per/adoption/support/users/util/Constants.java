package com.per.adoption.support.users.util;

import com.per.adoption.support.users.dto.UserRequest;
import com.per.adoption.support.users.model.User;
import com.per.adoption.support.users.model.UserRole;

import java.util.UUID;

public class Constants {
    private Constants() {
    }

    private static final String USER_ROLE_NAME = "USER";

    public static final UserRole USER_ROLE = new UserRole(1, USER_ROLE_NAME, null);

    public static final UserRequest USER_REQUEST = new UserRequest(
            "115de516-ae8a-4db5-b63b-969c6696a848",
            "USER123", "user@gmail.com", "Spain", USER_ROLE_NAME, "2092");


    public static final User USER = User.builder()
            .userId(UUID.fromString("2b4ceeb1-b822-489d-92c0-e221d6bfc187"))
            .country("Spain")
            .email("user@gmail.com")
            .identityId(UUID.fromString("3fe8f3a0-68c4-483c-8789-538c8c1776fe"))
            .postalCode("2092")
            .name("USER123")
            .build();
}
