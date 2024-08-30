package com.per.adoption.support.users.repository;

import com.per.adoption.support.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}