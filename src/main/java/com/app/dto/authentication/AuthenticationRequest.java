package com.app.dto.authentication;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
