package com.per.adoption.support.users.controller;

import com.per.adoption.support.users.config.PostgresContainerInitializer;
import com.per.adoption.support.users.dto.UserRequest;
import com.per.adoption.support.users.repository.UserRepository;
import com.per.adoption.support.users.repository.UserRoleRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static com.per.adoption.support.users.util.Constants.USER_REQUEST;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIT implements PostgresContainerInitializer {

    @LocalServerPort
    private Integer port;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost:" + port;
        userRepository.deleteAll();
    }


    @Test
    void shouldNotSaveUserIfRoleIsNotGiven() {

        UserRequest userRequest = USER_REQUEST.withRole("");

        given()
                .body(userRequest)
                .contentType(ContentType.JSON)
                .when()
                .post("/users")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    void shouldNotSaveUserIfRoleDoesNotExist() {
        given()
                .body(USER_REQUEST.withRole("NO_ROLE"))
                .contentType(ContentType.JSON)
                .when()
                .post("/users")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


    @Test
    void shouldSaveUser() {
        var response = given()
                .body(USER_REQUEST)
                .contentType(ContentType.JSON)
                .when()
                .post("/users")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().response();

        assertNotNull(response.headers().get(HttpHeaders.LOCATION));
        assertThat(userRepository.count()).isEqualTo(1);
    }


    @Test
    void shouldNotSaveUserWhenTheyAlreadyExist() {

        saveUser()
                .then()
                .statusCode(HttpStatus.CREATED.value());

        // Try to save the same user
        saveUser()
                .then()
                .statusCode(HttpStatus.CONFLICT.value());

        assertThat(userRepository.count()).isEqualTo(1);
    }

    private static Response saveUser() {
        return given()
                .body(USER_REQUEST)
                .contentType(ContentType.JSON)
                .when()
                .post("/users");
    }


}