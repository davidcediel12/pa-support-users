package com.per.adoption.support.users.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class CreatedUser {
    private Long id;
    private UUID userId;

    private OffsetDateTime createdAt;
}
