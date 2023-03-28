package com.app.controller;

import com.app.domain.RoleEnum;
import com.app.domain.UserRole;
import com.app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping()
    public UserRole createUserRole(@RequestBody RoleEnum role) {
        return roleService.createUserRole(role);
    }
}
