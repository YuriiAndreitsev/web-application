package com.app.repository;

import com.app.domain.RoleEnum;
import com.app.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByRole(RoleEnum role);

    Set<UserRole> findAllByRoleIn(Set<RoleEnum> roleSet);
}
