package com.app.service;

import com.app.domain.User;
import com.app.domain.UserRole;
import com.app.dto.authentication.AuthenticationResponse;
import com.app.dto.authentication.UserDto;
import com.app.repository.UserRepository;
import com.app.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse createUser(UserDto userDto) {
        Set<UserRole> userRole = roleService.findUserRoleByTypeIn(userDto.getRoleEnumSet());
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .userRoleSet(userRole)
                .build();
        userRepository.save(user);
        log.info("User with email {} and roles {} is created", user.getEmail(), user.getUserRoleSet());
        return AuthenticationResponse.builder()
                .jwt(jwtService.generateToken(user))
                .build();
    }
}
