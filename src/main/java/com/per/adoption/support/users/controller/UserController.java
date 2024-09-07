package com.per.adoption.support.users.controller;


import com.per.adoption.support.users.dto.CreatedUser;
import com.per.adoption.support.users.dto.UserRequest;
import com.per.adoption.support.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<CreatedUser> createUser(
            @RequestHeader Map<String, String> headers,
            @RequestBody @Valid UserRequest userRequest) {

        log.info("Received new request to save user");

        log.debug("Headers {}", headers);
        log.debug("Body {}", userRequest);

        CreatedUser createdUser = userService.saveUser(userRequest);

        return ResponseEntity
                .created(URI.create(createdUser.getId().toString()))
                .body(createdUser);
    }
}
