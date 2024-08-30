package com.per.adoption.support.users.service.impl;

import com.per.adoption.support.users.dto.CreatedUser;
import com.per.adoption.support.users.dto.UserRequest;
import com.per.adoption.support.users.mapper.UserMapper;
import com.per.adoption.support.users.model.User;
import com.per.adoption.support.users.repository.UserRepository;
import com.per.adoption.support.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public CreatedUser saveUser(UserRequest userRequest) {

        User user = userRepository.save(userMapper.requestToEntity(userRequest));

        return userMapper.entityToResponse(user);
    }
}
