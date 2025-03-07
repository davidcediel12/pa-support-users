package com.per.adoption.support.users.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issuer {
    @Column(length = 90, nullable = false, unique = true)
    private String id;

    @Column(length = 90, nullable = false)
    private String name;

}
