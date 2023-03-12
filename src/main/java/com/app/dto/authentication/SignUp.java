package com.app.dto.authentication;

import lombok.Data;

@Data
public class SignUp {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
