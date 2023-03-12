package com.app.service.authentication;

import com.app.domain.Role;
import com.app.domain.User;
import com.app.dto.authentication.AuthenticationRequest;
import com.app.dto.authentication.AuthenticationResponse;
import com.app.dto.authentication.SignUp;
import com.app.repository.UserRepository;
import com.app.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse signInUser(SignUp request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return AuthenticationResponse.builder()
                .jwt(jwtService.generateToken(user))
                .build();
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        return AuthenticationResponse.builder()
                .jwt(jwtService.generateToken(user))
                .build();
    }
}
