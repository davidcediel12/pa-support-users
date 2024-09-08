package com.per.adoption.support.users.service.impl;

import com.per.adoption.support.users.exception.ApiException;
import com.per.adoption.support.users.mapper.UserMapper;
import com.per.adoption.support.users.model.User;
import com.per.adoption.support.users.repository.UserRepository;
import com.per.adoption.support.users.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.per.adoption.support.users.util.Constants.USER_REQUEST;
import static com.per.adoption.support.users.util.Constants.USER_ROLE;
import static com.per.adoption.support.users.util.ErrorConstants.ROLE_DOES_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserRepository userRepository;
    @Mock
    UserRoleRepository userRoleRepository;
    @Mock
    UserMapper userMapper;


    @Test
    void shouldThrowExceptionWhenSavingUserWithNonexistentRole() {

        when(userRoleRepository.findByName(anyString())).thenReturn(Optional.empty());

        ApiException apiException = assertThrows(ApiException.class,
                () -> userService.saveUser(USER_REQUEST));

        assertEquals(ROLE_DOES_NOT_EXISTS, apiException.getError());

    }

    @Test
    void shouldSaveUser() {
        when(userRoleRepository.findByName(anyString())).thenReturn(Optional.of(USER_ROLE));
        when(userMapper.requestToEntity(USER_REQUEST)).thenReturn(new User());

        userService.saveUser(USER_REQUEST);

        verify(userRepository, times(1)).save(any());
    }


}