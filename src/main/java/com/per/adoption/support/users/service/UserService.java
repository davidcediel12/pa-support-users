package com.per.adoption.support.users.service;

import com.per.adoption.support.users.dto.CreatedUser;
import com.per.adoption.support.users.dto.UserRequest;

public interface UserService {
    CreatedUser saveUser(UserRequest userRequest);
}
