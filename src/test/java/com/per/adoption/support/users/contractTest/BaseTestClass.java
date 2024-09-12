package com.per.adoption.support.users.contractTest;

import com.per.adoption.support.users.controller.UserController;
import com.per.adoption.support.users.model.User;
import com.per.adoption.support.users.repository.UserRepository;
import com.per.adoption.support.users.repository.UserRoleRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.OffsetDateTime;
import java.util.Optional;

import static com.per.adoption.support.users.util.Constants.USER;
import static com.per.adoption.support.users.util.Constants.USER_ROLE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public abstract class BaseTestClass {

    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserRoleRepository userRoleRepository;

    @BeforeEach
    public void before() {
        RestAssuredMockMvc.standaloneSetup(this.userController);

        User user = new User(1, USER.getUserId(), USER.getIdentityId(),
                OffsetDateTime.now(), OffsetDateTime.now(), USER.getName(), USER.getEmail(),
                USER.getPostalCode(), USER.getCountry(), USER_ROLE);

        when(userRepository.save(any())).thenReturn(user);
        when(userRoleRepository.findByName(anyString())).thenReturn(Optional.of(USER_ROLE));
    }

}
