package com.app.dto.authentication;

import com.app.domain.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleEnum> roleEnumSet;
}
