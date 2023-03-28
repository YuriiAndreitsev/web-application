package com.app.controller;

import com.app.domain.RoleEnum;
import com.app.domain.UserRole;
import com.app.dto.authentication.AuthenticationResponse;
import com.app.dto.authentication.UserDto;
import com.app.service.RoleService;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize(value = "{'ADMIN'}")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("/role")
    public UserRole createUserRole(@RequestBody RoleEnum role) {
        return roleService.createUserRole(role);
    }
    @PostMapping("/user")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserDto request) {
        return ResponseEntity.ok(userService.createUser(request));
    }
}
