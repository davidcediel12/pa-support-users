package com.per.adoption.support.users.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, updatable = false)
    private UUID userId;
    @Column(nullable = false, updatable = false)
    private UUID identityId;
    @UpdateTimestamp
    private OffsetDateTime createdAt;
    @CreationTimestamp
    private OffsetDateTime updatedAt;
    @Column(name = "user_name", nullable = false, updatable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 90)
    private String email;
    @Column(length = 10)
    private String postalCode;
    @Column(length = 90)
    private String country;
    @ManyToOne
    @JoinColumn(name = "user_role", referencedColumnName = "id", nullable = false)
    private UserRole role;

}
