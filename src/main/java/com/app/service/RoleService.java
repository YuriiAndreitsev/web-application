package com.app.service;

import com.app.domain.RoleEnum;
import com.app.domain.UserRole;
import com.app.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public UserRole createUserRole(RoleEnum role) {
        return roleRepository.save(UserRole.builder().role(role).build());
    }

    public List<UserRole> findAllUserRoles() {
        return roleRepository.findAll();
    }

    public UserRole findUserRoleByType(RoleEnum roleEnum) {
        return roleRepository.findByRole(roleEnum).orElseThrow();
    }

    public Set<UserRole> findUserRoleByTypeIn(Set<RoleEnum> roleEnumSet) {
        return roleRepository.findAllByRoleIn(roleEnumSet);
    }
}
