package com.per.adoption.support.users.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_role")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRole {

    @Id
    Integer id;
    @Column(name = "role_name", nullable = false, length = 50)
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_role", referencedColumnName = "id")
    private UserRole parentRole;
}
