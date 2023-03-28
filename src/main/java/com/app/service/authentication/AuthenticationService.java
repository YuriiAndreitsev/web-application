package com.app.service.authentication;

import com.app.domain.User;
import com.app.dto.authentication.AuthenticationRequest;
import com.app.dto.authentication.AuthenticationResponse;
import com.app.repository.UserRepository;
import com.app.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticateUser(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        return AuthenticationResponse.builder()
                .jwt(jwtService.generateToken(user))
                .build();
    }
}
