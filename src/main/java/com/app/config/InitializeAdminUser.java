package com.app.config;

import com.app.domain.RoleEnum;
import com.app.dto.authentication.UserDto;
import com.app.service.RoleService;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@RequiredArgsConstructor
@Component
public class InitializeAdminUser implements CommandLineRunner {
    private final RoleService roleService;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        roleService.createUserRole(RoleEnum.USER);
        roleService.createUserRole(RoleEnum.ADMIN);
        roleService.createUserRole(RoleEnum.MANAGER);

        userService.createUser(new UserDto("Yurii", "Andreitsev", "y.andreitsev@digiscorp.com", "admin@123", Set.of(RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.MANAGER)));
        userService.createUser(new UserDto("Jack", "Black", "j.black@gmail.com", "user@123", Set.of(RoleEnum.USER)));
    }
}
