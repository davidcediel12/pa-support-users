package com.per.adoption.support.users.dto;

import lombok.*;

import java.time.OffsetDateTime;


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class CreatedUser {
    private Long id;
    private OffsetDateTime createdAt;
}
