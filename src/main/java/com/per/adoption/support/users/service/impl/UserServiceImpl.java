package com.per.adoption.support.users.service.impl;

import com.per.adoption.support.users.dto.CreatedUser;
import com.per.adoption.support.users.dto.UserRequest;
import com.per.adoption.support.users.mapper.UserMapper;
import com.per.adoption.support.users.model.User;
import com.per.adoption.support.users.model.UserRole;
import com.per.adoption.support.users.repository.UserRepository;
import com.per.adoption.support.users.repository.UserRoleRepository;
import com.per.adoption.support.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;

    @Override
    public CreatedUser saveUser(UserRequest userRequest) {

        UserRole role = userRoleRepository.findByName(userRequest.role()).orElseThrow(() ->
                new IllegalStateException("User role does not exists"));

        User user = userMapper.requestToEntity(userRequest);
        user.setRole(role);

        log.debug("user to save {}",  user);

        user = userRepository.save(user);

        return userMapper.entityToResponse(user);
    }
}
