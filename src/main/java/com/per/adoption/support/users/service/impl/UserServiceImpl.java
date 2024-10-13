package com.per.adoption.support.users.service.impl;

import com.per.adoption.support.users.dto.CreatedUser;
import com.per.adoption.support.users.dto.UserRequest;
import com.per.adoption.support.users.dto.UserResponse;
import com.per.adoption.support.users.exception.ApiException;
import com.per.adoption.support.users.mapper.UserMapper;
import com.per.adoption.support.users.model.User;
import com.per.adoption.support.users.model.UserRole;
import com.per.adoption.support.users.repository.UserRepository;
import com.per.adoption.support.users.repository.UserRoleRepository;
import com.per.adoption.support.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.per.adoption.support.users.util.ErrorConstants.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public CreatedUser saveUser(UserRequest userRequest) {

        UserRole role = userRoleRepository.findByName(userRequest.role()).orElseThrow(() ->
                ROLE_DOES_NOT_EXISTS_EXCEPTION);

        User user = userMapper.requestToEntity(userRequest);
        user.setRole(role);

        log.debug("user to save {}", user);

        user = userRepository.save(user);

        return userMapper.entityToResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByIdentityId(String identityId) {

        UUID identity;
        try {
            identity = UUID.fromString(identityId);
        } catch (IllegalArgumentException e) {
            throw new ApiException(WRONG_PARAMETERS, HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.findByIdentityId(identity)
                .orElseThrow(() -> USER_DOES_NOT_EXISTS_EXCEPTION);

        return userMapper.entityToUserResponse(user);
    }
}
