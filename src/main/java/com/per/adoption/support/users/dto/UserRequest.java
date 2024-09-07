package com.per.adoption.support.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record UserRequest(@NotEmpty String identityId,
                          @NotEmpty String name,
                          @NotEmpty @Email String email,
                          String country,
                          @NotEmpty String role,
                          String postalCode) {

    public UserRequest withRole(String role) {
        return new UserRequest(identityId, name, email, country, role, postalCode);
    }

}
