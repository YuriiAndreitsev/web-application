package com.app.controller;

import com.app.dto.authentication.AuthenticationResponse;
import com.app.dto.authentication.UserDto;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserDto request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

//    @PostMapping("/admin")
//    public ResponseEntity<AuthenticationResponse> createAdminUser(@RequestBody UserDto request) {
//        return ResponseEntity.ok(userService.createAdminUser(request));
//    }
}
