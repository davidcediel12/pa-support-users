package com.per.adoption.support.users.repository;

import com.per.adoption.support.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByIdentityId(UUID identityId);
}