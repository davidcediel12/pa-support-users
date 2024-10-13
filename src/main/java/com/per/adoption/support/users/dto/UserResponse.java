package com.per.adoption.support.users.dto;

import java.time.OffsetDateTime;

public record UserResponse (
    String identityId, String name,
    String email, String country,
    String role, String postalCode,
    String userId, OffsetDateTime createdAt,
    OffsetDateTime updatedAt
){}
