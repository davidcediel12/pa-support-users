package com.per.adoption.support.users.repository;

import com.per.adoption.support.users.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    Optional<UserRole> findByName(String name);
}
