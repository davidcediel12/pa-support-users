package com.per.adoption.support.users.dto;

public record UserRequest(String identityId,
                          String name,
                          String email,
                          String country,
                          String role,
                          String postalCode) {
}
