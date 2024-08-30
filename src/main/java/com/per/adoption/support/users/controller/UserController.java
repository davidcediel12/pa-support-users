package com.per.adoption.support.users.controller;


import com.per.adoption.support.users.dto.CreatedUser;
import com.per.adoption.support.users.dto.UserRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {


    @PostMapping
    public ResponseEntity<CreatedUser> createUser(
            @RequestHeader Map<String, String> headers,
            @RequestBody UserRequest userRequest) {

        System.out.println(headers);
        System.out.println(userRequest);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put(HttpHeaders.LOCATION, List.of("1092"));
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(
                        CreatedUser.builder()
                                .id(83L)
                                .createdAt(OffsetDateTime.now())
                                .build()
                );
    }
}
