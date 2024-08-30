package com.per.adoption.support.users.dto;

public record User(String identityId,
                   String name,
                   String email,
                   String country,
                   String role,
                   String postalCode) {
}
