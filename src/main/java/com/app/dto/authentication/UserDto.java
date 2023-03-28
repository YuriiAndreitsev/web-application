package com.app.dto.authentication;

import com.app.domain.RoleEnum;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleEnum> roleEnumSet;
}
