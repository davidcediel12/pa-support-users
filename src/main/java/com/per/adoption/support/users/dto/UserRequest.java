package com.per.adoption.support.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserRequest(@NotEmpty String identityId,
                          @NotEmpty String name,
                          @NotEmpty @Email String email,
                          String country,
                          @NotEmpty String role,
                          String postalCode) {
}
