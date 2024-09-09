package com.per.adoption.support.users.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(nullable = false, updatable = false, unique = true)
    private UUID userId;
    @Column(nullable = false, updatable = false, unique = true)
    private UUID identityId;
    @UpdateTimestamp
    private OffsetDateTime createdAt;
    @CreationTimestamp
    private OffsetDateTime updatedAt;
    @Column(name = "user_name", nullable = false, updatable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 90, unique = true)
    private String email;
    @Column(length = 10)
    private String postalCode;
    @Column(length = 90)
    private String country;
    @ManyToOne
    @JoinColumn(name = "user_role", referencedColumnName = "id", nullable = false)
    private UserRole role;

}
