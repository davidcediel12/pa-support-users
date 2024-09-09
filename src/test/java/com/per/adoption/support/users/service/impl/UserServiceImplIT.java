package com.per.adoption.support.users.service.impl;


import com.per.adoption.support.users.config.PostgresContainerInitializer;
import com.per.adoption.support.users.dto.UserRequest;
import com.per.adoption.support.users.exception.ApiException;
import com.per.adoption.support.users.mapper.UserMapper;
import com.per.adoption.support.users.repository.UserRepository;
import com.per.adoption.support.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static com.per.adoption.support.users.util.Constants.USER;
import static com.per.adoption.support.users.util.Constants.USER_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(UserServiceImpl.class)
class UserServiceImplIT implements PostgresContainerInitializer {


    @Autowired
    UserRepository userRepository;

    @MockBean
    UserMapper userMapper;

    @Autowired
    UserService userService;


    @Test
    void shouldNotSaveUserIfRoleDoesNotExist() {

        UserRequest userRequest = USER_REQUEST.withRole("");
        assertThrows(ApiException.class, () -> userService.saveUser(userRequest));

        assertEquals(0, userRepository.count());
    }


    @Test
    void shouldNotSaveUser() {
        when(userMapper.requestToEntity(USER_REQUEST)).thenReturn(USER);

        userService.saveUser(USER_REQUEST);

        assertEquals(1, userRepository.count());
    }
}
