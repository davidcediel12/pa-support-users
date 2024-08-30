package com.per.adoption.support.users.controller;


import com.per.adoption.support.users.dto.CreatedUser;
import com.per.adoption.support.users.dto.UserRequest;
import com.per.adoption.support.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
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
            @RequestBody UserRequest userRequest) {

        log.info("Received new request to save user");

        log.debug("Headers {}", headers);
        log.debug("Body {}", userRequest);

        CreatedUser createdUser = userService.saveUser(userRequest);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put(HttpHeaders.LOCATION, List.of(createdUser.getId().toString()));

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(createdUser);
    }
}
